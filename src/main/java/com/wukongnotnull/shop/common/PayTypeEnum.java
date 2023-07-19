package com.wukongnotnull.shop.common;

/**
 * @author 悟空非空也
 * 支付方式 : 0-无支付  1-支付宝支付   2-微信支付
 */

public enum PayTypeEnum {
    NO_TYPE_PAY(0,"无支付方式"),
    ZHIFUBAO_PAY(1,"支付宝方式支付"),
    WEIXIN_PAY(2,"微信方式支付")
    ;
    private int payType;
    private String name;

    PayTypeEnum(int payType, String name) {
        this.payType = payType;
        this.name = name;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
