package cn.prosayj.blog.core.common.constants.enums;

/**
 * TODO
 *
 * @author yangjian@bubi.cn
 * @date 2020-05-24 下午 05:38
 * @since 1.0.0
 */
public enum IsDeleted {
    /**
     * 未删除
     */
    UN_DELETED((byte) 0, "未删除"),
    /**
     * 已删除
     */
    DELETED((byte) 1, "已删除");
    private Byte code;
    private String des;


    IsDeleted(Byte code, String des) {
        this.code = code;
        this.des = des;
    }

    public Byte getCode() {
        return code;
    }

    public String getDes() {
        return des;
    }
}
