package cn.prosayj.blog.portal.operation.service;


import cn.prosayj.blog.core.dao.domain.operation.Tag;
import cn.prosayj.blog.core.dao.domain.operation.vo.TagVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * TagService
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
public interface TagService extends IService<Tag> {

    /**
     * 获取tagVoList
     * @return
     */
    List<TagVO> listTagVo();
}
