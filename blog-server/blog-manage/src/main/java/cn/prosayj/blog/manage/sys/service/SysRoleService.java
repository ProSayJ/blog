package cn.prosayj.blog.manage.sys.service;


import cn.prosayj.blog.core.dao.PageUtils;
import cn.prosayj.blog.core.dao.domain.sys.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * SysRoleService
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 分页查询角色
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 批量删除
     * @param roleIds
     */
    void deleteBatch(Integer[] roleIds);

    /**
     * 查询roleId
     * @param createUserId
     * @return
     */
    List<Integer> queryRoleIdList(Integer createUserId);
}
