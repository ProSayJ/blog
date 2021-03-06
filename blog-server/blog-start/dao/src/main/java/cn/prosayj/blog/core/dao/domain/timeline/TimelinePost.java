package cn.prosayj.blog.core.dao.domain.timeline;

import lombok.Data;

import java.util.Date;

/**
 * TimelinePost
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@Data
public class TimelinePost {

    private Integer id;

    private String title;

    private String description;

    private String postType;

    private Date createTime;

}
