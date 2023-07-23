package com.wukongnotnull.shop.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 悟空非空也
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 响应状态码
     */
    private int resultCode;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 响应数据（结果）
     */
    private T data;

    public Result() {
    }

    public Result(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }


}
