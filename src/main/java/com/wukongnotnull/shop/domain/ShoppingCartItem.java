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
 * @TableName shop_shopping_cart_item
 */
@TableName(value ="shop_shopping_cart_item")
@Data
public class ShoppingCartItem implements Serializable {
    /**
     * 购物项主键id
     */
    @TableId(type = IdType.AUTO)
    private Long cartItemId;

    /**
     * 用户主键id
     */
    private Long userId;

    /**
     * 关联商品id
     */
    private Long goodsId;

    /**
     * 数量(最大为5)
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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}