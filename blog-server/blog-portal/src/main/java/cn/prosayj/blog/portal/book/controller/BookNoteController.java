package cn.prosayj.blog.portal.book.controller;

import cn.prosayj.blog.core.common.Result;
import cn.prosayj.blog.core.common.constants.constant.RedisCacheNames;
import cn.prosayj.blog.core.dao.PageUtils;
import cn.prosayj.blog.core.dao.domain.book.BookNote;
import cn.prosayj.blog.portal.common.annotation.LogLike;
import cn.prosayj.blog.portal.common.annotation.LogView;
import cn.prosayj.blog.portal.book.service.BookNoteService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


/**
 * BookNoteController
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@RestController("bookNotePortalController")
@CacheConfig(cacheNames = {RedisCacheNames.BOOKNOTE})
public class BookNoteController {

    @Resource
    private BookNoteService bookNoteService;
   

    @GetMapping("/bookNote/{bookNoteId}")
    @LogView(type = "bookNote")
    public Result getBookNote(@PathVariable Integer bookNoteId){
        BookNote bookNote = bookNoteService.getById(bookNoteId);
        return Result.ok().put("bookNote",bookNote);
    }

    @GetMapping("/bookNotes")
    @Cacheable
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = bookNoteService.queryPageCondition(params);
        return Result.ok().put("page",page);
    }

    @PutMapping("/bookNote/like/{id}")
    @LogLike(type = "bookNote")
    public Result likeBookNote(@PathVariable Integer id) {
        return Result.ok();
    }


}
