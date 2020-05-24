package cn.prosayj.blog.manage.operation.service;

import cn.prosayj.blog.core.dao.PageUtils;
import cn.prosayj.blog.core.dao.domain.operation.Link;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 友链 服务类
 * </p>
 *
 */
public interface LinkService extends IService<Link> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);
}
