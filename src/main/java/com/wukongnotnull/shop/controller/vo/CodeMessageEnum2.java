package com.wukongnotnull.shop.controller.vo;

import com.wukongnotnull.shop.common.ServiceResultEnum;

/**
 * @author 悟空非空也  尝试一下
 */

public enum CodeMessageEnum2 {

    TEST_TEST(11111,null),
    SUCCESS(200,null)
    ;


    private int code;
    private ServiceResultEnum serviceResultEnum ;

    CodeMessageEnum2(int code, ServiceResultEnum serviceResultEnum) {
        this.code = code;
        this.serviceResultEnum = serviceResultEnum;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ServiceResultEnum getServiceResultEnum() {
        return serviceResultEnum;
    }

    public void setServiceResultEnum(ServiceResultEnum serviceResultEnum) {
        this.serviceResultEnum = serviceResultEnum;
    }
}
