package com.example.healthHub.Exception;

import org.springframework.http.HttpStatus;

import java.sql.Date;
import java.time.Instant;
import java.time.ZonedDateTime;


public class ExceptionResponse {

    private String message;
    private HttpStatus status;
    private Instant timeStamp;

    public ExceptionResponse(String message, HttpStatus status, Instant timeStamp) {
        this.message = message;
        this.status = status;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
