package com.wukongnotnull.shop.service;

import com.wukongnotnull.shop.controller.vo.CartItemVO;
import com.wukongnotnull.shop.domain.CartItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CartItemServiceTest {
    @Autowired
    private  CartItemService cartItemService;

    @Test
    void addCartItem(){
        CartItem cartItem = new CartItem();
        cartItem.setUserId(11L);
        cartItem.setGoodsId(10159L);
        cartItem.setGoodsCount(1);
        String s = cartItemService.addCartItem(cartItem);
        System.out.println("添加购物车记录：s = " + s);
    }

    @Test
    void findCartItemListPage() {
        List<CartItemVO> cartItemListPage = cartItemService.findCartItemList(11L);
        System.out.println("cartItemListPage = " + cartItemListPage);
    }
}