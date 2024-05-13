package com.example.healthHub.Exception;

public class ApiExceptionRequest extends RuntimeException{
    public ApiExceptionRequest(String message) {
        super(message);
    }

}
