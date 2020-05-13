package com.xsq.lagou.common;

import java.io.Serializable;

/**
 * @ClassName ResultPack
 * @Description 统一结果处理集
 * @Author xsq
 * @Date 2020/4/17 9:26
 **/
public class ResultPack implements Serializable {

    private final static String SUCCESS = "200";

    private final static String ERROR = "300";

    private String code;

    private String msg;

    private Object data;

    public ResultPack(String code) {
        this.code = code;

    }

    public ResultPack(String code, Object data) {
        this.code = code;
        this.data = data;
    }

    public ResultPack(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultPack success() {
        return new ResultPack(ResultPack.SUCCESS);
    }

    public static ResultPack success(Object data) {
        return new ResultPack(ResultPack.SUCCESS, data);
    }

    public static ResultPack success(String msg, Object data) {
        return new ResultPack(ResultPack.SUCCESS, msg, data);
    }

    public static ResultPack error() {
        return new ResultPack(ResultPack.ERROR);
    }

    public static ResultPack error(String msg) {
        return new ResultPack(ResultPack.ERROR, msg);
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
