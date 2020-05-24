package cn.prosayj.blog.core.common.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * IDvo
 *
 * @author yangjian@bubi.cn
 * @date 2020-05-10 下午 09:21
 * @since 1.0.0
 */
public class IdVO {
    @ApiModelProperty(value = "主键Id", required = false)
    private Long id;
    @ApiModelProperty(value = "业务主键Id", required = false)
    private Long businessId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    @Override
    public String toString() {
        return "IdVO{" +
                "id=" + id +
                ", businessId=" + businessId +
                '}';
    }
}
