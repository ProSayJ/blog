package cn.prosayj.blog.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取上下文Bean实例
 *
 * @author prosayj@gmail.com
 * @date 2019-12-05 上午 09:42
 * @since 1.0.0
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    /**
     * 上下文对象实例
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (ApplicationContextProvider.applicationContext == null) {
            ApplicationContextProvider.applicationContext = applicationContext;
        }
    }

    /**
     * 获取applicationContext
     *
     * @return applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name name
     * @return bean
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }


    /**
     * 通过clazz返回指定的Class
     *
     * @param clazz class
     * @return T
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name  name
     * @param clazz class
     * @return T
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}