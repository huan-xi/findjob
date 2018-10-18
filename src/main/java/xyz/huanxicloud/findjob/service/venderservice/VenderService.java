package xyz.huanxicloud.findjob.service.venderservice;

import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.pojo.Vender;

public interface VenderService {

    public ReturnMessage editVender(String id, Vender vender);

    public ReturnMessage getInfo(String id);

    public ReturnMessage cancelOrder(String venderId, int orderId);
    public ReturnMessage deleteOrder(String venderId, int orderId);
    public ReturnMessage deletePosition(String venderId, int positionId);

    public ReturnMessage getOrders(String id, int page, int size,int type);
}
