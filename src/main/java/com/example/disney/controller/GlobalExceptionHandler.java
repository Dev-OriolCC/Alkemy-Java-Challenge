package com.example.disney.controller;

import com.example.disney.exception.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Bad request
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public ErrorInfo handleMethodNotSupported(HttpServletRequest request,
        HttpServletResponse httpServletResponse, Exception ex) {
        return new ErrorInfo(request, ex, LocalTime.now().toString());
    }

    // Internal error
    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorInfo handleIOException(HttpServletRequest request,
            HttpServletResponse response, Exception ex) {
        return new ErrorInfo(request, ex, LocalTime.now().toString());
    }

}
