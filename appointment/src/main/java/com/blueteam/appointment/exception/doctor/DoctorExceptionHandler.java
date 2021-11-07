package com.blueteam.appointment.exception.doctor;

import com.blueteam.appointment.exception.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DoctorExceptionHandler {

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<ResponseError> handleDoctorNotFoundException(DoctorNotFoundException ex) {
        ResponseError responseError = new ResponseError(ex.getMessage(), "No additional information");
        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }
}
