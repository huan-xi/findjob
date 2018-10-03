package xyz.huanxicloud.findjob.service.venderservice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.mapper.VenderMapper;
import xyz.huanxicloud.findjob.pojo.Vender;
import xyz.huanxicloud.findjob.service.venderservice.VenderService;

@Service
public class VenderServiceImpl implements VenderService {
    @Autowired
    VenderMapper venderMapper;

    @Override
    public ReturnMessage editVender(String id, Vender vender) {
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
        return new ReturnMessage(1,venderMapper.selectByPrimaryKey(id));
    }
}
