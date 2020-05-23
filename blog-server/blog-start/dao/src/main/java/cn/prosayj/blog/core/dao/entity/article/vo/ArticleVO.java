package cn.prosayj.blog.core.dao.entity.article.vo;

import cn.prosayj.blog.core.dao.entity.article.Article;
import cn.prosayj.blog.core.dao.entity.operation.Tag;
import lombok.Data;

import java.util.List;

/**
 * ArticleVO
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@Data
public class ArticleVO extends Article {

    /**
     * 所属分类，以逗号分隔
     */
    private String categoryListStr;

    /**
     * 所属标签
     */
    private List<Tag> tagList;

}
