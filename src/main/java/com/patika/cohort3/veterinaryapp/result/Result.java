package com.patika.cohort3.veterinaryapp.result;

import lombok.Getter;

@Getter
public class Result {
    private final boolean status;
    private final String message;
    private final String code;


    public Result(boolean status, String message, String code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
