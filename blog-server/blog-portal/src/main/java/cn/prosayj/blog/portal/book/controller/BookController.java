package cn.prosayj.blog.portal.book.controller;


import cn.prosayj.blog.core.common.Result;
import cn.prosayj.blog.core.common.constants.constant.RedisCacheNames;
import cn.prosayj.blog.core.dao.PageUtils;
import cn.prosayj.blog.core.dao.entity.book.vo.BookVO;
import cn.prosayj.blog.portal.book.service.BookService;
import cn.prosayj.blog.portal.common.annotation.LogLike;
import cn.prosayj.blog.portal.common.annotation.LogView;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 文章 前端控制器
 * </p>
 *
 */
@RestController("bookPortalController")
@CacheConfig(cacheNames = {RedisCacheNames.BOOK})
public class BookController {

    @Resource
    private BookService bookService;

    @GetMapping("/book/{bookId}")
    @LogView(type = "book")
    public Result getBook(@PathVariable Integer bookId){
        BookVO book = bookService.getBookVo(bookId);
        return Result.ok().put("book",book);
    }

    @GetMapping("/books")
    @Cacheable
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = bookService.queryPageCondition(params);
        return Result.ok().put("page",page);
    }

    @PutMapping("/book/like/{id}")
    @LogLike(type = "book")
    public Result likeBook(@PathVariable Integer id) {
        return Result.ok();
    }


}
