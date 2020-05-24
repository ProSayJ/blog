package cn.prosayj.blog.portal.book.service.impl;

import cn.prosayj.blog.core.dao.PageUtils;
import cn.prosayj.blog.core.dao.Query;
import cn.prosayj.blog.core.dao.domain.book.Book;
import cn.prosayj.blog.core.dao.domain.book.vo.BookVO;
import cn.prosayj.blog.core.dao.mapper.book.BookMapper;
import cn.prosayj.blog.portal.book.service.BookService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 图书表 服务实现类
 * </p>
 *
 */
@Service("bookPortalService")
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {


    /**
     * 分页分类获取列表
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPageCondition(Map<String, Object> params) {
        Page<BookVO> page = new Query<BookVO>(params).getPage();
        List<BookVO> bookList = baseMapper.queryPageCondition(page, params);
        page.setRecords(bookList);
        return new PageUtils(page);
    }

    /**
     * 获取Book对象
     *
     * @param id
     * @return
     */
    @Override
    public BookVO getBookVo(Integer id) {
        return this.baseMapper.selectByIdWithSubList(id);
    }
}
