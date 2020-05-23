package cn.prosayj.blog.portal.book.service;

import cn.prosayj.blog.core.dao.PageUtils;
import cn.prosayj.blog.core.dao.entity.book.BookNote;
import cn.prosayj.blog.core.dao.entity.book.vo.BookNoteVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * BookNoteService
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
public interface BookNoteService extends IService<BookNote> {

    /**
     * 分页分类获取列表
     * @param params
     * @return
     */
    PageUtils queryPageCondition(Map<String, Object> params);

    /**
     * 获取简单对象
     * @param bookNoteId
     * @return
     */
    BookNoteVO getSimpleBookNoteVo(Integer bookNoteId);

    /**
     * 获取简单List
     * @param bookId
     * @return
     */
    List<BookNote> listSimpleBookNote(Integer bookId);
}
