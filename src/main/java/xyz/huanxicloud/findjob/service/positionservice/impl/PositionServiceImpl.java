package xyz.huanxicloud.findjob.service.positionservice.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.huanxicloud.findjob.common.Constant;
import xyz.huanxicloud.findjob.common.PageResult;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.mapper.POrderMapper;
import xyz.huanxicloud.findjob.mapper.PositionMapper;
import xyz.huanxicloud.findjob.mapper.UserMapper;
import xyz.huanxicloud.findjob.mapper.VenderMapper;
import xyz.huanxicloud.findjob.pojo.Position;
import xyz.huanxicloud.findjob.pojo.PositionExample;
import xyz.huanxicloud.findjob.pojo.Vender;
import xyz.huanxicloud.findjob.service.positionservice.PositionService;
import xyz.huanxicloud.findjob.util.Util;

import java.util.Date;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    PositionMapper positionMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    VenderMapper venderMapper;
    @Autowired
    POrderMapper pOrderMapper;
    @Override
    public ReturnMessage publicPosition(String id,Position position) {
        Vender vender=venderMapper.selectByPrimaryKey(id);
        if (!(vender!=null&&vender.getPhone()!=null&&vender.getPhone().length()==11&&vender.getName()!=null&&vender.getName().length()>=3&&vender.getAddress().length()>0))
            return new ReturnMessage(3,"请填写好公司信息在发布职位信息");
        position.sethCount(0);
        position.setCreateTime(new Date().getTime());
        position.setVenderId(id);
        position.setStatus(Constant.getPositionStatusOk());
        String name=venderMapper.selectByPrimaryKey(id).getName();
        position.setCompany(name);
        if (positionMapper.insert(position)>0)
            return new ReturnMessage(1,"发布成功!");
        return new ReturnMessage(0,"发布失败!");
    }

    /**
     *
     * @param id
     * @param page
     * @param size
     * @param type 1 差已完成 0差未完成
     * @return
     */
    @Override
    public ReturnMessage getPositionsByVender(String id, int page, int size,int type) {
        PageHelper.startPage(page,size);
        PositionExample example=new PositionExample();
        example.createCriteria().andVenderIdEqualTo(id).andStatusNotEqualTo(Constant.getPositionStatusNo());
        if(type==1)
            example.getOredCriteria().get(0).andStatusNotEqualTo(Constant.getOderStatusWaite());
        else
            example.getOredCriteria().get(0).andStatusEqualTo(Constant.getOderStatusWaite());
        Page<Position> positions= (Page<Position>) positionMapper.selectByExample(example);
        return new ReturnMessage(1,new PageResult(positions.getTotal(),positions.getResult(),positions.getPageNum()));
    }

    @Override
    public ReturnMessage getPositions(int page, int size) {
        PageHelper.startPage(page,size);
        PositionExample example=new PositionExample();
        example.createCriteria().andStatusNotEqualTo(Constant.getPositionStatusNo()) //没有禁用
        .andCreateTimeLessThan(new Date().getTime()-1000*120)//两分钟之前
        .andCreateTimeGreaterThan(Util.getTodayTime()); //大于今天
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

    @Override
    public ReturnMessage deletePosition(String id, int positionId) {
        Position position=positionMapper.selectByPrimaryKey(positionId);
        position.setStatus(Constant.getPositionStatusNo());
        if (positionMapper.updateByPrimaryKey(position)>0) return new ReturnMessage(1,"删除成功！");
        return new ReturnMessage(0,"删除失败！");
    }


}
