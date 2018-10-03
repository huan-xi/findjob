package xyz.huanxicloud.findjob.service.venderservice;

import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.pojo.Vender;

public interface VenderService {

    public ReturnMessage editVender(String id, Vender vender);

    public ReturnMessage getInfo(String id);
}
