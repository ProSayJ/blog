package cn.prosayj.blog.manage.sys.service;


import cn.prosayj.blog.core.dao.domain.sys.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 */
@Service
public interface SysRoleMenuService extends IService<SysRoleMenu> {


    void saveOrUpdate(Integer roleId, List<Integer> menuIdList);

    List<Integer> queryMenuIdList(Integer roleId);

    void deleteBatchByRoleId(Integer[] roleIds);
}
