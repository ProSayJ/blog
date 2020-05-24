package cn.prosayj.blog.core.dao.domain.timeline;

import lombok.Data;

import java.util.List;

/**
 * Timeline
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@Data
public class Timeline {

    private Integer year;

    private Integer count;

    private List<TimelineMonth> months;
}
