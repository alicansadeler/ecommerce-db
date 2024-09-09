package com.alicansadeler.myecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorResponse> exceptionHandler(ApiException apiException) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(apiException.getMessage(), apiException.getHttpStatus().value());

        return new ResponseEntity<>(errorResponse, apiException.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> exceptionHandler(Exception exception) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
