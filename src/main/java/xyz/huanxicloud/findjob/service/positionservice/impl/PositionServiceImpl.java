package xyz.huanxicloud.findjob.service.positionservice.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.huanxicloud.findjob.common.PageResult;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.mapper.PositionMapper;
import xyz.huanxicloud.findjob.mapper.VenderMapper;
import xyz.huanxicloud.findjob.pojo.Position;
import xyz.huanxicloud.findjob.pojo.PositionExample;
import xyz.huanxicloud.findjob.service.positionservice.PositionService;

import java.util.Date;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    PositionMapper positionMapper;
    @Autowired
    VenderMapper venderMapper;
    @Override
    public ReturnMessage publicPosition(String id,Position position) {
        position.sethCount(0);
        position.setCreateTime(new Date().getTime());
        position.setVenderId(id);
        String name=venderMapper.selectByPrimaryKey(id).getName();
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

    @Override
    public ReturnMessage editPosition(String vender, Position position) {
        Position position1=positionMapper.selectByPrimaryKey(position.getPositionId());
        if (position.getCount()<position1.gethCount()) return new ReturnMessage(0,"修改的人数不能小于已招人数");
        position.setVenderId(position1.getVenderId());
        position.setStatus(position1.getStatus());
        position.setCreateTime(position1.getCreateTime());
        position.setCompany(position1.getCompany());
        position.sethCount(position1.gethCount());
        if (positionMapper.updateByPrimaryKey(position)>0) return new ReturnMessage(1,"修改成功");
        return new ReturnMessage(2,"修改失败,请稍后重试!");
    }
}
