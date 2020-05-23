package cn.prosayj.blog.manage.sys.service.impl;


import cn.prosayj.blog.core.dao.PageUtils;
import cn.prosayj.blog.core.dao.Query;
import cn.prosayj.blog.core.dao.entity.sys.SysParam;
import cn.prosayj.blog.core.dao.mapper.sys.SysParamMapper;
import cn.prosayj.blog.manage.sys.service.SysParamService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 系统参数 服务实现类
 * </p>
 *
 */
@Service
@Slf4j
public class SysParamServiceImpl extends ServiceImpl<SysParamMapper, SysParam> implements SysParamService {

    /**
     * 分页查询
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String menuUrl = (String) params.get("menuUrl");
        String type = (String) params.get("type");
        IPage<SysParam> page=baseMapper.selectPage(new Query<SysParam>(params).getPage(),
                new QueryWrapper<SysParam>().lambda()
                        .eq(StringUtils.isNotBlank(menuUrl), SysParam::getMenuUrl,menuUrl)
                        .like(StringUtils.isNotBlank(type), SysParam::getType,type));
        return new PageUtils(page);
    }

}
