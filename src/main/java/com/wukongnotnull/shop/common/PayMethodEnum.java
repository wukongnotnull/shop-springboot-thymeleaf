package com.wukongnotnull.shop.common;

/**
 * @author 悟空非空也
 * 支付方式 : 0-无支付  1-支付宝支付   2-微信支付
 */

public enum PayMethodEnum {
    NO_TYPE_PAY(0, "无支付方式"),
    ZHIFUBAO_PAY(1, "支付宝方式支付"),
    WEIXIN_PAY(2, "微信方式支付");
    private int payMethod;
    private String name;

    public static String getPayMethodName(int payMethod) {
        switch (payMethod) {
            case 0:
                PayMethodEnum.NO_TYPE_PAY.getName();
            case 1:
                PayMethodEnum.ZHIFUBAO_PAY.getName();
            case 2:
                PayMethodEnum.WEIXIN_PAY.getName();
        }
        return  null;
    }

    PayMethodEnum(int payMethod, String name) {
        this.payMethod = payMethod;
        this.name = name;
    }

    public int getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(int payMethod) {
        this.payMethod = payMethod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
