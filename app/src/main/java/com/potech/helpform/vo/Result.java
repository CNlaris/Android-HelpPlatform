package com.potech.helpform.vo;

import java.io.Serializable;
import java.util.Map;

public class Result<T> implements Serializable {
    private int code = 0;
    private String message = "";
    private T data;

    public Result(int code,String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(T data){
        this.data = data;
    }

    public Result() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
