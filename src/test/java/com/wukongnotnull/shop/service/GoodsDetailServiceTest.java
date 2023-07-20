package com.wukongnotnull.shop.service;

import com.wukongnotnull.shop.controller.vo.GoodsDetailVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GoodsDetailServiceTest {
    @Autowired
    private  GoodsDetailService goodsDetailService;

    @Test
    void findGoodsDetail() {
        GoodsDetailVO goodsDetailVO = goodsDetailService.findGoodsDetailOnSale(10003L);
        System.out.println("goodsDetailVO = " + goodsDetailVO);

    }
}