package xyz.huanxicloud.findjob.service.userservice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.mapper.UserMapper;
import xyz.huanxicloud.findjob.pojo.User;
import xyz.huanxicloud.findjob.service.positionservice.PositionService;
import xyz.huanxicloud.findjob.service.userservice.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    PositionService positionService;
    @Autowired
    UserMapper userMapper;

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
    public ReturnMessage editInfo(String id,String phone,String name,String types) {
        User user=userMapper.selectByPrimaryKey(id);
        //工种处理
        user.setType(types);
        user.setPhone(phone);
        user.setName(name);
        if (userMapper.updateByPrimaryKey(user) > 0)
            return new ReturnMessage(1, "修改信息成功");
        return new ReturnMessage(0, "修改信息失败！");
    }
}
