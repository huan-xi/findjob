package xyz.huanxicloud.findjob.service.userservice;

import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.pojo.Resume;

public interface UserService {
    public ReturnMessage findUserById(int id);
    public ReturnMessage getUserResume(int id);
    public ReturnMessage saveUserResume(int id, Resume resume);
}
