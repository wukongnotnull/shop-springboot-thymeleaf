package com.wukongnotnull.shop.service;

import com.wukongnotnull.shop.controller.vo.GoodsDetailVO;
import com.wukongnotnull.shop.domain.GoodsDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wukongnotnull.shop.util.PageQueryMapUtil;
import com.wukongnotnull.shop.util.PageResult;

import java.util.List;

/**
* @author wukong
* @description 针对表【shop_goods_detail】的数据库操作Service
*/
public interface GoodsDetailService extends IService<GoodsDetail> {

    /**
     * 分页查询商品列表
     * @param pageUtil pageUtil
     * @return PageResult
     */
    PageResult<Object> getGoodsListPage(PageQueryMapUtil pageUtil);

    /**
     *  根据 商品id 获得商品信息
     * @param goodsId 商品id
     * @return GoodsDetailVO
     */
    GoodsDetailVO findGoodsDetailOnSale(Long goodsId);

    /**
     * save  Goods detail
     * @param goodsDetail goodsDetail
     * @return String
     */
    String saveGoodsDetail(GoodsDetail goodsDetail);

    GoodsDetail findGoodsDetail(Long goodsId);

    String modifyGoodsDetail(GoodsDetail goodsDetail);

    String modifyGoodsSellStatusBatch(List<Long> ids, Integer goodsSellStatus);
}
