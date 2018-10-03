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

import java.util.Date;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    PositionMapper positionMapper;
    @Override
    public ReturnMessage publicPosition(String id,String name,Position position) {
        position.sethCount(0);
        position.setCreateTime(new Date().getTime());
        position.setVenderId(id);
        position.setCompany(name);
        if (positionMapper.insert(position)>0)
            return new ReturnMessage(1,"发布成功!");
        return new ReturnMessage(0,"发布失败!");
    }

    @Override
    public ReturnMessage getPositionsByVender(String id, int page, int size) {
        PageHelper.startPage(page,size);
        PositionExample example=new PositionExample();
        example.createCriteria().andVenderIdEqualTo(id);
        Page<Position> positions= (Page<Position>) positionMapper.selectByExample(example);
        return new ReturnMessage(1,new PageResult(positions.getTotal(),positions.getResult(),positions.getPageNum()));
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

    @Override
    public Position getPositionInfo(int positionId) {
        return positionMapper.selectByPrimaryKey(positionId);
    }

    @Override
    public int update(Position position) {
        return positionMapper.updateByPrimaryKey(position);
    }
}
