package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {
    private Integer fileId;
    private String fileName;
    private String contentType;
    private String fileSize;
    private Integer userId;
    private byte[] fileData;

    public File(Integer fileId, String fileName, String contentType, String fileSize, Integer userId, byte[] fileData) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.userId = userId;
        this.fileData = fileData;
    }

    public Integer getFileId() {
        return fileId;
    }

    public File setFileId(Integer fileId) {
        this.fileId = fileId;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public File setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public File setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public String getFileSize() {
        return fileSize;
    }

    public File setFileSize(String fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public File setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public File setFileData(byte[] fileData) {
        this.fileData = fileData;
        return this;
    }
}
