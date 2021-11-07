package com.blueteam.tracker.exception.search;

import com.blueteam.tracker.exception.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SearchExceptionHandler {

    @ExceptionHandler(SearchNullParametersException.class)
    public ResponseEntity<ResponseError> handleSearchNullParameterException(SearchNullParametersException ex) {
        ResponseError responseError = new ResponseError(ex.getMessage(), ex.getData());
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SearchIllegalArgumentException.class)
    public ResponseEntity<ResponseError> handleSearchIllegalArgumentException(SearchIllegalArgumentException ex) {
        ResponseError responseError = new ResponseError(ex.getMessage(), ex.getData());
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }
}
