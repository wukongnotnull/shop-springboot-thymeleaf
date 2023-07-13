package com.wukongnotnull.shop.common;

import lombok.Data;

/**
 * 分类级别枚举类
 * 分类级别的值是固定的，要想到设计枚举类
 * @author 悟空非空也
 */
public enum CategoryLevelEnum {
    CATEGORY_LEVEL_DEFAULT(0, "error"),
    CATEGORY_LEVEL_FIRST(1, "一级分类"),
    CATEGORY_LEVEL_SECOND(2, "二级分类"),
    CATEGORY_LEVEL_THIRD(3, "三级分类");
    // 分类级别
    private Integer level;
    // 级别名称
    private String name;

    public Integer getLevel() {
        return level;
    }

    CategoryLevelEnum(Integer level, String name) {
        this.level = level;
        this.name = name;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
