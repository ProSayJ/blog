package cn.prosayj.blog.core.dao.entity.book.vo;

import cn.prosayj.blog.core.dao.entity.book.Book;
import cn.prosayj.blog.core.dao.entity.book.BookNote;
import cn.prosayj.blog.core.dao.entity.book.BookSense;
import cn.prosayj.blog.core.dao.entity.operation.Tag;
import lombok.Data;

import java.util.List;

/**
 * BookVO
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@Data
public class BookVO extends Book {

    /**
     * 所属分类，以逗号分隔
     */
    private String categoryListStr;

    /**
     * 所属标签
     */
    private List<Tag> tagList;

    /**
     * 所属笔记
     */
    private List<BookNote> bookNoteList;

    /**
     * 读后感
     */
    private BookSense bookSense;
}
