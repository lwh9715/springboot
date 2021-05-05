package com.spring.springutil.entity;

/**
 * @描述 :
 * @Author : 梁文辉
 * @Date: 2021-05-02 11:25
 */
public class NidInfo {

    private String account;
    private String endEffectiveDate;
    private String fileType;
    private String imageType;
    private String imageGroup;
    private String fileName;
    private String nidNbr;
    private String authorizedLetterSignedDate;
    private String dataSource;
    private String imageInfo;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEndEffectiveDate() {
        return endEffectiveDate;
    }

    public void setEndEffectiveDate(String endEffectiveDate) {
        this.endEffectiveDate = endEffectiveDate;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImageGroup() {
        return imageGroup;
    }

    public void setImageGroup(String imageGroup) {
        this.imageGroup = imageGroup;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getNidNbr() {
        return nidNbr;
    }

    public void setNidNbr(String nidNbr) {
        this.nidNbr = nidNbr;
    }

    public String getAuthorizedLetterSignedDate() {
        return authorizedLetterSignedDate;
    }

    public void setAuthorizedLetterSignedDate(String authorizedLetterSignedDate) {
        this.authorizedLetterSignedDate = authorizedLetterSignedDate;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getImageInfo() {
        return imageInfo;
    }

    public void setImageInfo(String imageInfo) {
        this.imageInfo = imageInfo;
    }
}
