package cn.prosayj.blog.portal.book.service.impl;

import cn.prosayj.blog.core.dao.PageUtils;
import cn.prosayj.blog.core.dao.Query;
import cn.prosayj.blog.core.dao.domain.book.BookNote;
import cn.prosayj.blog.core.dao.domain.book.vo.BookNoteVO;
import cn.prosayj.blog.core.dao.mapper.book.BookNoteMapper;
import cn.prosayj.blog.portal.book.service.BookNoteService;
import cn.prosayj.blog.portal.book.service.BookService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * BookNoteServiceImpl
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@Service("BookNotePortalService")
public class BookNoteServiceImpl extends ServiceImpl<BookNoteMapper, BookNote> implements BookNoteService {

    @Resource
    private BookService bookService;


    /**
     * 分页分类获取列表
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPageCondition(Map<String, Object> params) {
        Page<BookNoteVO> page = new Query<BookNoteVO>(params).getPage();
        List<BookNoteVO> bookNoteList = baseMapper.queryPageCondition(page, params);
        page.setRecords(bookNoteList);
        return new PageUtils(page);
    }

    /**
     * 获取简单对象
     *
     * @param bookNoteId
     * @return
     */
    @Override
    public BookNoteVO getSimpleBookNoteVo(Integer bookNoteId) {
        BookNoteVO bookNoteVo = baseMapper.getSimpleBookNoteVo(bookNoteId);
        bookNoteVo.setBook(bookService.getBookVo(bookNoteVo.getBookId()));
        return bookNoteVo;
    }

    /**
     * 获取简单List
     *
     * @param bookId
     * @return
     */
    @Override
    public List<BookNote> listSimpleBookNote(Integer bookId) {
        return baseMapper.listSimpleBookNote(bookId);
    }


}
