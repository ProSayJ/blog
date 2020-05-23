package cn.prosayj.blog.auth.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码类
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
public interface SysCaptchaService {

    /**
     * 获取验证码
     *
     *
     * @param response
     * @param uuid
     * @return
     */
    BufferedImage getCaptcha(HttpServletResponse response, String uuid) throws IOException;


    /**
     * 获取验证码并输出
     *
     * @param uuid
     * @return
     */
    void getCaptcha(HttpServletRequest request, HttpServletResponse response, String uuid) throws IOException;

    /**
     * 验证验证码
     *
     * @param uuid
     * @param code
     * @return
     */
    boolean validate(String uuid, String code);
}
