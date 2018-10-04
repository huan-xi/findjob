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
import xyz.huanxicloud.findjob.pojo.*;
import xyz.huanxicloud.findjob.service.positionservice.PositionService;
import xyz.huanxicloud.findjob.service.userservice.UserService;
import xyz.huanxicloud.findjob.util.Util;

import java.lang.System;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    PositionService positionService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    POrderMapper pOrderMapper;
    @Autowired
    OperateLogMapper operateLogMapper;
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
    @Transactional
    @Override
    public ReturnMessage orderPosition(String userId, int positionId){
        //如果有单则不能再接今天的,如果没单随便接
        POrderExample pOrderExample = new POrderExample();
        pOrderExample.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(Constant.getOderStatusWaite());
        //查询未完成的
        List<POrder> pOrders = pOrderMapper.selectByExample(pOrderExample);
        Position position = positionService.getPositionInfo(positionId);
        if (position.gethCount() >= position.getCount()) return new ReturnMessage(3, "该订单已抢完了！");
        if (pOrders.size() > 0) {
            Long time = position.getTime();
            //如果此职位工作日有订单报错
            for (POrder p : pOrders) {
                if (Util.isSameDay(time,p.getWorkTime()))
                    return new ReturnMessage(2, "该日已有订单了哦！");
            }
        }
       try {
        //接单
        POrder pOrder = new POrder();
        pOrder.setCreateTime(new Date().getTime());
        pOrder.setStatus(Constant.getOderStatusWaite());
        pOrder.setPositionId(positionId);
        pOrder.setUserId(userId);
        pOrder.setWorkTime(position.getTime());
        if (pOrderMapper.insert(pOrder) > 0) {
            position.sethCount(position.gethCount()+1);
            if (positionService.update(position) > 0)
                return new ReturnMessage(1, "接单成功");
            else new ReturnMessage(3, "接单失败");
        }
        return new ReturnMessage(0, "接单失败");
       }catch (Exception e){
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
           return new ReturnMessage(4, "接单失败,服务器出现异常");
       }
    }
    @Transactional
    @Override
    public ReturnMessage cancelOrder(String userId, int orderId) {
        //查询用户今日取消操作日志
        OperateLogExample example=new OperateLogExample();
        example.createCriteria().andCreateTimeGreaterThanOrEqualTo(new Date().getTime()).andTypeEqualTo(Constant.getLog_ORPRATE_CANCEL_ORDER())
                .andOperatorEqualTo(Constant.getLog_USER_USER());
        List<OperateLog> logs=operateLogMapper.selectByExample(example);
        if (logs.size()>0)
            return new ReturnMessage(0,"一天只能取消一次订单哦！");
       try {
           //开始取消
           POrder order=pOrderMapper.selectByPrimaryKey(orderId);
           //职位数量加1
           Position position=positionService.getPositionInfo(order.getPositionId());
           if (position==null) return new ReturnMessage(2,"订单异常,修改失败");
           //订单状态改为取消
           position.sethCount(position.gethCount()-1);
           order.setStatus(Constant.getOderStatusCancel());
           //写入日志
           OperateLog operateLog= new OperateLog();
           operateLog.setCreateTime(new Date().getTime());
           operateLog.setOperator(userId);
           operateLog.setOperatorType(Constant.getLog_USER_USER());
           operateLog.setType(Constant.getLog_ORPRATE_CANCEL_ORDER());
           operateLogMapper.insert(operateLog);
           positionService.update(position);
           if (pOrderMapper.updateByPrimaryKey(order) >0) return new ReturnMessage(1,"取消订单成功");
       }catch (Exception e){
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
           return new ReturnMessage(4, "取消失败,服务器出现异常");
       }
        return new ReturnMessage(101,"未知错误！");
    }

    @Override
    public ReturnMessage getOrders(String id,int page,int size) {
        PageHelper.startPage(page,size);
        POrderExample example=new POrderExample();
        Page<POrder> pOrders= (Page<POrder>) pOrderMapper.selectByExample(example);
        List<Order> orders=new ArrayList<>();
        for(POrder p:pOrders){
            System.out.println(p.getOrderId());
            orders.add(pOrderMapper.selectOrderByPrimaryKey(p.getOrderId()));
        }
        return new ReturnMessage(1,new PageResult(pOrders.getTotal(),orders,pOrders.getPageNum()));
    }
}
