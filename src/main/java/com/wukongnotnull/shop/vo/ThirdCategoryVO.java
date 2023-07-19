package com.wukongnotnull.shop.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 悟空非空也
 */
@Data
public class ThirdCategoryVO implements Serializable {

    private Long categoryId;
    private  Long parentId;
    private Long categoryLevel;
    private  String categoryName;
}
