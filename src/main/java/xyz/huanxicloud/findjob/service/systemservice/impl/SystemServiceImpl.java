package xyz.huanxicloud.findjob.service.systemservice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.huanxicloud.findjob.common.Constant;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.mapper.SystemMapper;
import xyz.huanxicloud.findjob.pojo.ServiceInfo;
import xyz.huanxicloud.findjob.pojo.System;
import xyz.huanxicloud.findjob.pojo.SystemExample;
import xyz.huanxicloud.findjob.service.systemservice.SystemService;

import java.util.List;

@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    SystemMapper systemMapper;

    @Override
    public ReturnMessage getAllWT() {
        SystemExample example = new SystemExample();
        example.createCriteria().andSKeyEqualTo(Constant.getSystemKeyWt());
        List<System> systems = systemMapper.selectByExample(example);
        return new ReturnMessage(1, systems);
    }

    @Override
    public ReturnMessage addWT(String name) {
        if (StringUtils.isEmpty(name)) return new ReturnMessage(0, "工种名不能为空");
        System system = new System();
        system.setsKey(Constant.getSystemKeyWt());
        system.setsValue(name);
        if (systemMapper.insert(system) > 0) return new ReturnMessage(1, "添加成功");
        return new ReturnMessage(2, "添加失败");
    }

    //获取客服信息
    @Override
    public ReturnMessage getServiceInfo() {
        SystemExample example = new SystemExample();
        example.createCriteria().andSKeyEqualTo(Constant.getSystemKeyPhone());
        List<System> systems = systemMapper.selectByExample(example);
        String phone = systems.get(0).getsValue();
        SystemExample example2 = new SystemExample();
        example2.createCriteria().andSKeyEqualTo(Constant.getSystemKeyPhone());
        List<System> systems2 = systemMapper.selectByExample(example);
        String name = systems2.get(0).getsValue();
        return new ReturnMessage(1, new ServiceInfo(phone, name));
    }

    private String getValueByKey(String key) {
        SystemExample example = new SystemExample();
        example.createCriteria().andSKeyEqualTo(key);
        List<System> systems = systemMapper.selectByExample(example);
        if (systems != null && systems.size() > 0)
            return systems.get(0).getsValue();
        return null;
    }

    @Override
    public ReturnMessage getUserNotice() {
        String value = this.getValueByKey(Constant.getSystemKeyUserNotice());
        if (!StringUtils.isEmpty(value))
            return new ReturnMessage(1, value);
        return new ReturnMessage(0, "");
    }

    @Override
    public ReturnMessage getVenderNotice() {
        String value = this.getValueByKey(Constant.getSystemKeyVenderNotice());
        if (!StringUtils.isEmpty(value))
            return new ReturnMessage(1, value);
        return new ReturnMessage(0, "");
    }
}
