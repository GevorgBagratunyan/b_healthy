package com.blueteam.tracker.exception;

import com.blueteam.tracker.exception.doctor.DoctorNotFoundException;
import com.blueteam.tracker.exception.patient.PatientNotFoundException;
import com.blueteam.tracker.exception.search.SearchIllegalArgumentException;
import com.blueteam.tracker.exception.search.SearchNullParametersException;
import com.blueteam.tracker.exception.validation.ValidationError;
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

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<ResponseError> handleDoctorNotFoundException(DoctorNotFoundException ex) {
        log.error("An Exception during requesting data -> {}, {}",
                ex.getMessage(), ex.getData());
        ResponseError responseError = new ResponseError(ex.getMessage(), ex.getData());
        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Object> handlePatientNotFoundException(PatientNotFoundException ex) {
        log.error("An Exception occurred during requesting data -> msg: {}, data: {}",
                ex.getMessage(), ex.getData());
        ResponseError responseError = new ResponseError(ex.getMessage(), ex.getData());
        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SearchNullParametersException.class)
    public ResponseEntity<ResponseError> handleSearchNullParameterException(
            SearchNullParametersException ex) {
        log.error("An Exception during searching data -> msg: {}, data: {}",
                ex.getMessage(), ex.getData());
        ResponseError responseError = new ResponseError(ex.getMessage(), ex.getData());
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SearchIllegalArgumentException.class)
    public ResponseEntity<ResponseError> handleSearchIllegalArgumentException(
            SearchIllegalArgumentException ex) {
        log.error("An Exception during searching data -> msg: {}, data: {}",
                ex.getMessage(), ex.getData());
        ResponseError responseError = new ResponseError(ex.getMessage(), ex.getData());
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

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
        log.error("An Exception occurred during validation -> {}", responseError);
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseError> handleRuntimeException(RuntimeException ex) {
        ResponseError responseError = new ResponseError(
                "An unhandled RuntimeException was thrown", ex.getMessage());
        log.error("An Exception occurred during validation -> {}", responseError);
        return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
