package com.lotte.danuri.order.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    ORDER_NOT_FOUND(400,"ORDER-ERR-400","ORDER NOT FOUND IN DB")
    ;

    private int status;
    private String errorCode;
    private String message;
}
