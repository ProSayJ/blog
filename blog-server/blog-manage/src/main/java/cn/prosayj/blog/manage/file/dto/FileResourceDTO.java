package cn.prosayj.blog.manage.file.dto;

import java.util.Date;

public class FileResourceDTO {
    private Long id;

    private Long userId;

    private Long articleId;

    private String fileName;

    private String fileSuffix;

    private String fileDbUrl;

    private String fileStaticUrl;

    private Date createDate;

    private Date updateDate;

    private Byte isDelete;

    private byte[] fileSource;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getFileDbUrl() {
        return fileDbUrl;
    }

    public void setFileDbUrl(String fileDbUrl) {
        this.fileDbUrl = fileDbUrl;
    }

    public String getFileStaticUrl() {
        return fileStaticUrl;
    }

    public void setFileStaticUrl(String fileStaticUrl) {
        this.fileStaticUrl = fileStaticUrl;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public byte[] getFileSource() {
        return fileSource;
    }

    public void setFileSource(byte[] fileSource) {
        this.fileSource = fileSource;
    }
}