package com.patagonia.web.entity;



import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "log")
public class LogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_seq")
    @SequenceGenerator(name = "log_seq", sequenceName = "log_seq", allocationSize = 1)
    private Long id;
    @Column(name = "message")
    private String message;
    @Column(name = "log_level")
    private  String logLevel;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "evnt_type")
    private  String eventType;

    public LogModel() {
    }
    public LogModel(String message, String logLevel, Date createdAt, String eventType) {
        this.message = message;
        this.logLevel = logLevel;
        this.createdAt = createdAt;
        this.eventType = eventType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "LogModel{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", logLevel='" + logLevel + '\'' +
                ", createdAt=" + createdAt +
                ", eventType='" + eventType + '\'' +
                '}';
    }
}
