package xyz.huanxicloud.findjob.service.userservice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.mapper.ResumeMapper;
import xyz.huanxicloud.findjob.mapper.UserMapper;
import xyz.huanxicloud.findjob.pojo.Resume;
import xyz.huanxicloud.findjob.service.userservice.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    ResumeMapper resumeMapper;
    @Override
    public ReturnMessage findUserById(int id) {
        return null;
    }

    @Override
    public ReturnMessage getUserResume(int id) {
        return null;
    }

    @Override
    public ReturnMessage saveUserResume(int id, Resume resume) {
        resume.setUserId(id);
        if (resumeMapper.insert(resume)>0)
            return new ReturnMessage(1,"保存成功！");
        return new ReturnMessage(0,"保存失败！");
    }
}
