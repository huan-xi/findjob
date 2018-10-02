package xyz.huanxicloud.findjob.service.systemservice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.mapper.SystemMapper;
import xyz.huanxicloud.findjob.pojo.System;
import xyz.huanxicloud.findjob.pojo.SystemExample;
import xyz.huanxicloud.findjob.service.systemservice.SystemService;

import java.util.List;

@Service
public class SystemServiceImpl implements SystemService {
    private final static String SYSTEM_KEY_WT="WT";
    @Autowired
    SystemMapper systemMapper;
    @Override
    public ReturnMessage getAllWT() {
        SystemExample example=new SystemExample();
        example.createCriteria().andSKeyEqualTo(SYSTEM_KEY_WT);
        List<System> systems=systemMapper.selectByExample(example);
        return new ReturnMessage(1,systems);
    }

    @Override
    public ReturnMessage addWT(String name) {
        if (StringUtils.isEmpty(name)) return new ReturnMessage(0,"工种名不能为空");
        System system=new System();
        system.setsKey(SYSTEM_KEY_WT);
        system.setsValue(name);
        if (systemMapper.insert(system)>0) return new ReturnMessage(1,"添加成功");
        return new ReturnMessage(2,"添加失败");
    }
}
