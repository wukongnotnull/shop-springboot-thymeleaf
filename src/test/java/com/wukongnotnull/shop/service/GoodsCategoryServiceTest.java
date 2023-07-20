package com.wukongnotnull.shop.service;

import com.wukongnotnull.shop.controller.vo.IndexCategoryVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GoodsCategoryServiceTest {

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @Test
    void getIndexCategoryNav() {
        List<IndexCategoryVO> indexCategoryVOList = goodsCategoryService.getIndexCategoryNav();
        System.out.println("indexCategoryVOList = " + indexCategoryVOList);
    }
}