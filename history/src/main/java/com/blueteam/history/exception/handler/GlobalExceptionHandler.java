package com.blueteam.history.exception.handler;

import com.blueteam.history.dto.ApiError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ApiError handleException(Exception e){
        return new ApiError(400, e.getMessage());
    }
}
