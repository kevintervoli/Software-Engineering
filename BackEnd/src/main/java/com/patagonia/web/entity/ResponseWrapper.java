package com.patagonia.web.entity;

import java.util.List;


public class ResponseWrapper<T> {

    private boolean status;
    private String message;
    private int totalSize;
    private String faultReason;
    private Error error;
    private List<?> content;

    public ResponseWrapper() {
    }

    public ResponseWrapper(boolean status, List<?> content) {
        this.status = status;
        this.content = content;
    }

    public ResponseWrapper(boolean status, String faultReason, Error error) {
        this.status = status;
        this.faultReason = faultReason;
    }

    public ResponseWrapper(boolean status, String message) {
        this.status = status;
        this.message = message;

    }
    public ResponseWrapper(boolean status, String message, List<?> content) {
        this.status = status;
        this.message = message;
        this.content = content;
    }
    public ResponseWrapper(boolean b, Object s, List<User> users) {
        this.status = b;
        this.message = (String) s;
        this.content = (List<?>) users;
    }

    public <T> ResponseWrapper(boolean b, String listFound, List<?> content, int totalSize) {
        this.status = b;
        this.message = listFound;
        this.content = (List<?>) content;
        this.totalSize = totalSize;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFaultReason() {
        return faultReason;
    }

    public void setFaultReason(String faultReason) {
        this.faultReason = faultReason;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ResponseWrapper{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", faultReason='" + faultReason + '\'' +
                ", error=" + error +
                ", content=" + content +
                '}';
    }
}
