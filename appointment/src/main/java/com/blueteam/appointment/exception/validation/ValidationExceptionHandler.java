package com.blueteam.appointment.exception.validation;

import com.blueteam.appointment.exception.ResponseError;
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
public class ValidationExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ResponseError> handleBindingException(BindException ex) {
        ValidationError error = new ValidationError();
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        for (ObjectError oe : allErrors) {
            error.addViolation(((FieldError) oe).getField(), oe.getDefaultMessage());
        }
        ResponseError responseError = new ResponseError("Validation error(s) occurred", error);
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleJSONParseError(HttpMessageNotReadableException ex) {
        ResponseError responseError = new ResponseError("JSON not readable or invalid type of values", ex.getMessage());
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseError> handleRuntimeException(RuntimeException ex) {
        ResponseError responseError = new ResponseError("An unhandled RuntimeException was thrown", ex.getMessage());
        return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
