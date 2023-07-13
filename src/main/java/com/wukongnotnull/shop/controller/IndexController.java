package com.wukongnotnull.shop.controller;

import com.wukongnotnull.shop.common.Constants;
import com.wukongnotnull.shop.common.IndexConfigTypeEnum;
import com.wukongnotnull.shop.controller.vo.IndexCarouselVO;
import com.wukongnotnull.shop.controller.vo.IndexCategoryVO;
import com.wukongnotnull.shop.controller.vo.IndexGoodsVO;
import com.wukongnotnull.shop.service.CarouselService;
import com.wukongnotnull.shop.service.GoodsCategoryService;
import com.wukongnotnull.shop.service.GoodsDetailService;
import com.wukongnotnull.shop.service.IndexConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private GoodsCategoryService goodsCategoryService;
    @Autowired
    private CarouselService carouselService;
    @Autowired
    private GoodsDetailService goodsDetailService;
    @Autowired
    private IndexConfigService indexConfigService;

    @GetMapping({"/","/index","index.html"})
    public String index(Model model){
        // 获得分栏侧边导航
        List<IndexCategoryVO> indexCategoryList = goodsCategoryService.getIndexCategoryNav();
        model.addAttribute("categories",indexCategoryList);

        // 获得首页轮播图
        List<IndexCarouselVO> indexCarouselVOList = carouselService.getIndexCarousel();
        model.addAttribute("carousels",indexCarouselVOList);

        // 获得热销产品列表
        List<IndexGoodsVO> hotGoodsVOList = indexConfigService.getIndexGoodsModule(
                IndexConfigTypeEnum.INDEX_GOODS_HOT.getType(),
                Constants.INDEX_MODULE_GOODS_NUM);
        model.addAttribute("hotGoodses",hotGoodsVOList);

        // 获得新品上线列表
        List<IndexGoodsVO> newGoodsVOList = indexConfigService.getIndexGoodsModule(
                IndexConfigTypeEnum.INDEX_GOODS_NEW.getType(),
                Constants.INDEX_MODULE_GOODS_NUM);
        model.addAttribute("newGoodses",newGoodsVOList);

        // 获得为你推荐列表
        List<IndexGoodsVO> recommendGoodsVOList = indexConfigService.getIndexGoodsModule(
                IndexConfigTypeEnum.INDEX_GOODS_RECOMMEND.getType(),
                Constants.INDEX_MODULE_RECOMMEND_GOODS_NUM);
        model.addAttribute("recommendGoodses",newGoodsVOList);

        return "shop/index";
    }

}
