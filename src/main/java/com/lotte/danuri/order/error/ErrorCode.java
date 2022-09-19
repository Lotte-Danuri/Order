package com.lotte.danuri.order.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    PRODUCT_NOT_FOUND(400,"PRODUCT-ERR-400","PRODUCT NOT FOUND IN DB")
    ;

    private int status;
    private String errorCode;
    private String message;
}
