package com.wukongnotnull.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wukongnotnull.shop.common.GoodsSellStatusEnum;
import com.wukongnotnull.shop.vo.GoodsDetailVO;
import com.wukongnotnull.shop.domain.GoodsDetail;
import com.wukongnotnull.shop.exception.ShopException;
import com.wukongnotnull.shop.service.GoodsDetailService;
import com.wukongnotnull.shop.mapper.GoodsDetailMapper;
import com.wukongnotnull.shop.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author wukong
* @description 针对表【shop_goods_detail】的数据库操作Service实现
* @createDate 2023-07-11 15:40:44
*/
@Service
public class GoodsDetailServiceImpl extends ServiceImpl<GoodsDetailMapper, GoodsDetail>
    implements GoodsDetailService{
    @Autowired
    private GoodsDetailMapper goodsDetailMapper;

    /**
     * 根据 商品id 获得商品信息
     * @param goodsId 商品id
     * @return GoodsDetailVO
     */
    @Override
    public GoodsDetailVO findGoodsDetailOnSale(Long goodsId) {
        GoodsDetail goodsDetail = goodsDetailMapper.selectGoodsDetail(goodsId);

        // 判断 goodsDetail 中的商品是否是售卖状态 0-表示下架； 1-表示上架
        if(goodsDetail.getGoodsSellStatus() == GoodsSellStatusEnum.GOODS_NOT_ON_SALE.getStatus()){
            ShopException.fail("商品下架中，抛出异常！");
        }

        // do to vo ，除了 轮播图字符串
        GoodsDetailVO goodsDetailVO = new GoodsDetailVO();
        BeanUtil.copyProperties(goodsDetail,goodsDetailVO);

        // 将轮播图集合字符串，转换成字符串数组
        goodsDetailVO.setGoodsCarouselList(goodsDetail.getGoodsCarousels().split(","));

        return goodsDetailVO;
    }
}




