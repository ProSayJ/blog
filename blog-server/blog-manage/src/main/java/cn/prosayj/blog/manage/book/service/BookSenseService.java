package cn.prosayj.blog.manage.book.service;

import cn.prosayj.blog.core.dao.domain.book.BookSense;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 读后感 服务类
 * </p>
 *
 * @since 2019-02-13
 */
public interface BookSenseService extends IService<BookSense> {

    /**
     * 获取读后感
     * @param bookId
     * @return
     */
    BookSense getBookSense(Integer bookId);
}
