package cn.prosayj.blog.core.dao.mapper.operation;

import cn.prosayj.blog.core.dao.domain.operation.TagLink;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章标签多对多维护表 Mapper 接口
 * </p>
 *
 */
@Mapper
public interface TagLinkMapper extends BaseMapper<TagLink> {

}
