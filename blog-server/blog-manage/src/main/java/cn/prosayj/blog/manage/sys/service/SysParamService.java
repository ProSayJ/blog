package cn.prosayj.blog.manage.sys.service;

import cn.prosayj.blog.core.dao.PageUtils;
import cn.prosayj.blog.core.dao.domain.sys.SysParam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 系统参数 服务类
 * </p>
 *
 */
public interface SysParamService extends IService<SysParam> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);
}
