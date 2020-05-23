package cn.prosayj.blog.manage.operation.service.impl;

import cn.prosayj.blog.core.dao.PageUtils;
import cn.prosayj.blog.core.dao.Query;
import cn.prosayj.blog.core.dao.entity.operation.Recommend;
import cn.prosayj.blog.core.dao.entity.operation.vo.RecommendVO;
import cn.prosayj.blog.manage.operation.mapper.RecommendMapper;
import cn.prosayj.blog.manage.operation.service.RecommendService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 推荐 服务实现类
 * </p>
 *
 */
@Service
@Slf4j
public class RecommendServiceImpl extends ServiceImpl<RecommendMapper, Recommend> implements RecommendService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String title = (String) params.get("title");
        IPage<Recommend> page=baseMapper.selectPage(new Query<Recommend>(params).getPage(),
                new QueryWrapper<Recommend>().lambda()
                        .like(StringUtils.isNotEmpty(title),Recommend::getTitle,title));
        return new PageUtils(page);
    }

    /**
     * 获取推荐列表
     *
     * @return
     */
    @Override
    public List<RecommendVO> listSelect() {
        return baseMapper.listSelect();
    }

    /**
     * 更新置顶状态
     *
     * @param id
     */
    @Override
    public void updateTop(Integer id) {
        // 更新本条
        Recommend recommend = new Recommend();
        recommend.setTop(true);
        recommend.setId(id);
        this.baseMapper.updateById(recommend);
        //批量更新其他
        recommend.setTop(false);
        recommend.setId(null);
        this.baseMapper.update(recommend,new QueryWrapper<Recommend>().lambda()
                .ne(Recommend::getId,id));
    }

    /**
     * 批量删除
     *
     * @param linkIds
     * @param type
     */
    @Override
    public void deleteBatchByLinkId(Integer[] linkIds, int type) {
        for (int i = 0; i < linkIds.length; i++) {
            int linkId = linkIds[i];
            baseMapper.delete(new QueryWrapper<Recommend>().lambda()
                    .eq(Recommend::getLinkId,linkId)
                    .eq(Recommend::getType,type));
        }
    }

}
