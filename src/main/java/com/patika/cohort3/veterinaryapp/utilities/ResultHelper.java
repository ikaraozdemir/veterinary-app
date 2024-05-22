package com.patika.cohort3.veterinaryapp.utilities;

import com.patika.cohort3.veterinaryapp.result.Result;
import com.patika.cohort3.veterinaryapp.result.ResultData;

public class ResultHelper {

    public static <T> ResultData<T> created(T data) {
        return new ResultData<>(true, Message.CREATED,"201",data);
    }

    public static <T> ResultData<T> validateError(T data) {
        return new ResultData<>(false,Message.VALIDATE_ERROR,"400",data);
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(true, Message.OK,"200",data);
    }

    public static Result ok() {
        return new Result(true, Message.OK,"200");
    }

    public static Result notFoundError(String msg) {
        return new Result(false, msg,"404");
    }

    public static Result alreadyExist(String msg) {
        return new Result(false, msg,"500");
    }

    public static Result notAvailableError(String msg) {
        return new Result(false, msg, "400");
    }

    public static Result cannotDeleteError(String msg) {
        return new Result(false, msg, "409");
    }

    public static Result dateFormatError() {
        return new Result(false,Message.DATE_FORMAT_ERROR,"400");
    }

}