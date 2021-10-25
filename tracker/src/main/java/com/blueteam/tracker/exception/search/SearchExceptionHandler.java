package com.blueteam.tracker.exception.search;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SearchExceptionHandler {

    @ExceptionHandler(SearchNullParametersException.class)
    public ResponseEntity<Object> handleSearchNullParameterException(SearchNullParametersException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(SearchIllegalArgumentException.class)
    public ResponseEntity<Object> handleSearchIllegalArgumentException(SearchIllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_GATEWAY);
    }
}
