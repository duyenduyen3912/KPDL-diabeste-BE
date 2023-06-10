package com.thi.bt.knn.response;

import java.util.Date;

public class ResponseData<T> {

    private int code;
    private String message;
    private T data;
    //    private T typeOfBody;
    private String path;
    private Date time;

    public ResponseData(Result result) {
        this.code = result.getCode();
        this.message = result.getMessage();
    }

    public ResponseData(T data, Result result) {
        this.data = data;
        this.code = result.getCode();
        this.message = result.getMessage();
    }

    public ResponseData (T data, Result result, String message){
        this.data = data;
        this.code = result.getCode();
        this.message = message;
    }

    public ResponseData(Result result, String s) {
        this.code = result.getCode();
        this.message = s;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
