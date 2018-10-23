package xyz.huanxicloud.findjob.service.venderservice.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import xyz.huanxicloud.findjob.common.Constant;
import xyz.huanxicloud.findjob.common.PageResult;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.mapper.*;
import xyz.huanxicloud.findjob.pojo.*;
import xyz.huanxicloud.findjob.service.venderservice.VenderService;
import xyz.huanxicloud.findjob.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VenderServiceImpl implements VenderService {
    @Autowired
    VenderMapper venderMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    POrderMapper pOrderMapper;
    @Autowired
    PositionMapper positionMapper;
    @Autowired
    OperateLogMapper operateLogMapper;

    @Override
    public ReturnMessage editVender(String id, Vender vender) {
        //数据校验
        if (vender.getPhone().length() != 11) return new ReturnMessage(5002, "手机号格式不正确");
        Vender venderl = venderMapper.selectByPrimaryKey(id);
        vender.setVenderId(id);
        vender.setStatus(venderl.getStatus());
        vender.setCreateTime(venderl.getCreateTime());
        if (venderMapper.updateByPrimaryKey(vender) > 0)
            return new ReturnMessage(1, "修改成功");
        return new ReturnMessage(0, "修改失败");
    }

    @Override
    public ReturnMessage getInfo(String id) {
        Vender vender = venderMapper.selectByPrimaryKey(id);
        ;
        if (vender != null) return new ReturnMessage(1, vender);
        return new ReturnMessage(0, "获取信息失败");
    }

    @Transactional
    @Override
    public ReturnMessage cancelOrder(String venderId, int orderId) {
        //查询用户今日取消操作日志
        OperateLogExample example = new OperateLogExample();
        example.createCriteria()
                .andCreateTimeGreaterThanOrEqualTo(Util.getTodayTime()) //今天的日志
                .andTypeEqualTo(Constant.getLog_ORPRATE_CANCEL_ORDER()) //取消操作
                .andOperatorTypeEqualTo(Constant.getLog_USER_USER())//用户
                .andOperatorEqualTo(venderId);//此用户
        List<OperateLog> logs = operateLogMapper.selectByExample(example);
        if (logs.size() > 0)
            return new ReturnMessage(0, "一天只能取消一次订单哦！");
        try {
            //开始取消
            POrder order = pOrderMapper.selectByPrimaryKey(orderId);
            //职位数量加1
            Position position = positionMapper.selectByPrimaryKey(order.getPositionId());
            if (position == null) return new ReturnMessage(2, "订单异常,修改失败");
            //订单状态改为取消
            position.sethCount(position.gethCount() - 1);
            position.setStatus(Constant.getPositionStatusOk());
            order.setStatus(Constant.getOderStatusVenderCancel());
            //写入日志
            OperateLog operateLog = new OperateLog();
            operateLog.setCreateTime(new Date().getTime());
            operateLog.setOperator(venderId);
            operateLog.setOperatorType(Constant.getLog_USER_USER());
            operateLog.setType(Constant.getLog_ORPRATE_CANCEL_ORDER());
            operateLogMapper.insert(operateLog);
            positionMapper.updateByPrimaryKey(position);
            if (pOrderMapper.updateByPrimaryKey(order) > 0) return new ReturnMessage(1, "取消订单成功");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ReturnMessage(4, "取消失败,服务器出现异常");
        }
        return new ReturnMessage(101, "未知错误！");
    }

    @Override
    public ReturnMessage deleteOrder(String venderId, int orderId) {
        return deleteOrder(venderId, orderId, pOrderMapper);
    }

    public static ReturnMessage deleteOrder(String venderId, int orderId, POrderMapper pOrderMapper) {
        POrderExample example = new POrderExample();
        example.createCriteria().andUserIdEqualTo(venderId)
                .andOrderIdEqualTo(orderId);
        List<POrder> orders = pOrderMapper.selectByExample(example);
        if (orders.size() > 0) {
            POrder pOrder = orders.get(0);
            if (pOrder.getStatus().equals(Constant.getOderStatusVenderDelete()))
                pOrder.setStatus(Constant.getOderStatusAllDelete());
            else
                pOrder.setStatus(Constant.getOderStatusUserDelete());
            if (pOrderMapper.updateByPrimaryKey(pOrder) > 0)
                return new ReturnMessage(1, "订单删除成功");
        }
        return new ReturnMessage(0, "删除失败,您不存在该订单！");
    }

    @Override
    public ReturnMessage deletePosition(String venderId, int positionId) {
        return null;
    }

    @Override
    public ReturnMessage getOrders(String id, int page, int size, int type) {
        PageHelper.startPage(page, size);
        //获取没删除的订单
        POrderExample example = new POrderExample();
        example.createCriteria()
                .andStatusNotEqualTo(Constant.getOderStatusVenderDelete())
                .andStatusNotEqualTo(Constant.getOderStatusAllDelete())
                .andVenderIdEqualTo(id);
        if (type == 1)
            example.getOredCriteria().get(0).andStatusEqualTo(Constant.getOderStatusWaite());
        else
            example.getOredCriteria().get(0).andStatusNotEqualTo(Constant.getOderStatusWaite());
        Page<POrder> pOrders = (Page<POrder>) pOrderMapper.selectByExample(example);
        List<OrderWithUser> orders = new ArrayList<>();
        for (POrder p : pOrders) {
            OrderWithUser order = new OrderWithUser();
            order.setpOrder(p);
            order.setPosition(positionMapper.selectByPrimaryKey(p.getPositionId()));
            order.setUser(userMapper.selectByPrimaryKey(p.getUserId()));
            orders.add(order);
        }
        return new ReturnMessage(1, new PageResult(pOrders.getTotal(), orders, pOrders.getPageNum()));
    }
}
