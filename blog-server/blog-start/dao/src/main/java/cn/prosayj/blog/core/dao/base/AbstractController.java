package cn.prosayj.blog.core.dao.base;

import cn.prosayj.blog.core.dao.domain.sys.SysUser;
import org.apache.shiro.SecurityUtils;

/**
 * AbstractController
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
public class AbstractController {

    protected SysUser getUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    protected Integer getUserId() {
        return getUser().getUserId();
    }
}
