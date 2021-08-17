package com.huangpuguang.common.core.domain;

import com.huangpuguang.common.core.constant.Constants;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @author procon
 */
public class ResultModel<T> implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 成功 */
    public static final int SUCCESS = Constants.SUCCESS;

    /** 失败 */
    public static final int FAILED = Constants.FAILED;

    private int code;

    private String msg;


    private transient T data;

    public static <T> ResultModel<T> ok()
    {
        return restResult(null, SUCCESS, null);
    }

    public static <T> ResultModel<T> ok(T data)
    {
        return restResult(data, SUCCESS, null);
    }

    public static <T> ResultModel<T> ok(T data, String msg)
    {
        return restResult(data, SUCCESS, msg);
    }

    public static <T> ResultModel<T> fail()
    {
        return restResult(null, FAILED, null);
    }

    public static <T> ResultModel<T> fail(String msg)
    {
        return restResult(null, FAILED, msg);
    }

    public static <T> ResultModel<T> fail(T data)
    {
        return restResult(data, FAILED, null);
    }

    public static <T> ResultModel<T> fail(T data, String msg)
    {
        return restResult(data, FAILED, msg);
    }

    public static <T> ResultModel<T> fail(int code, String msg)
    {
        return restResult(null, code, msg);
    }

    private static <T> ResultModel<T> restResult(T data, int code, String msg)
    {
        ResultModel<T> apiResult = new ResultModel<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }
}
