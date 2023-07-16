package com.wukongnotnull.shop.controller.shop;

import com.wukongnotnull.shop.common.Constants;
import com.wukongnotnull.shop.controller.vo.CartItemVO;
import com.wukongnotnull.shop.controller.vo.OrdinaryUserVO;
import com.wukongnotnull.shop.domain.CartItem;
import com.wukongnotnull.shop.domain.OrdinaryUser;
import com.wukongnotnull.shop.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author wukong
 */
@Controller
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;


    /**
     * 查看指定用户下的购物车记录情况(分页呈现)
     * @return
     */
    @GetMapping("/shop-cart")
    public String getCartItemListPage(HttpSession httpSession,
                                      Model model,
                                      @RequestParam(value = "pageNo",required = false,defaultValue = "1")  Integer pageNo,
                                      @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize){
        // 查询指定用户下的购物车明细记录
        OrdinaryUserVO ordinaryUserVO = (OrdinaryUserVO) httpSession.getAttribute(Constants.LOGIN_SUCCESS_SESSION_KEY);
        List<CartItemVO> cartItemVOListPage = cartItemService.findCartItemListPage(ordinaryUserVO.getUserId(),pageNo,pageSize);

        // 统计指定用户下购物车中商品总数
        // 统计指定用户下购物车中总价
        List<CartItemVO> cartItemVOListAll = cartItemService.findCartItemListPage(ordinaryUserVO.getUserId(), 1, 100);
        int goodsTotalPrice = 0;
        int goodsTotalCount = 0;
        for(CartItemVO cartItemVO: cartItemVOListAll){
            goodsTotalPrice += cartItemVO.getSellingPrice() * cartItemVO.getGoodsCount();
            goodsTotalCount += cartItemVO.getGoodsCount();
        }

        // model 返回数据给页面
        model.addAttribute("cartItemVOListPage",cartItemVOListPage);
        model.addAttribute("goodsTotalCount",goodsTotalCount);
        model.addAttribute("goodsTotalPrice",goodsTotalPrice);

        return "shop/cart";

    }
}
