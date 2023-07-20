package com.wukongnotnull.shop.mapper;

import com.wukongnotnull.shop.domain.CartItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 悟空非空也
* @description 针对表【shop_cart_item】的数据库操作Mapper
*/
@Mapper
public interface CartItemMapper extends BaseMapper<CartItem> {

    /**
     * 根据 用户id 查询名下购物车中记录数量
     * @param userId 用户id
     * @return Integer
     */
    Integer selectCartItemCount(@Param("userId") Long userId);

    /**
     * 查询指定用户下的购物车明细记录列表
     * @param userId  userId
     * @return List<CartItem>
     */
    List<CartItem> selectCartItemList(@Param("userId") Long userId);


    /**
     *  插入购物车明细记录一条
     * @param cartItem
     * @return
     */
    int insertCartItem(CartItem cartItem);

    /**
     * 根据购物车记录id 删除该记录
     * 本质是：假删除，实质是修改
     * @param cartItemId cartItemId
     * @return int
     */
    int deleteCartItem(@Param("cartItemId") Long cartItemId);

    /**
     * 修改购物车记录中的商品数量
     * @param cartItem cartItem
     * @return int
     */
    int updateCartItem(CartItem cartItem);

    /**
     * delete cart items ,in fact , change is_deleted value from 0 to 1
     * @param userId
     * @return
     */
    int deleteCartItems(@Param("userId") Long userId);
}




