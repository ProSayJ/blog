package cn.prosayj.blog.portal.common.annotation;

import java.lang.annotation.*;

/**
 * LogLike
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogLike {

     String type();
}
