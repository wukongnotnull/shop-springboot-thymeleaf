package com.wukongnotnull.shop.mapper;

import com.wukongnotnull.shop.common.IndexConfigTypeEnum;
import com.wukongnotnull.shop.domain.IndexConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class IndexConfigMapperTest {
    @Autowired
    private IndexConfigMapper indexConfigMapper;
    @Test
    void findIndexConfigList() {
        List<IndexConfig> indexConfigList = indexConfigMapper.
                findIndexConfigList(
                        IndexConfigTypeEnum.INDEX_GOODS_HOT.getType(),
                        15);
        System.out.println("indexConfigList = " + indexConfigList);

    }
}