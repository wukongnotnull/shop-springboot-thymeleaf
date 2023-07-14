package com.wukongnotnull.shop.common;

/**
 * @author 悟空非空也
 * 商品销售状态类
 */

public enum GoodsSellStatusEnum {
    GOODS_NOT_ON_SALE(0,"GOODS_NOT_ON_SALE"),
    GOODS__ON_SALE(1,"GOODS_ON_SALE"),

    ;
    private int status;
    private String name;

    GoodsSellStatusEnum(int status, String name) {
        this.status = status;
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
