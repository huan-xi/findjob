package xyz.huanxicloud.findjob.service.positionservice.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.huanxicloud.findjob.common.PageResult;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.mapper.PositionMapper;
import xyz.huanxicloud.findjob.pojo.Position;
import xyz.huanxicloud.findjob.pojo.PositionExample;
import xyz.huanxicloud.findjob.service.positionservice.PositionService;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    PositionMapper positionMapper;
    @Override
    public ReturnMessage publicPosition(Position position) {
        position.sethCount(0);
        if (positionMapper.insert(position)>0)
            return new ReturnMessage(1,"发布成功!");
        return new ReturnMessage(0,"发布失败!");
    }

    @Override
    public ReturnMessage getPositions(int page, int size) {
        PageHelper.startPage(page,size);
        PositionExample example=new PositionExample();
        Page<Position> positions= (Page<Position>) positionMapper.selectByExample(example);
        return new ReturnMessage(1,new PageResult(positions.getTotal(),positions.getResult(),positions.getPageNum()));
    }

    @Override
    public ReturnMessage getPosition(int id) {
        return new ReturnMessage(1,positionMapper.selectByPrimaryKey(id));
    }
}
