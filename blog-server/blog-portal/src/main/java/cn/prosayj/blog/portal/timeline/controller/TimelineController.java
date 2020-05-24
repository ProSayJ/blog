package cn.prosayj.blog.portal.timeline.controller;

import cn.prosayj.blog.core.common.Result;
import cn.prosayj.blog.core.dao.domain.timeline.Timeline;
import cn.prosayj.blog.portal.timeline.service.TimelineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * TimelineController
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@RestController
@RequestMapping("/timeline")
public class TimelineController {

    @Resource
    private TimelineService timelineService;

    @GetMapping("")
    public Result listTimeline() {
        List<Timeline> timelineList = timelineService.listTimeLine();

        return Result.ok().put("timelineList",timelineList);
    }
}
