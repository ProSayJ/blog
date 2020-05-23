package cn.prosayj.blog.auth.service.impl;

import cn.prosayj.blog.auth.service.SysCaptchaService;
import cn.prosayj.blog.core.common.constants.constant.RedisKeyConstants;
import cn.prosayj.blog.core.common.exception.BussinessException;
import cn.prosayj.blog.core.common.constants.enums.ErrorEnum;
import cn.prosayj.blog.core.util.RedisUtils;
import cn.prosayj.blog.core.util.easycaptchautil.CaptchaUtil;
import com.baomidou.mybatisplus.core.toolkit.IOUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static cn.prosayj.blog.core.common.constants.constant.CaptchaConstant.CAPTCHA_EXPIRE;
import static cn.prosayj.blog.core.common.constants.constant.CaptchaConstant.SESSION_KEY;

/**
 * SysCaptchaServiceImpl
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@Service
public class SysCaptchaServiceImpl implements SysCaptchaService {

    /**
     * 图片验证码{@link cn.prosayj.blog.core.config.KaptchaConfig}
     */
    @Autowired
    private Producer producer;

    @Autowired
    private RedisUtils redisUtils;


    /**
     * 获取验证码
     *
     * @param uuid
     * @return
     */
    @Override
    public BufferedImage getCaptcha(HttpServletResponse response, String uuid) throws IOException {
        if (StringUtils.isEmpty(uuid)) {
            throw new BussinessException(ErrorEnum.NO_UUID);
        }
        //生成文字验证码
        String code = producer.createText();
        // 存进redis,5分钟后过期
        redisUtils.set(genRedisKey(uuid), code, CAPTCHA_EXPIRE);
        BufferedImage image = producer.createImage(code);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
        return image;
    }

    @Override
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response, String uuid) throws IOException {
        CaptchaUtil.out(request, response);
        String captcha = String.valueOf(request.getSession().getAttribute(SESSION_KEY));
        // 存进redis,5分钟后过期
        redisUtils.set(RedisKeyConstants.MANAGE_SYS_CAPTCHA + uuid, captcha, CAPTCHA_EXPIRE);
    }

    /**
     * 验证验证码
     *
     * @param uuid
     * @param code
     * @return
     */
    @Override
    public boolean validate(String uuid, String code) {
        if (StringUtils.isEmpty(uuid) || StringUtils.isEmpty(code)) {
            return false;
        }
        // 从redis中取
        String redisKey = genRedisKey(uuid);
        String captchaCode = redisUtils.get(redisKey);
        //删除验证码
        redisUtils.delete(redisKey);
        if (code.equalsIgnoreCase(captchaCode)) {
            return true;
        }
        return false;
    }

    /**
     * 生成redis key
     *
     * @param uuid
     * @return
     */
    private String genRedisKey(String uuid) {
        return RedisKeyConstants.MANAGE_SYS_CAPTCHA + uuid;
    }
}
