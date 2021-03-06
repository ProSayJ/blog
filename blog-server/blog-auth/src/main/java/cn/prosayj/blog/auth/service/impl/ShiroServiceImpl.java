package cn.prosayj.blog.auth.service.impl;


import cn.prosayj.blog.core.dao.domain.sys.SysUser;
import cn.prosayj.blog.auth.oauth2.SysUserToken;
import cn.prosayj.blog.auth.service.ShiroService;
import cn.prosayj.blog.auth.service.SysUserTokenService;
import cn.prosayj.blog.core.common.constants.constant.RedisKeyConstants;
import cn.prosayj.blog.core.common.constants.constant.SysConstants;
import cn.prosayj.blog.core.dao.domain.sys.SysMenu;
import cn.prosayj.blog.core.dao.mapper.sys.SysMenuMapper;
import cn.prosayj.blog.core.dao.mapper.sys.SysUserMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ShiroServiceImpl
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysUserTokenService sysUserTokenService;

    /**
     * 获取用户的所有权限
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> getUserPermissions(Integer userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if (SysConstants.SUPER_ADMIN.equals(userId)) {
            List<SysMenu> menuList = sysMenuMapper.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            menuList.forEach(menu -> permsList.add(menu.getPerms()));
        } else {
            permsList = sysUserMapper.queryAllPerms(userId);
        }
        //返回用户权限列表
        return permsList.stream()
                // 过滤空置的字符串
                .filter(perms -> !StringUtils.isEmpty(perms))
                // 把小的list合并成大的list
                .flatMap(perms -> Arrays.stream(perms.split(",")))
                // 转换成set集合
                .collect(Collectors.toSet());
    }

    /**
     * 查询token
     *
     * @param token
     * @return
     */
    @Override
    public SysUserToken queryByToken(String token) {
        return sysUserTokenService.queryByToken(RedisKeyConstants.MANAGE_SYS_USER_TOKEN + token);
    }

    /**
     * 查询用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public SysUser queryUser(Integer userId) {
        return sysUserMapper.selectById(userId);
    }

    /**
     * 续期
     *
     * @param userId
     * @param accessToken
     */
    @Override
    public void refreshToken(Integer userId, String accessToken) {
        sysUserTokenService.refreshToken(userId, accessToken);
    }
}
