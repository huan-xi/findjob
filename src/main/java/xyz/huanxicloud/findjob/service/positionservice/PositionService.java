package xyz.huanxicloud.findjob.service.positionservice;

import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.pojo.Position;

public interface PositionService {
    public ReturnMessage publicPosition(Position position);
    public ReturnMessage getPositions(int page, int size);
}
