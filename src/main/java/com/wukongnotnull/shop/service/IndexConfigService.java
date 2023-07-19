package com.wukongnotnull.shop.service;

import com.wukongnotnull.shop.vo.IndexGoodsVO;
import com.wukongnotnull.shop.domain.IndexConfig;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 悟空非空也
* @description 针对表【shop_index_config】的数据库操作 Service
*/
public interface IndexConfigService extends IService<IndexConfig> {

    /**
     * 获取首页各个模块（热品售卖、新品推荐、为你推荐）的商品列表
     * @param configType  配置类型
     * @param goodsNum  商品数量
     * @return List<IndexGoodsVO>
     */
    List<IndexGoodsVO> getIndexGoodsModule(int configType,int goodsNum);



}
