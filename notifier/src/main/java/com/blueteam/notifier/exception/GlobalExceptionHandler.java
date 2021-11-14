package com.blueteam.notifier.exception;

import com.blueteam.notifier.exception.notification.NotificationException;
import com.blueteam.notifier.exception.validation.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {


    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ResponseError> handleBindingException(BindException ex) {
        ValidationError error = new ValidationError();
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        for (ObjectError oe : allErrors) {
            error.addViolation(((FieldError) oe).getField(), oe.getDefaultMessage());
        }
        ResponseError responseError = new ResponseError(
                "Validation error(s) occurred", error);
        log.error("An Exception occurred during validation -> {}", responseError);
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleJSONParseError(HttpMessageNotReadableException ex) {
        ResponseError responseError = new ResponseError(
                "JSON not readable or invalid type of values", ex.getMessage());
        log.error("An Exception occurred during JSON parsing -> {}", responseError);
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotificationException.class)
    public ResponseEntity<Object> handleNotificationException(NotificationException ex) {
        ResponseError responseError = new ResponseError(ex.getMessage(), ex.getData());
        log.error("An Exception occurred during notification process -> {}", responseError);
        return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseError> handleRuntimeException(RuntimeException ex) {
        ResponseError responseError = new ResponseError(
                "An unhandled RuntimeException was thrown", ex.getMessage());
        log.error("An unhandled RuntimeException was thrown -> {}", responseError);
        return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
