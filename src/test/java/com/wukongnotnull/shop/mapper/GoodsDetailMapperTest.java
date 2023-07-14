package com.wukongnotnull.shop.mapper;

import com.wukongnotnull.shop.domain.GoodsDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GoodsDetailMapperTest {
    @Autowired
    private GoodsDetailMapper goodsDetailMapper;

    @Test
    void getGoodsDetailList() {
        List<Long> ids = new ArrayList<>();
        ids.add(10024L);
        ids.add(10015L);
        ids.add(10035L);
        ids.add(10003L);
        List<GoodsDetail> goodsDetailList = goodsDetailMapper.getGoodsDetailListOnSale(ids);
        System.out.println("goodsDetailList = " + goodsDetailList);
    }

}