package cn.prosayj.blog.core.dao.mapper.timeline;

import cn.prosayj.blog.core.dao.entity.timeline.Timeline;
import cn.prosayj.blog.core.dao.entity.timeline.TimelinePost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TimelineMapper
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@Mapper
public interface TimelineMapper {

    List<TimelinePost> listTimelinePost(@Param("year") Integer year, @Param("month") Integer month);

    List<Timeline> listTimeline();
}
