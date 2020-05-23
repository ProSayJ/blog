package cn.prosayj.blog.manage.operation.controller;

import cn.prosayj.blog.core.common.Result;
import cn.prosayj.blog.core.dao.PageUtils;
import cn.prosayj.blog.core.dao.base.AbstractController;
import cn.prosayj.blog.core.common.constants.constant.RedisCacheNames;
import cn.prosayj.blog.core.dao.entity.operation.Recommend;
import cn.prosayj.blog.core.dao.entity.operation.vo.RecommendVO;
import cn.prosayj.blog.manage.operation.service.RecommendService;
import cn.prosayj.blog.core.util.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 推荐 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/admin/operation/recommend")
@CacheConfig(cacheNames = RedisCacheNames.RECOMMEND)
public class RecommendController extends AbstractController {
    @Resource
    private RecommendService recommendService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("operation:recommend:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = recommendService.queryPage(params);

        return Result.ok().put("page", page);
    }

    @GetMapping("/select")
    @RequiresPermissions("operation:recommend:list")
    public Result select () {
        List<RecommendVO> recommendList = recommendService.listSelect();
        return Result.ok().put("recommendList",recommendList);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("operation:recommend:info")
    public Result info(@PathVariable("id") String id){
       Recommend recommend = recommendService.getById(id);

        return Result.ok().put("recommend", recommend);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("operation:recommend:save")
    @CacheEvict(allEntries = true)
    public Result save(@RequestBody Recommend recommend){
        ValidatorUtils.validateEntity(recommend);
        recommendService.save(recommend);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @RequiresPermissions("operation:recommend:update")
    @CacheEvict(allEntries = true)
    public Result update(@RequestBody Recommend recommend){
        ValidatorUtils.validateEntity(recommend);
        recommendService.updateById(recommend);
        return Result.ok();
    }

    @PutMapping("/top/{id}")
    @RequiresPermissions("operation:recommend:update")
    @CacheEvict(allEntries = true)
    public Result updateTop (@PathVariable Integer id) {
        recommendService.updateTop(id);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("operation:recommend:delete")
    @CacheEvict(allEntries = true)
    public Result delete(@RequestBody String[] ids){
        recommendService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }
}
