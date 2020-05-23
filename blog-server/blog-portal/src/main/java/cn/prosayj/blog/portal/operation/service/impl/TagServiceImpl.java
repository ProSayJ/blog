package cn.prosayj.blog.portal.operation.service.impl;

import cn.prosayj.blog.core.dao.entity.operation.Tag;
import cn.prosayj.blog.core.dao.entity.operation.vo.TagVO;
import cn.prosayj.blog.core.dao.mapper.operation.TagMapper;
import cn.prosayj.blog.portal.operation.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TagServiceImpl
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@Service("TagPortalService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {


    /**
     * 获取tagVoList
     *
     * @return
     */
    @Override
    public List<TagVO> listTagVo() {
        return baseMapper.listTagVo();
    }
}
