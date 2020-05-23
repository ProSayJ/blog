package cn.prosayj.blog.core.util.notice.mq.annotation;

import java.lang.annotation.*;

/**
 * RefreshEsMqSender
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RefreshEsMqSender {
    String sender();

    String msg() default "send refresh msg to ElasticSearch";

}
