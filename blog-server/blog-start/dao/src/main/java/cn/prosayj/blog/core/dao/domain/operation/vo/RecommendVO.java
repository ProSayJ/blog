package cn.prosayj.blog.core.dao.domain.operation.vo;

import cn.prosayj.blog.core.dao.domain.operation.Recommend;
import cn.prosayj.blog.core.dao.domain.operation.Tag;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * RecommendVO
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@Data
public class RecommendVO extends Recommend {

    private String description;

    private Long readNum;

    private Long commentNum;

    private Long likeNum;

    private String urlType;

    private String cover;

    private Date createTime;

    private List<Tag> tagList;

}
