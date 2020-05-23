package cn.prosayj.blog.portal.book.service;

import cn.prosayj.blog.core.dao.PageUtils;
import cn.prosayj.blog.core.dao.entity.book.Book;
import cn.prosayj.blog.core.dao.entity.book.vo.BookVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;


/**
 * <p>
 * 图书表 服务类
 * </p>
 *
 */
public interface BookService extends IService<Book> {

    /**
     * 分页分类获取列表
     * @param params
     * @return
     */
    PageUtils queryPageCondition(Map<String, Object> params);

    /**
     * 获取Book对象
     * @param id
     * @return
     */
    BookVO getBookVo(Integer id);
}
