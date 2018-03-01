package com.xhuabu.xhuabu_androiddemo.http;


public class ApiException extends RuntimeException {

    private String message;
    private int code = -1;
    private Object data;

    public ApiException(String message) {
        this.message = message;
    }

    public ApiException(String message, int code, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public ApiException(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
