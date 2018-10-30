package xyz.huanxicloud.findjob.service.userservice.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import xyz.huanxicloud.findjob.common.Constant;
import xyz.huanxicloud.findjob.common.PageResult;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.mapper.OperateLogMapper;
import xyz.huanxicloud.findjob.mapper.POrderMapper;
import xyz.huanxicloud.findjob.mapper.UserMapper;
import xyz.huanxicloud.findjob.mapper.VenderMapper;
import xyz.huanxicloud.findjob.pojo.*;
import xyz.huanxicloud.findjob.service.positionservice.PositionService;
import xyz.huanxicloud.findjob.service.userservice.UserService;
import xyz.huanxicloud.findjob.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static xyz.huanxicloud.findjob.service.venderservice.impl.VenderServiceImpl.deleteOrder;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    PositionService positionService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    POrderMapper pOrderMapper;
    @Autowired
    OperateLogMapper operateLogMapper;
    @Autowired
    VenderMapper venderMapper;

    @Override
    public User findUserById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public ReturnMessage getUserInfo(String id) {
        User user = this.findUserById(id);
        if (user != null) {
            return new ReturnMessage(1, user);
        }
        return new ReturnMessage(0, "获取数据失败");
    }

    @Override
    public ReturnMessage editInfo(String id, String phone, String name, String types) {
        User user = userMapper.selectByPrimaryKey(id);
        //工种处理
        user.setType(types);
        user.setPhone(phone);
        user.setName(name);
        if (userMapper.updateByPrimaryKey(user) > 0)
            return new ReturnMessage(1, "修改信息成功");
        return new ReturnMessage(0, "修改信息失败！");
    }

    //用户接单
    @Transactional
    @Override
    public ReturnMessage orderPosition(String userId, int positionId) {
        User user=userMapper.selectByPrimaryKey(userId);
        if (!(user!=null&&user.getName()!=null&&user.getPhone()!=null&&user.getPhone().length()==11))
            return new ReturnMessage(3,"请先填写信息再接单");
        //用户禁用禁止接单
        if(user.getStatus().equals(Constant.getRoueStatusForbid()))
            return new ReturnMessage(4,"接单失败,您已被禁用,请联系客服解决问题！");
        Position position = positionService.getPositionInfo(positionId);
        //不是对应工种不能接单
        if (!(user.getType()!=null&&user.getType().contains(position.getType())))
            return new ReturnMessage(5,"接单失败,您不是对应工种");
        //如果有单则不能再接今天的,如果没单随便接
        POrderExample pOrderExample = new POrderExample();
        pOrderExample.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(Constant.getOderStatusWaite());
        //查询未完成的
        List<POrder> pOrders = pOrderMapper.selectByExample(pOrderExample);
        if (position.gethCount() >= position.getCount()) return new ReturnMessage(3, "该订单已抢完了！");
        if (pOrders.size() > 0) {
            Long time = position.getTime();
            //如果此职位工作日有订单报错
            for (POrder p : pOrders) {
                if (Util.isSameDay(time, p.getWorkTime()))
                    return new ReturnMessage(2, "该日已有订单了哦！");
            }
        }
        try {
            //接单
            POrder pOrder = new POrder();
            pOrder.setCreateTime(new Date().getTime());
            pOrder.setStatus(Constant.getOderStatusWaite());
            pOrder.setPositionId(positionId);
            pOrder.setWorkTime(position.getTime());
            pOrder.setVenderId(position.getVenderId());
            pOrder.setUserId(userId);
            if (pOrderMapper.insert(pOrder) > 0) {
                position.sethCount(position.gethCount() + 1);
                //判断人员是否已满
                if (position.gethCount() >= position.getCount())
                    position.setStatus(Constant.getPositionStatusNo());
                if (positionService.update(position) > 0)
                    return new ReturnMessage(1, "接单成功");
                else new ReturnMessage(3, "接单失败");
            }
            return new ReturnMessage(0, "接单失败");
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ReturnMessage(4, "接单失败,服务器出现异常");
        }
    }

    @Transactional
    @Override
    public ReturnMessage cancelOrder(String userId, int orderId) {
        //查询用户今日取消操作日志
        OperateLogExample example = new OperateLogExample();
        example.createCriteria()
                .andCreateTimeGreaterThanOrEqualTo(Util.getTodayTime()) //今天的日志
                .andTypeEqualTo(Constant.getLog_ORPRATE_CANCEL_ORDER()) //取消操作
                .andOperatorTypeEqualTo(Constant.getLog_USER_USER())//用户
                .andOperatorEqualTo(userId);//此用户
        List<OperateLog> logs = operateLogMapper.selectByExample(example);
        if (logs.size() > 0)
            return new ReturnMessage(0, "一天只能取消一次订单哦！");
        try {
            //开始取消
            POrder order = pOrderMapper.selectByPrimaryKey(orderId);
            //职位数量加1
            Position position = positionService.getPositionInfo(order.getPositionId());
            if (position == null) return new ReturnMessage(2, "订单异常,修改失败");
            //订单状态改为取消
            position.sethCount(position.gethCount() - 1);
            position.setStatus(Constant.getPositionStatusOk());
            order.setStatus(Constant.getOderStatusUserCancel());
            //写入日志
            OperateLog operateLog = new OperateLog();
            operateLog.setCreateTime(new Date().getTime());
            operateLog.setOperator(userId);
            operateLog.setOperatorType(Constant.getLog_USER_USER());
            operateLog.setType(Constant.getLog_ORPRATE_CANCEL_ORDER());
            operateLogMapper.insert(operateLog);
            positionService.update(position);
            if (pOrderMapper.updateByPrimaryKey(order) > 0) return new ReturnMessage(1, "取消订单成功");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ReturnMessage(4, "取消失败,服务器出现异常");
        }
        return new ReturnMessage(101, "未知错误！");
    }


    @Override
    public ReturnMessage getOrders(String id, int page, int size) {
        PageHelper.startPage(page, size);
        //获取没删除的订单
        POrderExample example = new POrderExample();
        example.createCriteria()
                .andStatusNotEqualTo(Constant.getOderStatusUserDelete())
                .andStatusNotEqualTo(Constant.getOderStatusAllDelete())
                .andUserIdEqualTo(id);
        Page<POrder> pOrders = (Page<POrder>) pOrderMapper.selectByExample(example);
        List<OrderWithVender> orders = new ArrayList<>();
        for (POrder p : pOrders) {
            OrderWithVender order = new OrderWithVender();
            order.setpOrder(p);
            order.setPosition(positionService.getPositionInfo(p.getPositionId()));
            order.setVender(venderMapper.selectByPrimaryKey(p.getVenderId()));
            orders.add(order);
        }
        return new ReturnMessage(1, new PageResult(pOrders.getTotal(), orders, pOrders.getPageNum()));
    }

    private int setOrderStatus(String userId, int orderId, String status) {
        POrderExample example = new POrderExample();
        example.createCriteria().andUserIdEqualTo(userId)
                .andOrderIdEqualTo(orderId);
        List<POrder> orders = pOrderMapper.selectByExample(example);
        if (orders.size() > 0) {
            POrder pOrder = orders.get(0);
            pOrder.setStatus(status);
            if (pOrderMapper.updateByPrimaryKey(pOrder) > 0)
                return 1;
        }
        return 0;
    }

    @Override
    public ReturnMessage deleteOrders(String userId, int orderId) {
        return deleteOrder(userId, orderId, pOrderMapper);
    }

    @Override
    public ReturnMessage finishOrders(String userId, int orderId) {
        if (this.setOrderStatus(userId, orderId, Constant.getOderStatusFinish()) == 1)
            return new ReturnMessage(1, "订单已完成");
        return new ReturnMessage(0, "操作失败,您不存在该订单！");
    }
}
