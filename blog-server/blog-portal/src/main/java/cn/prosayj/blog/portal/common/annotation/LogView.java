package cn.prosayj.blog.portal.common.annotation;

import java.lang.annotation.*;

/**
 * LogView
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogView {

     String type();
}
