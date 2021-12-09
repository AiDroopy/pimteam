package com.company;

import java.sql.Timestamp;


public class Attachment {
    private int id;
    private String fileUrl;
    private String userId;
    private Timestamp timestamp;

    public Attachment() {
    }

    public int getId() {
        return id;
    }


    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Files{" +
                "id=" + id +
                ", fileUrl='" + fileUrl + '\'' +
                ", userId='" + userId + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
