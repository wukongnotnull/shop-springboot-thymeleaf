package com.wukongnotnull.shop.controller.vo;

/**
 * @author 悟空非空也
 */

public enum CodeMessageEnum {
    SUCCESS(20000,"SUCCESS"),
    REDIRECT(30100,"REDIRECT"),
    NOT_FOND(40000,"NOT_FOUND"),
    SERVER_ERROR(50000,"SERVER_ERROR"),
    PARAMS_ERROR(1314,"PARAMS_ERROR"),
    SYSTEM_ERROR(520,"SYSTEM_ERROR"),
    REGISTER_ERROR(21000,"REGISTER_ERROR"),
    PARAM_IS_NULL(22000,"PARAM_IS_NULL"),
    CAPTCHA_IS_ILLEGAL(23000,"CAPTCHA_IS_ILLEGAL"),
    LOGIN_FAIL(24000,"LOGIN_FAIL" );

    private int code;
    private String msg;

    CodeMessageEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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
}
