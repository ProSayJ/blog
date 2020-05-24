package cn.prosayj.blog.core.dao.domain.book.vo;

import cn.prosayj.blog.core.dao.domain.book.BookNote;
import cn.prosayj.blog.core.dao.domain.operation.Tag;
import cn.prosayj.blog.core.dao.domain.book.Book;
import lombok.Data;

import java.util.List;

/**
 * BookNoteVO
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@Data
public class BookNoteVO extends BookNote {

    /**
     * 所属分类，以逗号分隔
     */
    private String categoryListStr;

    /**
     * 所属标签
     */
    private List<Tag> tagList;

    /**
     * 所属书本
     */
    private Book book;

}
