package com.lotte.danuri.order.exception;

import com.lotte.danuri.order.error.ErrorCode;
import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException{

    private ErrorCode errorCode;
    public ProductNotFoundException(String msg, ErrorCode errorCode){
        super(msg);
        this.errorCode = errorCode;
    }
}
