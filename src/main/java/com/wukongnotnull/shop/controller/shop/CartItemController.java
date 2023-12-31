package com.wukongnotnull.shop.controller.shop;

import com.wukongnotnull.shop.common.Constants;
import com.wukongnotnull.shop.common.ServiceResultEnum;
import com.wukongnotnull.shop.controller.vo.CartItemVO;
import com.wukongnotnull.shop.controller.vo.CodeMessageEnum;
import com.wukongnotnull.shop.controller.vo.HttpResponseResult;
import com.wukongnotnull.shop.controller.vo.OrdinaryUserVO;
import com.wukongnotnull.shop.domain.CartItem;
import com.wukongnotnull.shop.exception.ShopException;
import com.wukongnotnull.shop.service.CartItemService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * @author 悟空非空也
 */
@Controller
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    /**
     * 结算
     */
    @GetMapping("/shop-cart/order-settle")
    public String settle(HttpSession httpSession, Model model){
        // 获得购物车明细vo列表
        // 查询指定用户下的购物车明细记录
        OrdinaryUserVO ordinaryUserVO = (OrdinaryUserVO) httpSession.getAttribute(Constants.LOGIN_SUCCESS_SESSION_KEY);
        if (ordinaryUserVO == null) {
            ShopException.fail("ordinaryUserVO 不能为空");
        }
        List<CartItemVO> cartItemVOList = null;
        int goodsTotalPrice = 0;
        int goodsTotalCount = 0;

        // query cart item list
        cartItemVOList = cartItemService.findCartItemList(ordinaryUserVO.getUserId());
        // 统计指定用户下购物车中商品总数
        // 统计指定用户下购物车中总价

        for(CartItemVO cartItemVO: cartItemVOList){
            goodsTotalPrice += cartItemVO.getSellingPrice() * cartItemVO.getGoodsCount();
            goodsTotalCount += cartItemVO.getGoodsCount();
        }


        // 将  goodsTotalPrice 存入 session 中
        ordinaryUserVO.setTotalPrice(goodsTotalPrice);

        // model 返回数据给页面
        model.addAttribute("cartItemVOListPage",cartItemVOList);
        model.addAttribute("goodsTotalCount",goodsTotalCount);
        model.addAttribute("goodsTotalPrice",goodsTotalPrice);

        return "shop/order/order-settle";
    }


    /**
     * 修改购物车记录中的商品数量
     *
     */
    @PutMapping("/shop-cart")
    @ResponseBody
    public HttpResponseResult<Object> updateCartItem(@RequestBody CartItem cartItem){
        // 后端数据校验

        // 调用service 层
        String result = cartItemService.updateCartItem(cartItem);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return HttpResponseResult.success();
        }
        return HttpResponseResult.fail(CodeMessageEnum.UPDATE_FAIL);
    }


    /**
     * 根据id 删除购物车中的记录
     */
    @DeleteMapping("/shop-cart/{id}")
    @ResponseBody
    public  HttpResponseResult<Object> deleteCartItem(@PathVariable(value = "id") Long cartItemId){
        // 后端数据校验

        // call service 层
        String result = cartItemService.deleteCartItem(cartItemId);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)){
            return HttpResponseResult.success();
        }
       return HttpResponseResult.fail(CodeMessageEnum.DELETE_FAIL);

    }

    /**
     * 加入购物车功能 ,ajax 请求
     */
    @PostMapping("/shop-cart")
    @ResponseBody
    public HttpResponseResult<Object> saveToCart(
            @RequestBody CartItem cartItem,HttpSession httpSession){
        // ToDo 后端数据校验

        // 调用service 层
        OrdinaryUserVO ordinaryUserVO = (OrdinaryUserVO) httpSession.getAttribute(Constants.LOGIN_SUCCESS_SESSION_KEY);
        cartItem.setUserId(ordinaryUserVO.getUserId());
        cartItem.setCreateBy(ordinaryUserVO.getUserId());
        String result =cartItemService.addCartItem(cartItem);

        // 添加结果进行判断
        if (!result.equals(ServiceResultEnum.SUCCESS.getResult())) {
            // 添加失败
            return HttpResponseResult.fail(CodeMessageEnum.ADD_FAIL);
        }

        return  HttpResponseResult.success();
    }

    /**
     * 查看指定用户下的购物车记录情况
     * @return
     */
    @GetMapping("/shop-cart")
    public String getCartItemList(HttpSession httpSession,
                                      Model model
                                     ){
        // 查询指定用户下的购物车明细记录
        OrdinaryUserVO ordinaryUserVO = (OrdinaryUserVO) httpSession.getAttribute(Constants.LOGIN_SUCCESS_SESSION_KEY);


        if (ordinaryUserVO == null) {
            return "redirect:/login";
        }

        List<CartItemVO> cartItemVOList = cartItemService.findCartItemList(ordinaryUserVO.getUserId());

        if (cartItemVOList == null) {
            // model 返回数据给页面
            model.addAttribute("cartItemVOListPage",null);
            model.addAttribute("goodsTotalCount",0);
            model.addAttribute("goodsTotalPrice",0);
            return "shop/cart";
        }
        // 统计指定用户下购物车中商品总数
        // 统计指定用户下购物车中总价
        int goodsTotalPrice = 0;
        int goodsTotalCount = 0;
        for(CartItemVO cartItemVO: cartItemVOList){
            goodsTotalPrice += cartItemVO.getSellingPrice() * cartItemVO.getGoodsCount();
            goodsTotalCount += cartItemVO.getGoodsCount();
        }

        // model 返回数据给页面
        model.addAttribute("cartItemVOListPage",cartItemVOList);
        model.addAttribute("goodsTotalCount",goodsTotalCount);
        model.addAttribute("goodsTotalPrice",goodsTotalPrice);

        return "shop/cart";

    }
}
