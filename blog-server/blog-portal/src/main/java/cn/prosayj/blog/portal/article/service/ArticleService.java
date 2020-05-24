package cn.prosayj.blog.portal.article.service;



import cn.prosayj.blog.core.dao.PageUtils;
import cn.prosayj.blog.core.dao.domain.article.Article;
import cn.prosayj.blog.core.dao.domain.article.vo.ArticleVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 */
public interface ArticleService extends IService<Article> {

    /**
     * 分页分类获取列表
     * @param params
     * @return
     */
    PageUtils queryPageCondition(Map<String, Object> params);

    /**
     * 获取ArticleVo对象
     * @param articleId
     * @return
     */
    ArticleVO getArticleVo(Integer articleId);

    /**
     * 获取简单的Article对象
     * @param articleId
     * @return
     */
     ArticleVO getSimpleArticleVo(Integer articleId);

}
