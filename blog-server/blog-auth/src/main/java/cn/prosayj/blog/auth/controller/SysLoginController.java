package cn.prosayj.blog.auth.controller;

import cn.prosayj.blog.auth.service.SysCaptchaService;
import cn.prosayj.blog.auth.service.SysUserTokenService;
import cn.prosayj.blog.core.common.Result;
import cn.prosayj.blog.core.common.constants.enums.ErrorEnum;
import cn.prosayj.blog.core.dao.base.AbstractController;
import cn.prosayj.blog.core.dao.domain.sys.SysUser;
import cn.prosayj.blog.core.dao.domain.sys.form.SysLoginForm;
import cn.prosayj.blog.core.dao.mapper.sys.SysUserMapper;
import cn.prosayj.blog.core.util.RedisUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * SysLoginController
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sys")
public class SysLoginController extends AbstractController {
    @Autowired
    private SysCaptchaService sysCaptchaService;
    @Autowired
    private SysUserTokenService sysUserTokenService;

    @Autowired
    private SysUserMapper sysUserMapper;


    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/get/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response, String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //获取图片验证码
        //sysCaptchaService.getCaptcha(response, uuid);
        sysCaptchaService.getCaptcha(request, response, uuid);
    }

    @PostMapping("/admin/login")
    public Result login(@RequestBody SysLoginForm form) {
        boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
        if (!captcha) {
            // 验证码不正确
            return Result.error(ErrorEnum.CAPTCHA_WRONG);
        }
        // 用户信息
        SysUser user = sysUserMapper.selectOne(new QueryWrapper<SysUser>()
                .lambda()
                .eq(SysUser::getUsername, form.getUsername()));
        if (user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
            // 用户名或密码错误
            return Result.error(ErrorEnum.USERNAME_OR_PASSWORD_WRONG);
        }
        if (user.getStatus() == 0) {
            return Result.error("账号已被锁定，请联系管理员");
        }
        //生成token，并保存到redis
        return sysUserTokenService.createToken(user.getUserId());
    }

    /**
     * 退出
     */
    @PostMapping("/admin/logout")
    public Result logout() {
        sysUserTokenService.logout(getUserId());
        return Result.ok();
    }
}
