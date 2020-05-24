package cn.prosayj.blog.manage.book.controller;

import cn.prosayj.blog.core.common.Result;
import cn.prosayj.blog.core.dao.base.AbstractController;
import cn.prosayj.blog.core.common.constants.constant.RedisCacheNames;
import cn.prosayj.blog.core.dao.domain.book.BookSense;
import cn.prosayj.blog.manage.book.service.BookSenseService;
import cn.prosayj.blog.core.dao.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 读后感 前端控制器
 * </p>
 *
 */
@RestController
@CacheConfig(cacheNames ={RedisCacheNames.RECOMMEND,RedisCacheNames.TAG,RedisCacheNames.BOOK})
@RequestMapping("/admin/book/sense")
public class BookSenseController extends AbstractController {

    @Resource
    private BookSenseService bookSenseService;

    @GetMapping("/{bookId}")
    @RequiresPermissions("book:info")
    public Result getReadSense(@PathVariable Integer bookId) {
        BookSense bookSense = bookSenseService.getBookSense(bookId);
        return Result.ok().put("bookSense",bookSense);
    }
    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("book:save")
    @CacheEvict(allEntries = true)
    public Result save(@RequestBody BookSense bookSense) {
        ValidatorUtils.validateEntity(bookSense);
        bookSenseService.save(bookSense);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @RequiresPermissions("book:update")
    @CacheEvict(allEntries = true)
    public Result update(@RequestBody BookSense bookSense) {
        ValidatorUtils.validateEntity(bookSense);
        bookSenseService.updateById(bookSense);
        return Result.ok();
    }
}
