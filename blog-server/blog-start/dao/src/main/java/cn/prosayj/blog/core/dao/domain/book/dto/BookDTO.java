package cn.prosayj.blog.core.dao.domain.book.dto;

import cn.prosayj.blog.core.dao.domain.operation.Tag;
import cn.prosayj.blog.core.dao.domain.book.Book;
import lombok.Data;

import java.util.List;

/**
 * BookDTO
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@Data
public class BookDTO extends Book {

    List<Tag> tagList;
}
