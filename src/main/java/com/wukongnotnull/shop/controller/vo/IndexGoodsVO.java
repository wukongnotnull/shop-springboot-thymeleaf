package com.wukongnotnull.shop.controller.vo;

import lombok.Data;

/**
 * @author 悟空非空也
 */
@Data
public class IndexGoodsVO {
    private Long goodsId;
    private String goodsName;
    private String goodsCoverImg;
    private String tag;
    private String goodsIntro;
    private Integer sellingPrice;

}
