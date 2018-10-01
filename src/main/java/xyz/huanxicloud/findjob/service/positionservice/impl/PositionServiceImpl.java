package xyz.huanxicloud.findjob.service.positionservice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.mapper.PositionMapper;
import xyz.huanxicloud.findjob.pojo.Position;
import xyz.huanxicloud.findjob.service.positionservice.PositionService;

public class PositionServiceImpl implements PositionService {
    @Autowired
    PositionMapper positionMapper;
    @Override
    public ReturnMessage publicPosition(Position position) {
        if (positionMapper.insert(position)>0)
            return new ReturnMessage(1,"发布成功!");
        return new ReturnMessage(0,"发布失败!");
    }

    @Override
    public ReturnMessage getPositions(int page, int size) {
        return null;
    }
}
