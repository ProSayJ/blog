package cn.prosayj.blog.core.dao.entity.article.dto;

import cn.prosayj.blog.core.dao.entity.article.Article;
import cn.prosayj.blog.core.dao.entity.operation.Tag;
import lombok.Data;

import java.util.List;

/**
 * ArticleDTO
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@Data
public class ArticleDTO extends Article {

    private List<Tag> tagList;

}
