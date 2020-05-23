package cn.prosayj.blog.core.dao.fill;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * MybatisPlus 自动填充处理类
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@Component
public class MybatisPlusAutoFillHandler implements MetaObjectHandler {
    /**
     * 插入时填充
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 填充创建时间 更新时间字段
        Date now = new Date();
        this.setFieldValByName("createTime", now, metaObject);
        this.setFieldValByName("updateTime", now, metaObject);
    }

    /**
     * 更新时填充
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 填充给更新时间字段
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
