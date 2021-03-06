package xyz.huanxicloud.findjob.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.huanxicloud.findjob.pojo.POrder;
import xyz.huanxicloud.findjob.pojo.POrderExample;

import java.util.List;

public interface POrderMapper {
    long countByExample(POrderExample example);

    int deleteByExample(POrderExample example);

    int deleteByPrimaryKey(Integer orderId);

    int insert(POrder record);

    int insertSelective(POrder record);

    List<POrder> selectByExample(POrderExample example);

    POrder selectByPrimaryKey(Integer orderId);

    int updateByExampleSelective(@Param("record") POrder record, @Param("example") POrderExample example);

    int updateByExample(@Param("record") POrder record, @Param("example") POrderExample example);

    int updateByPrimaryKeySelective(POrder record);

    int updateByPrimaryKey(POrder record);
}
