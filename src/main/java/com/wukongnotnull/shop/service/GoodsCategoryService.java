package com.wukongnotnull.shop.service;

import com.wukongnotnull.shop.vo.IndexCategoryVO;
import com.wukongnotnull.shop.domain.GoodsCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author wukong
* @description 针对表【shop_goods_category】的数据库操作Service
* @createDate 2023-07-11 15:40:15
*/
public interface GoodsCategoryService extends IService<GoodsCategory> {


    /**
     * 查询首页侧边分类导航数据
     * @return List<IndexCategoryVO>
     */
    List<IndexCategoryVO> getIndexCategoryNav();
}
