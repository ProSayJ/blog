package cn.prosayj.blog.portal.operation.service.impl;

import cn.prosayj.blog.core.common.constants.enums.ModuleEnum;
import cn.prosayj.blog.core.dao.domain.article.vo.ArticleVO;
import cn.prosayj.blog.core.dao.domain.book.vo.BookNoteVO;
import cn.prosayj.blog.core.dao.domain.operation.Recommend;
import cn.prosayj.blog.core.dao.domain.operation.vo.RecommendVO;
import cn.prosayj.blog.manage.operation.mapper.RecommendMapper;
import cn.prosayj.blog.portal.article.service.ArticleService;
import cn.prosayj.blog.portal.book.service.BookNoteService;
import cn.prosayj.blog.portal.operation.service.RecommendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * RecommendServiceImpl
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@Service("recommendPortalService")
public class RecommendServiceImpl extends ServiceImpl<RecommendMapper, Recommend> implements RecommendService {

    @Resource
    private ArticleService articleService;

    @Resource
    private BookNoteService bookNoteService;


    @Override
    public List<RecommendVO> listRecommendVo() {
        List<RecommendVO> recommendList =this.baseMapper.listRecommendVo();
        return genRecommendList(recommendList);
    }

    @Override
    public List<RecommendVO> listHotRead() {
        List<RecommendVO> hotReadList =this.baseMapper.listHotRead();
        genRecommendList(hotReadList);
        hotReadList.get(0).setTop(true);
        return hotReadList;
    }

    private List<RecommendVO> genRecommendList(List<RecommendVO> recommendList) {
        recommendList.forEach(recommendVo -> {
            if(ModuleEnum.ARTICLE.getValue() == recommendVo.getType()){
                ArticleVO simpleArticleVo = articleService.getSimpleArticleVo(recommendVo.getLinkId());
                BeanUtils.copyProperties(simpleArticleVo,recommendVo);
                recommendVo.setUrlType("article");
            }else if(ModuleEnum.BOOK_NOTE.getValue() == recommendVo.getType()) {
                BookNoteVO simpleBookNoteVo = bookNoteService.getSimpleBookNoteVo(recommendVo.getLinkId());
                recommendVo.setUrlType("bookNote");
                BeanUtils.copyProperties(simpleBookNoteVo,recommendVo);
            }
        });
        return recommendList;
    }
}
