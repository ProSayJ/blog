package cn.prosayj.blog.core.dao.mapper.sys;

import cn.prosayj.blog.core.dao.domain.sys.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    /**
     * 根据roleId查询所属menuId
     * @param roleId
     * @return
     */
    List<Integer> queryMenuIdList(Integer roleId);
}
