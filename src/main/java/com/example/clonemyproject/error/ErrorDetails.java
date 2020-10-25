package com.example.clonemyproject.error;

import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.util.Date;

public class ErrorDetails {
    private LocalDateTime date;
    private String message;
    private String details;

    public ErrorDetails() {
    }

    public ErrorDetails(LocalDateTime date, String message, String details) {
        this.date = date;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
