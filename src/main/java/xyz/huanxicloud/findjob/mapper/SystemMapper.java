package xyz.huanxicloud.findjob.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.huanxicloud.findjob.pojo.System;
import xyz.huanxicloud.findjob.pojo.SystemExample;

public interface SystemMapper {
    long countByExample(SystemExample example);

    int deleteByExample(SystemExample example);

    int deleteByPrimaryKey(Integer systemId);

    int insert(System record);

    int insertSelective(System record);

    List<System> selectByExample(SystemExample example);

    System selectByPrimaryKey(Integer systemId);

    int updateByExampleSelective(@Param("record") System record, @Param("example") SystemExample example);

    int updateByExample(@Param("record") System record, @Param("example") SystemExample example);

    int updateByPrimaryKeySelective(System record);

    int updateByPrimaryKey(System record);
}