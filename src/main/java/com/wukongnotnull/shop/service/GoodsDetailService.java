package com.wukongnotnull.shop.service;

import com.wukongnotnull.shop.vo.GoodsDetailVO;
import com.wukongnotnull.shop.domain.GoodsDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author wukong
* @description 针对表【shop_goods_detail】的数据库操作Service
* @createDate 2023-07-11 15:40:44
*/
public interface GoodsDetailService extends IService<GoodsDetail> {

    /**
     *  根据 商品id 获得商品信息
     * @param goodsId 商品id
     * @return GoodsDetailVO
     */
    GoodsDetailVO findGoodsDetailOnSale(Long goodsId);
}
