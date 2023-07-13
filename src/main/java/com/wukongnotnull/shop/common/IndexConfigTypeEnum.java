package com.wukongnotnull.shop.common;

import lombok.Data;

/**
 * @author 悟空非空也
 */

public enum IndexConfigTypeEnum {
    INDEX_DEFAULT(0,"default"),
    INDEX_SEARCH_HOT(1,"搜索框热搜"),
    INDEX_SEARCH_DOWN_HOT(2,"搜索下拉框热搜"),
    INDEX_GOODS_HOT(3,"(首页）热销商品"),
    INDEX_GOODS_NEW(4,"(首页)新品上线"),
    INDEX_GOODS_RECOMMEND(5,"(首页）为你推荐")
    ;
    private  Integer type;
    private  String  name;

    IndexConfigTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
