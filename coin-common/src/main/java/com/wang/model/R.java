package com.wang.model;

import com.wang.constant.Constants;
import java.io.Serializable;

/**
 * @Author wangzhen
 * @Description 公共的返回值对象
 * @Date 2025/4/3 9:49
 * @Version 1.0
 */
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功
     */
    public static final int SUCCESS = Constants.SUCCESS;

    /**
     * 失败
     */
    public static final int FAIL = Constants.FAIL;

    private int code; // 执行状态码 如：200：成功

    private String msg; // 提示信息

    private T data; // 携带的数据

    // ---------------------------  OK时的构造方法  ------------------------------------

    public static <T> R<T> ok() {
        return restResult(null, SUCCESS, null);
    }

    public static <T> R<T> ok(T data) {
        return restResult(data, SUCCESS, null);
    }

    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, SUCCESS, msg);
    }

    // ---------------------------  FAIL时的构造方法  ------------------------------------
    public static <T> R<T> fail() {
        return restResult(null, FAIL, null);
    }

    public static <T> R<T> fail(String msg) {
        return restResult(null, FAIL, msg);
    }

    public static <T> R<T> fail(T data) {
        return restResult(data, FAIL, null);
    }

    public static <T> R<T> fail(T data, String msg) {
        return restResult(data, FAIL, msg);
    }

    public static <T> R<T> fail(int code, String msg) {
        return restResult(null, code, msg);
    }

    private static <T> R<T> restResult(T data, int code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    // 属性相关的构造方法
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
