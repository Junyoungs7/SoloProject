package com.solo.soloproject.exception;

import lombok.Getter;

public enum ExceptionCode {

    TODO_NOT_FOUND(404, "Todo not found"),
    TODO_EXISTS(404, "Todo exists");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
