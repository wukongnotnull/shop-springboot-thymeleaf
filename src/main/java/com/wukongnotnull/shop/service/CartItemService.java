package com.wukongnotnull.shop.service;

import com.wukongnotnull.shop.controller.vo.CartItemVO;
import com.wukongnotnull.shop.domain.CartItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 悟空非空也
*/
public interface CartItemService extends IService<CartItem> {

    /**
     * 查询指定用户下的购物车明细记录列表
     *
     * @param userId userId
     * @return List<CartItemVO>
     */
    List<CartItemVO> findCartItemList(Long userId);

    /**
     * 某用户添加购物车明细记录
     * @param cartItem    cartItem
     * @return String
     */
    String addCartItem(CartItem cartItem);

    /**
     * 查询指定用户名下的购物车记录数
     * @param userId userId
     * @return Integer
     */
    Integer getCartItemCount(Long userId);

    /**
     * 根据购物车记录id 删除该记录
     * @param cartItemId cartItemId
     * @return String
     */
    String deleteCartItem(Long cartItemId);

    /**
     * 修改购物车记录中的商品数量
     * @param cartItem cartItem
     * @return String
     */
    String updateCartItem(CartItem cartItem);

    /**
     * according to userId ,drop cart items
     * @param userId userId
     * @return String
     */
    String dropCartItems(Long userId);
}
