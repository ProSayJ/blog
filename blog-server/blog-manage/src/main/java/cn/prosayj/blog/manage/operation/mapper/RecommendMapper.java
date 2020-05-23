package cn.prosayj.blog.manage.operation.mapper;

import cn.prosayj.blog.core.dao.entity.operation.Recommend;
import cn.prosayj.blog.core.dao.entity.operation.vo.RecommendVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 推荐 Mapper 接口
 * </p>
 *
 */
@Mapper
public interface RecommendMapper extends BaseMapper<Recommend> {

    /**
     * 获取推荐文章列表
     * @return
     */
    List<RecommendVO> listSelect();

    /**
     * 获取推荐列表
     * @return
     */
    List<RecommendVO> listRecommendVo();

    /**
     * 获取最热列表
     * @return
     */
    List<RecommendVO> listHotRead();
}
