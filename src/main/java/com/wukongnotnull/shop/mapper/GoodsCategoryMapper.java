package com.wukongnotnull.shop.mapper;

import com.wukongnotnull.shop.domain.GoodsCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author wukong
* @description 针对表【shop_goods_category】的数据库操作Mapper
* @createDate 2023-07-11 15:40:15
* @Entity com.wukongnotnull.shop.domain.GoodsCategory
*/
@Mapper
public interface GoodsCategoryMapper extends BaseMapper<GoodsCategory> {

    /**
     *  根据分类级别、父分类id、最大数量 查询商品一级分类列表
     * @param categoryLevel  分类级别
     * @param parentId 父分类 id
     * @param indexFirstCategoryMaxNum  首页一级分类最大数量
     * @return  商品分类列表
     */
    List<GoodsCategory> selectByLevelAndParentIdsAndNumber(
            @Param("categoryLevel") Integer categoryLevel,
            @Param("parentIds") List<Long> parentIds,
            @Param("categoryMaxNum") int indexCategoryMaxNum);


    GoodsCategory selectCategoryBy(@Param("categoryId") Long categoryId);

}




