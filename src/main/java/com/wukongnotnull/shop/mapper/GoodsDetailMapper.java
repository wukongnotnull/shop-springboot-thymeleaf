package com.wukongnotnull.shop.mapper;

import com.wukongnotnull.shop.domain.GoodsDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 悟空非空也
* @description 针对表【shop_goods_detail】的数据库操作Mapper
*/
@Mapper
public interface GoodsDetailMapper extends BaseMapper<GoodsDetail> {

    /**
     *  根据商品 id 列表，获得商品信息列表
     * @param goodsIdList 商品 id 列表
     * @return List<GoodsDetail>
     */
    List<GoodsDetail> getGoodsDetailListOnSale(@Param("goodsIdList") List<Long> goodsIdList);

    /**
     *  根据 商品id 查询对应商品信息
     * @param goodsId 商品id
     * @return GoodsDetail
     */
    GoodsDetail selectGoodsDetail(@Param("goodsId") Long goodsId);
}




