package com.wukongnotnull.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wukongnotnull.shop.common.CategoryLevelEnum;
import com.wukongnotnull.shop.common.GoodsSellStatusEnum;
import com.wukongnotnull.shop.common.ServiceResultEnum;
import com.wukongnotnull.shop.controller.vo.GoodsDetailVO;
import com.wukongnotnull.shop.domain.GoodsCategory;
import com.wukongnotnull.shop.domain.GoodsDetail;
import com.wukongnotnull.shop.exception.ShopException;
import com.wukongnotnull.shop.mapper.GoodsCategoryMapper;
import com.wukongnotnull.shop.service.GoodsDetailService;
import com.wukongnotnull.shop.mapper.GoodsDetailMapper;
import com.wukongnotnull.shop.util.BeanUtil;
import com.wukongnotnull.shop.util.PageQueryMapUtil;
import com.wukongnotnull.shop.util.PageResult;
import com.wukongnotnull.shop.util.ShopUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author wukong
* @description 针对表【shop_goods_detail】的数据库操作Service实现
* @createDate 2023-07-11 15:40:44
*/
@Service
public class GoodsDetailServiceImpl extends ServiceImpl<GoodsDetailMapper, GoodsDetail>
    implements GoodsDetailService{

    @Resource
    private GoodsDetailMapper goodsDetailMapper;

    @Resource
    private GoodsCategoryMapper goodsCategoryMapper;

    /**
     * @param pageUtil
     * @return
     */
    @Override
    public PageResult<Object> getGoodsListPage(PageQueryMapUtil pageUtil) {
        List<GoodsDetail> goodsList = goodsDetailMapper.selectGoodsList(pageUtil);
        int total = goodsDetailMapper.getTotalGoodsCount(pageUtil);
        return new PageResult(goodsList, total, pageUtil.getLimit(), pageUtil.getPage());
    }

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

    /**
     * save  Goods detail
     *
     * @param goodsDetail goodsDetail
     * @return String
     */
    @Override
    public String saveGoodsDetail(GoodsDetail goodsDetail) {
        GoodsCategory goodsCategory = goodsCategoryMapper.selectById(goodsDetail.getGoodsCategoryId());
        // 分类不存在或者不是三级分类，则该参数字段异常
        if (goodsCategory == null || !goodsCategory.getCategoryLevel().equals(CategoryLevelEnum.CATEGORY_LEVEL_THIRD.getLevel())) {
            return ServiceResultEnum.GOODS_CATEGORY_ERROR.getResult();
        }
        //  若该商品存在，不要重复添加
        if (goodsDetailMapper.selectByCategoryIdAndName(goodsDetail.getGoodsName(), goodsDetail.getGoodsCategoryId()) != null) {
            return ServiceResultEnum.SAME_GOODS_EXIST.getResult();
        }
        goodsDetail.setGoodsName(ShopUtil.cleanString(goodsDetail.getGoodsName()));
        goodsDetail.setGoodsIntro(ShopUtil.cleanString(goodsDetail.getGoodsIntro()));
        goodsDetail.setTag(ShopUtil.cleanString(goodsDetail.getTag()));

        if (goodsDetailMapper.insertSelective(goodsDetail) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public GoodsDetail findGoodsDetail(Long goodsId) {
        return goodsDetailMapper.selectGoodsDetail(goodsId);
    }

    @Override
    public String modifyGoodsDetail(GoodsDetail goodsDetail) {
        int i = goodsDetailMapper.updateById(goodsDetail);
        if (i ==1) {
          return   ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.ERROR.getResult();
    }

    @Override
    public String modifyGoodsSellStatusBatch(List<Long> ids, Integer goodsSellStatus) {
       int i = goodsDetailMapper.updateSellStatusBatch(ids,goodsSellStatus);
        if (i >0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.ERROR.getResult();
    }
}




