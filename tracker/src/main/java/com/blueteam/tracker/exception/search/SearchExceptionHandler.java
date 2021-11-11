package com.blueteam.tracker.exception.search;

import com.blueteam.tracker.exception.ResponseError;
import com.blueteam.tracker.exception.doctor.DoctorExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SearchExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(DoctorExceptionHandler.class);

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
}
