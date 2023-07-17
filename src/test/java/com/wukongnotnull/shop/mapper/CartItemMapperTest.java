package com.wukongnotnull.shop.mapper;

import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CartItemMapperTest {

    @Autowired
    private CartItemMapper cartItemMapper;

    @Test
    void selectGoodsCount() {

        Integer cartItemCount = cartItemMapper.selectCartItemCount(11L);
        System.out.println("cartItemCount = " + cartItemCount);
    }

    @Test
    void deleteCartItem(){
        int i = cartItemMapper.deleteCartItem(78L);
        System.out.println("删除结果为 i = " + i);
    }
}