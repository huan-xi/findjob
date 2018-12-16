package xyz.huanxicloud.findjob.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.huanxicloud.findjob.pojo.Vender;
import xyz.huanxicloud.findjob.pojo.VenderExample;

public interface VenderMapper {
    long countByExample(VenderExample example);

    int deleteByExample(VenderExample example);

    int deleteByPrimaryKey(String venderId);

    int insert(Vender record);

    int insertSelective(Vender record);

    List<Vender> selectByExample(VenderExample example);

    Vender selectByPrimaryKey(String venderId);

    int updateByExampleSelective(@Param("record") Vender record, @Param("example") VenderExample example);

    int updateByExample(@Param("record") Vender record, @Param("example") VenderExample example);

    int updateByPrimaryKeySelective(Vender record);

    int updateByPrimaryKey(Vender record);
}