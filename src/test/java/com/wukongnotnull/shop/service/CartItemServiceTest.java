package com.wukongnotnull.shop.service;

import com.wukongnotnull.shop.controller.vo.CartItemVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CartItemServiceTest {
    @Autowired
    private  CartItemService cartItemService;

    @Test
    void findCartItemListPage() {
        List<CartItemVO> cartItemListPage = cartItemService.findCartItemListPage(11L, 1, 2);
        System.out.println("cartItemListPage = " + cartItemListPage);
    }
}