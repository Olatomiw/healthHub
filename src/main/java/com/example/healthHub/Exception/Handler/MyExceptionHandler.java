package com.example.healthHub.Exception.Handler;

import com.example.healthHub.Exception.ApiExceptionRequest;
import com.example.healthHub.Exception.ExceptionResponse;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
@ComponentScan
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler (ApiExceptionRequest.class)
    public ResponseEntity<ExceptionResponse> apiExceptionHandler(ApiExceptionRequest apiExceptionRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                apiExceptionRequest.getMessage(),
                HttpStatus.BAD_REQUEST,
                Instant.now()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
