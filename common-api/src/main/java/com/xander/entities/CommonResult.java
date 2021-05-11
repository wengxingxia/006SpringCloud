package com.xander.entities;


/**
 * Description: 公共响应实体封装
 *
 * @author Xander
 * datetime: 2021-05-10 22:34
 */
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T data;

    public static CommonResult newInstance() {
        CommonResult instance = new CommonResult();
        return instance;
    }

    public Integer getCode() {
        return code;
    }

    public CommonResult setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommonResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public CommonResult setData(T data) {
        this.data = data;
        return this;
    }
}
