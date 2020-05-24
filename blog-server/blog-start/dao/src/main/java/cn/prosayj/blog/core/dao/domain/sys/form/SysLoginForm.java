package cn.prosayj.blog.core.dao.domain.sys.form;

import lombok.Data;

/**
 * SysLoginForm
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@Data
public class SysLoginForm {
    private String username;
    private String password;
    private String captcha;
    private String uuid;
}
