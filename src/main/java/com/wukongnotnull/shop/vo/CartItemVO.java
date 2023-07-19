package com.wukongnotnull.shop.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 悟空非空也
 */
@Data
public class CartItemVO  implements Serializable {

    /**
     *  cartItemId  与 goodsId 是一对一的关系
     */
    private Long cartItemId;

    private Long goodsId;

    private Integer goodsCount;


    /**
     * 如下属性来源于 GoodsDetail
     */
    private String goodsName;

    private String goodsCoverImg;

    private Integer sellingPrice;


}
