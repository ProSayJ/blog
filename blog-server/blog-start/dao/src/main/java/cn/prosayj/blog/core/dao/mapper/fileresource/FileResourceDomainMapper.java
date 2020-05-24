package cn.prosayj.blog.core.dao.mapper.fileresource;

import cn.prosayj.blog.core.dao.domain.fileresource.FileResourceDomain;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FileResourceDomainMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FileResourceDomain record);

    int insertSelective(FileResourceDomain record);

    FileResourceDomain selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FileResourceDomain record);

    int updateByPrimaryKeyWithBLOBs(FileResourceDomain record);

    int updateByPrimaryKey(FileResourceDomain record);
}