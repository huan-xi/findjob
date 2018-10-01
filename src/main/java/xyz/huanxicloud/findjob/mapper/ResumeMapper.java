package xyz.huanxicloud.findjob.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.huanxicloud.findjob.pojo.Resume;
import xyz.huanxicloud.findjob.pojo.ResumeExample;

public interface ResumeMapper {
    long countByExample(ResumeExample example);

    int deleteByExample(ResumeExample example);

    int deleteByPrimaryKey(Integer resumeId);

    int insert(Resume record);

    int insertSelective(Resume record);

    List<Resume> selectByExample(ResumeExample example);

    Resume selectByPrimaryKey(Integer resumeId);

    int updateByExampleSelective(@Param("record") Resume record, @Param("example") ResumeExample example);

    int updateByExample(@Param("record") Resume record, @Param("example") ResumeExample example);

    int updateByPrimaryKeySelective(Resume record);

    int updateByPrimaryKey(Resume record);
}