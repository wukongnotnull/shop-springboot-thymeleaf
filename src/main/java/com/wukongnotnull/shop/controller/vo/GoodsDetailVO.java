package com.wukongnotnull.shop.controller.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 悟空非空也
 */
@Data
public class GoodsDetailVO {
    private  Long goodsId;
    private String  goodsCoverImg;
    private String[] goodsCarouselList;
    private String goodsName;
    private String goodsIntro;
    private Integer sellingPrice;
    private  Integer originalPrice;
    private  String goodsDetailContent;


}
