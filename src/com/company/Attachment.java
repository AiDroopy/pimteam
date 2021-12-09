package com.company;

import java.sql.Timestamp;
import java.time.Instant;


public class Attachment {
    private int id;
    private String header;
    private String fileUrl;
    private int userId;
    private Timestamp timestamp;

    public Attachment() {
    }

    public Attachment(String fileUrl, int userId, String header) {
        this.header = header;
        this.fileUrl = fileUrl;
        this.userId = userId;
        this.timestamp = Timestamp.from(Instant.now());
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", userId=" + userId +
                ", timestamp=" + timestamp +
                '}';
    }
}
