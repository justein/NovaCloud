package com.nova.sbnetty.base;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {

    private String code;
    private String msg;
    private String reqNo;
    private T dataBody;

    public BaseResponse() {}

    public BaseResponse(T dataBody) {
        this.dataBody = dataBody;
    }

    public BaseResponse(String code, String message) {
        this.code = code;
        this.msg = message;
    }

    public BaseResponse(String code, String message, T dataBody) {
        this.code = code;
        this.msg = message;
        this.dataBody = dataBody;
    }

    public BaseResponse(String code, String message, String reqNo, T dataBody) {
        this.code = code;
        this.msg = message;
        this.reqNo = reqNo;
        this.dataBody = dataBody;
    }

    public static <T> BaseResponse<T> create(T t){
        return new BaseResponse<T>(t);
    }

    public static <T> BaseResponse<T> create(T t, StatusEnum statusEnum){
        return new BaseResponse<T>(statusEnum.getCode(), statusEnum.getMessage(), t);
    }

    public static <T> BaseResponse<T> createSuccess(T t, String message){
        return new BaseResponse<T>(StatusEnum.SUCCESS.getCode(), StringUtils.isNotEmpty(message) ? StatusEnum.SUCCESS.getMessage() : message, t);
    }

    public static <T> BaseResponse<T> createFail(T t, String message){
        return new BaseResponse<T>(StatusEnum.FAIL.getCode(), StringUtils.isNotEmpty(message) ? StatusEnum.FAIL.getMessage() : message, t);
    }

    public static <T> BaseResponse<T> create(T t, StatusEnum statusEnum, String message){

        return new BaseResponse<T>(statusEnum.getCode(), message, t);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getDataBody() {
        return dataBody;
    }

    public void setDataBody(T dataBody) {
        this.dataBody = dataBody;
    }

    public String getReqNo() {
        return reqNo;
    }

    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }

}
