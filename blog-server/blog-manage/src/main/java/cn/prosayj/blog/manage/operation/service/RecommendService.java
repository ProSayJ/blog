package cn.prosayj.blog.manage.operation.service;

import cn.prosayj.blog.core.dao.PageUtils;
import cn.prosayj.blog.core.dao.entity.operation.Recommend;
import cn.prosayj.blog.core.dao.entity.operation.vo.RecommendVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 推荐 服务类
 * </p>
 *
 */
public interface RecommendService extends IService<Recommend> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取推荐列表
     * @return
     */
    List<RecommendVO> listSelect();

    /**
     * 更新置顶状态
     * @param id
     */
    void updateTop(Integer id);

    /**
     * 批量删除
     * @param articleIds
     * @param value
     */
    void deleteBatchByLinkId(Integer[] articleIds, int value);
}
