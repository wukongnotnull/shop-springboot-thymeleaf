package com.wukongnotnull.shop.controller.shop;

import com.wukongnotnull.shop.common.Constants;
import com.wukongnotnull.shop.controller.vo.GoodsDetailVO;
import com.wukongnotnull.shop.controller.vo.OrdinaryUserVO;
import com.wukongnotnull.shop.exception.ParamsException;
import com.wukongnotnull.shop.exception.ShopException;
import com.wukongnotnull.shop.service.CartItemService;
import com.wukongnotnull.shop.service.GoodsDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

/**
 * @author 悟空非空也
 */
@Controller
public class GoodsDetailController {


    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private GoodsDetailService goodsDetailService;

    @GetMapping({"/goods/detail/{goodsId}"})
    public String getGoodsDetail(Model model,
                                 @PathVariable("goodsId") Long goodsId,
                                             HttpSession httpSession
    ) throws ParamsException {
        // 若是 goodsId 小于 1
        if(goodsId < 1){
            // 抛出一个自定义异常，统一全局异常。。。
            ShopException.fail("goodsId 小于 1,抛出异常！");
        }

        System.out.println("goodsId = " + goodsId);
        GoodsDetailVO goodsDetailVO = goodsDetailService.findGoodsDetailOnSale(goodsId);

        // 更新购物车数字标签的显示
        // 更新首页购物车记录数的显示()
        if (httpSession.getAttribute(Constants.LOGIN_SUCCESS_SESSION_KEY) != null) {
            OrdinaryUserVO ordinaryUserVO = (OrdinaryUserVO) httpSession.getAttribute(Constants.LOGIN_SUCCESS_SESSION_KEY);
            Integer cartItemCount = cartItemService.getCartItemCount(ordinaryUserVO.getUserId());
            ordinaryUserVO.setCartItemCount(cartItemCount);
            httpSession.setAttribute(Constants.LOGIN_SUCCESS_SESSION_KEY,ordinaryUserVO);
        }


        model.addAttribute("goodsDetail",goodsDetailVO);
        // todo 商品详情页的轮播图，需要完善一下
        return "shop/goods-detail";
    }










}
