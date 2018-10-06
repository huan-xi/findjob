package xyz.huanxicloud.findjob.service.positionservice;

import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.pojo.Position;

public interface PositionService {
    public ReturnMessage publicPosition(String id,Position position);

    /**
     * 获取已发布职位信息
     * @param page
     * @param size
     * @return
     */
    public ReturnMessage getPositionsByVender(String id,int page, int size);
    public ReturnMessage getPositions(int page, int size);
    /**
     * 获取详细信息
     * @param id
     * @return
     */
    public ReturnMessage getPosition(int id);

    public  Position getPositionInfo(int positionId);

    public int update(Position position);

    public ReturnMessage editPosition(String vender, Position position);
}
