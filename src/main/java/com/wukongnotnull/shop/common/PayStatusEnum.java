package com.wukongnotnull.shop.common;

/**
 * 未支付 1  支付成功 2  支付失败 -1
 * @author 悟空非空也
 */
public enum PayStatusEnum {
    PAY_ERROR(-1,"支付失败"),
    PAY_READY(1,"未支付,准备支付"),
    PAY_SUCCESS(2,"支付成功")
    ;
    private Integer payStatus;
    private String name;

    PayStatusEnum(Integer payStatus, String name) {
        this.payStatus = payStatus;
        this.name = name;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
