package com.example.demo.entity;

public class ResponseWrapper<T> {

    private boolean status;
    private String message;
    private int totalSize;
    private String faultReason;
    private Error error;
    private T content;

    public ResponseWrapper() {
    }
    public ResponseWrapper(boolean status, T content) {
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
    public ResponseWrapper(boolean status, String message, T content) {
        this.status = status;
        this.message = message;
        this.content = content;
    }

    public ResponseWrapper(boolean b, String listFound, T content, int totalSize) {
        this.status = b;
        this.message = listFound;
        this.content = content;
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

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
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
