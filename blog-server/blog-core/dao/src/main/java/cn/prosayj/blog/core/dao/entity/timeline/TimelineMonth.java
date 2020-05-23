package cn.prosayj.blog.core.dao.entity.timeline;

import lombok.Data;

import java.util.List;

/**
 * TimelineMonth
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@Data
public class TimelineMonth {

    private Integer month;

    private Integer count;

    private List<TimelinePost> posts;

}
