package cn.prosayj.blog.core.common.constants.enums;

/**
 * 文件类型枚举
 *
 * @author yangjian@bubi.cn
 * @date 2020-05-24 下午 04:45
 * @since 1.0.0
 */
public enum FileEnum {
    /**
     * 图片
     */
    IMG((byte) 0, "图片");

    private Byte fileType;
    private String fileTypeDes;

    FileEnum(Byte fileType, String fileTypeDes) {
        this.fileType = fileType;
        this.fileTypeDes = fileTypeDes;
    }

    public Byte getFileType() {
        return fileType;
    }

    public String getFileTypeDes() {
        return fileTypeDes;
    }
}
