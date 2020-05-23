package cn.prosayj.blog.portal.timeline.service;


import cn.prosayj.blog.core.dao.entity.timeline.Timeline;

import java.util.List;

/**
 * TimelineService
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
public interface TimelineService {

    /**
     * 获取timeLine数据
     * @return
     */
    List<Timeline> listTimeLine();
}
