package cn.prosayj.blog.core.util.easycaptchautil;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

import static cn.prosayj.blog.core.common.constants.constant.CaptchaConstant.SESSION_KEY;


/**
 * 图形验证码工具类
 *
 * @author yangjian@bubi.cn
 * @date 2020-02-22 下午 04:41
 * @since 1.0.0
 */
public class CaptchaUtil {

    /**
     * 验证验证码
     *
     * @param code    用户输入的验证码
     * @param request HttpServletRequest
     * @return 是否正确
     */
    public static boolean ver(String code, HttpServletRequest request) {
        if (code != null && !code.trim().isEmpty()) {
            String captcha = (String) request.getSession().getAttribute(SESSION_KEY);
            return code.trim().toLowerCase().equals(captcha);
        }
        return false;
    }

    /**
     * 输出验证码
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException IO异常
     */
    public static void out(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        out(5, request, response);
    }

    /**
     * 输出验证码
     *
     * @param len      长度
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException IO异常
     */
    public static void out(int len, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        out(130, 48, len, request, response);
    }

    /**
     * 输出验证码
     *
     * @param len      长度
     * @param font     字体
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException IO异常
     */
    public static void out(int len, Font font, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        out(130, 48, len, font, request, response);
    }

    /**
     * 输出验证码
     *
     * @param width    宽度
     * @param height   高度
     * @param len      长度
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException IO异常
     */
    public static void out(int width, int height, int len, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        out(width, height, len, null, request, response);
    }

    /**
     * 输出验证码
     *
     * @param width    宽度
     * @param height   高度
     * @param len      长度
     * @param font     字体
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException IO异常
     */
    public static void out(int width, int height, int len, Font font, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        outCaptcha(width, height, len, font, 1, request, response);
    }

    /**
     * 输出验证码
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException IO异常
     */
    public static void outPng(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        outPng(5, request, response);
    }

    /**
     * 输出验证码
     *
     * @param len      长度
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException IO异常
     */
    public static void outPng(int len, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        outPng(130, 48, len, request, response);
    }

    /**
     * 输出验证码
     *
     * @param len      长度
     * @param font     字体
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException IO异常
     */
    public static void outPng(int len, Font font, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        outPng(130, 48, len, font, request, response);
    }

    /**
     * 输出验证码
     *
     * @param width    宽度
     * @param height   高度
     * @param len      长度
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException IO异常
     */
    public static void outPng(int width, int height, int len, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        outPng(width, height, len, null, request, response);
    }

    /**
     * 输出验证码
     *
     * @param width    宽度
     * @param height   高度
     * @param len      长度
     * @param font     字体
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException IO异常
     */
    public static void outPng(int width, int height, int len, Font font, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        outCaptcha(width, height, len, font, 0, request, response);
    }

    /**
     * 输出验证码
     *
     * @param width    宽度
     * @param height   高度
     * @param len      长度
     * @param font     字体
     * @param cType    类型
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException IO异常
     */
    private static void outCaptcha(int width, int height, int len, Font font, int cType, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        setHeader(response);
        Captcha captcha = null;
        if (cType == 0) {
            captcha = new SpecCaptcha(width, height, len);
        } else if (cType == 1) {
            captcha = new GifCaptcha(width, height, len);
        }
        if (font != null) {
            assert captcha != null;
            captcha.setFont(font);
        }
        assert captcha != null;
        request.getSession().setAttribute(SESSION_KEY, captcha.text().toLowerCase());
        captcha.out(response.getOutputStream());
    }

    /**
     * 清除session中的验证码
     *
     * @param request HttpServletRequest
     */
    public static void clear(HttpServletRequest request) {
        request.getSession().removeAttribute(SESSION_KEY);
    }

    /**
     * 设置相应头
     *
     * @param response HttpServletResponse
     */
    public static void setHeader(HttpServletResponse response) {
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    }

}
