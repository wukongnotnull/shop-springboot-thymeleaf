package com.wukongnotnull.shop.service.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author wukong
 */
@Data
public class CartItemBO implements Serializable {

    /**
     * 用户主键id
     */
    private Long userId;

    /**
     * 关联商品id
     */
    private Long goodsId;

    /**
     * 单商品数量(最大为5)
     */
    private Integer goodsCount;

    /**
     * 删除标识字段(0-未删除 1-已删除)
     */
    private Integer isDeleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最新修改时间
     */
    private Date updateTime;

    // 补充一下 goods_detail 中的属性

    private String goodsName;

    private String goodsCoverImg;

    private Integer sellingPrice;

    /**
     * order_id
     */
    private Long orderId;


}