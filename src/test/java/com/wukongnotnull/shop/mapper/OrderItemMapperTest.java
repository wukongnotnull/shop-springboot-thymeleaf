package com.wukongnotnull.shop.mapper;

import com.wukongnotnull.shop.bo.CartItemBO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderItemMapperTest {
    @Resource
    private OrderItemMapper orderItemMapper;

    @Test
    void insertBatch() {
        List<CartItemBO> cartItemBOList = new ArrayList<>();
        CartItemBO cartItemBO = new CartItemBO();
        cartItemBO.setOrderId(111L);
        cartItemBO.setGoodsId(1L);
        cartItemBO.setGoodsName("1 号商品");
        cartItemBO.setGoodsCoverImg("http://img");
        cartItemBO.setSellingPrice(1000);
        cartItemBO.setGoodsCount(1);

        CartItemBO cartItemBO2 = new CartItemBO();
        cartItemBO2.setOrderId(111L);
        cartItemBO2.setGoodsId(2L);
        cartItemBO2.setGoodsName("2 号商品");
        cartItemBO2.setGoodsCoverImg("http://img2");
        cartItemBO2.setSellingPrice(2000);
        cartItemBO2.setGoodsCount(2);

        cartItemBOList.add(cartItemBO);
        cartItemBOList.add(cartItemBO2);

        int i = orderItemMapper.insertBatch(cartItemBOList);
        System.out.println("批量插入结果：i = " + i);


    }
}