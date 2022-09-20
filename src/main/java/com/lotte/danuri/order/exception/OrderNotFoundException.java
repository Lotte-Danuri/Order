package com.lotte.danuri.order.exception;

import com.lotte.danuri.order.error.ErrorCode;
import lombok.Getter;

@Getter
public class OrderNotFoundException extends RuntimeException{

    private ErrorCode errorCode;
    public OrderNotFoundException(String msg, ErrorCode errorCode){
        super(msg);
        this.errorCode = errorCode;
    }
}
