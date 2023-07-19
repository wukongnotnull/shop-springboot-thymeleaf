package com.wukongnotnull.shop.service;

import com.wukongnotnull.shop.vo.IndexGoodsVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class IndexConfigServiceTest {

    @Autowired
    private IndexConfigService indexConfigService;

    @Test
    void getIndexGoodsModule() {
        List<IndexGoodsVO> indexGoodsHotsList = indexConfigService.getIndexGoodsModule(5, 3);
        System.out.println("indexGoodsHotsList = " + indexGoodsHotsList);
    }


}