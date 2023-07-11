package com.wukongnotnull.shop.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName shop_order_item
 */
@TableName(value ="shop_order_item")
@Data
public class OrderItem implements Serializable {
    /**
     * 订单关联购物项主键id
     */
    @TableId(type = IdType.AUTO)
    private Long orderItemId;

    /**
     * 订单主键id
     */
    private Long orderId;

    /**
     * 关联商品id
     */
    private Long goodsId;

    /**
     * 下单时商品的名称(订单快照)
     */
    private String goodsName;

    /**
     * 下单时商品的主图(订单快照)
     */
    private String goodsCoverImg;

    /**
     * 下单时商品的价格(订单快照)
     */
    private Integer sellingPrice;

    /**
     * 数量(订单快照)
     */
    private Integer goodsCount;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}