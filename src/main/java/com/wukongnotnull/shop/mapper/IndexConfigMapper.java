package com.wukongnotnull.shop.mapper;

import com.wukongnotnull.shop.domain.IndexConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author wukong
* @description 针对表【shop_index_config】的数据库操作Mapper
* @createDate 2023-07-11 15:41:01
* @Entity com.wukongnotnull.shop.domain.IndexConfig
*/
@Mapper
public interface IndexConfigMapper extends BaseMapper<IndexConfig> {


    List<IndexConfig> findIndexConfigList(
            @Param("configType") Integer configType,
            @Param("indexModuleGoodsNum") int indexModuleGoodsNum);
}




