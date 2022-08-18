package com.example.disney.exception;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;

public class ErrorInfo {
    private final String url;
    private final String message;
    private final String timestamp;

    public ErrorInfo(String url, String message, String timestamp) {
        this.url = url;
        this.message = message;
        this.timestamp = timestamp;
    }

    public ErrorInfo(HttpServletRequest request, Exception ex, String timestamp) {
        this.url = request.getRequestURL().toString();
        this.message = ex.getMessage();
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

}
