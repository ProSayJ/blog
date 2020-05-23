package cn.prosayj.blog.core.common.constants.constant;

/**
 * 图形验证码常量类
 *
 * @author yangjian@bubi.cn
 * @date 2020-05-23 下午 11:40
 * @since 1.0.0
 */
public class CaptchaConstant {
    /**
     *
     */
    public static final String SESSION_KEY = "captcha";

    /**
     * 验证码过期时长5秒
     */
    public final static long CAPTCHA_EXPIRE = 60 * 5;
}
