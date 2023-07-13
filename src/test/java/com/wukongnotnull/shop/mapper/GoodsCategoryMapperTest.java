package com.wukongnotnull.shop.mapper;

import com.wukongnotnull.shop.domain.GoodsCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GoodsCategoryMapperTest {
    @Autowired
    private  GoodsCategoryMapper goodsCategoryMapper;

    @Test
    void selectByLevelAndParentIdsAndNumber() {
        List<GoodsCategory> goodsCategories =
                goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(
                        1, Collections.singletonList(0L), 10);
        System.out.println("goodsCategories = " + goodsCategories);
    }

}