package cn.prosayj.blog.portal.operation.service;


import cn.prosayj.blog.core.dao.domain.operation.Link;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * LinkService
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
public interface LinkService extends IService<Link> {

    /**
     * 获取link列表
     * @return
     */
    List<Link> listLink();
}
