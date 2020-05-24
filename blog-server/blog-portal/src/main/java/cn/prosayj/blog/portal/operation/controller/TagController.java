package cn.prosayj.blog.portal.operation.controller;

import cn.prosayj.blog.core.common.Result;
import cn.prosayj.blog.core.common.constants.constant.RedisCacheNames;
import cn.prosayj.blog.core.dao.domain.operation.vo.TagVO;
import cn.prosayj.blog.portal.operation.service.TagService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * TagController
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@RestController("tagPortalController")
@CacheConfig(cacheNames = RedisCacheNames.TAG)
@RequestMapping("/operation")
public class TagController {

    @Resource
    private TagService tagService;

    @GetMapping("/tags")
    @Cacheable
    public Result listTag() {
        List<TagVO> tagList = tagService.listTagVo();
        return Result.ok().put("tagList",tagList);
    }

}
