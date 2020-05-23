package cn.prosayj.blog.core.dao.entity.book.dto;

import cn.prosayj.blog.core.dao.entity.operation.Tag;
import cn.prosayj.blog.core.dao.entity.book.Book;
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
