package xyz.huanxicloud.findjob.service.systemservice;

import xyz.huanxicloud.findjob.common.ReturnMessage;

public interface SystemService {
    public ReturnMessage getAllWT();
    public ReturnMessage addWT(String name);
}
