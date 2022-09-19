package com.lotte.danuri.order.handler;

import com.lotte.danuri.order.exception.ProductNotFoundException;
import com.lotte.danuri.order.model.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> productNotFoundException(ProductNotFoundException e){
        log.error("productNotFoundException",e);
        ErrorResponse response = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }
}