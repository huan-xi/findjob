package xyz.huanxicloud.findjob.service.userservice;

import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.pojo.User;

public interface UserService {
    public User findUserById(String id);
    public int updateByPrimaryKey(User user);
    public ReturnMessage getUserInfo(String id);
    public ReturnMessage editInfo(String id,String phone,String name,String types);
}
