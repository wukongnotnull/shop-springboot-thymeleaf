package com.wukongnotnull.shop.controller.shop;

import com.wukongnotnull.shop.common.*;
import com.wukongnotnull.shop.controller.vo.CodeMessageEnum;
import com.wukongnotnull.shop.controller.vo.HttpResponseResult;
import com.wukongnotnull.shop.controller.vo.OrderDetailVO;
import com.wukongnotnull.shop.service.CartItemService;
import com.wukongnotnull.shop.service.bo.OrderDetailBO;
import com.wukongnotnull.shop.exception.ShopException;
import com.wukongnotnull.shop.controller.vo.OrdinaryUserVO;
import com.wukongnotnull.shop.service.OrderService;
import com.wukongnotnull.shop.service.bo.OrderDetailPageBO;
import com.wukongnotnull.shop.util.BeanUtil;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



/**
 * @author 悟空非空也
 */
@Controller
public class OrderController {
    @Resource
    private OrderService orderService;
    @Resource
    private CartItemService cartItemService;

    @GetMapping("/myOrders")
    public String findMyOrdersPage(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                                   @RequestParam(value = "pageSize",defaultValue = "4") Integer pageSize,
                                   HttpSession httpSession,
                                   Model model){
        // query all orders of login user in pages
        // return an object which includes order list and total pages and total number of order records
        OrdinaryUserVO ordinaryUserVO = (OrdinaryUserVO) httpSession.getAttribute(Constants.LOGIN_SUCCESS_SESSION_KEY);
        Long userId = ordinaryUserVO.getUserId();
        OrderDetailPageBO orderDetailPageBO =orderService.getOrdersPage(pageNo,pageSize,userId);
        model.addAttribute("orderPageResult",orderDetailPageBO);
        return "shop/order/my-orders";
    }

    @GetMapping("/paySuccess")
    @ResponseBody
    public HttpResponseResult<Object> paySuccess(@RequestParam("payMethod") Integer payMethod,
                                                 @RequestParam("orderNo") String orderNo,
                                                 HttpSession httpSession) {
        // 根据订单号和支付方式，修改订单状态为已支付等
        String result = orderService.modifyOrderWhenPaySuccess(orderNo, payMethod);

        if (!ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return HttpResponseResult.fail(CodeMessageEnum.UPDATE_FAIL);
        }

        // delete cart data after pay success
        OrdinaryUserVO ordinaryUserVO = (OrdinaryUserVO) httpSession.getAttribute(Constants.LOGIN_SUCCESS_SESSION_KEY);
        Long userId = ordinaryUserVO.getUserId();
        String dropResult = cartItemService.dropCartItems(userId);
        if (!ServiceResultEnum.SUCCESS.getResult().equals(dropResult)) {
            return HttpResponseResult.fail(CodeMessageEnum.DELETE_FAIL);
        }

        // drop data in session about ordinary user
        ordinaryUserVO.setTotalPrice(0);
        ordinaryUserVO.setCartItemCount(0);
        ordinaryUserVO.setTotalNum(0);

        return HttpResponseResult.success();

    }


    @GetMapping("/payPage")
    public String handlePayment(@RequestParam("orderNo") String orderNo,
                                @RequestParam("payMethod") Integer payMethod,
                                Model model,
                                HttpSession httpSession) {

        // 数据校验 ：1 该订单号是否是登录用户的订单 ；2 该订单是否处于未支付状态
        OrdinaryUserVO ordinaryUserVO = (OrdinaryUserVO) httpSession.getAttribute(Constants.LOGIN_SUCCESS_SESSION_KEY);

        OrderDetailBO orderDetailBO = orderService.findOrderDetail(orderNo);
        if (!orderDetailBO.getUserId().equals(ordinaryUserVO.getUserId())) {
            ShopException.fail("该订单号不是登录用户的订单");
        }

        if (!orderDetailBO.getPayStatus().equals(PayStatusEnum.PAY_READY.getPayStatus())) {
            ShopException.fail("该订单不是处于未支付状态");
        }
        //todo  TotalPrice 设计存入session中，这意味着 一个登录用户只能存在一个未支付订单，后续需要完善和优化
        model.addAttribute("totalPrice", orderDetailBO.getTotalPrice());
        model.addAttribute("orderNo", orderNo);
        if (payMethod == 1) {
            return "shop/pay/alipay";
        } else {
            return "shop/pay/wxpay";
        }

    }

    @GetMapping("/select/payMethod")
    public String selectPayMethod(@RequestParam("orderNo") String orderNo,
                                  Model model,
                                  HttpSession httpSession) {
        OrdinaryUserVO ordinaryUserVO = (OrdinaryUserVO) httpSession.getAttribute(Constants.LOGIN_SUCCESS_SESSION_KEY);
        // totalPrice
        model.addAttribute("totalPrice", ordinaryUserVO.getTotalPrice());
        model.addAttribute("orderNo", orderNo);


        return "shop/pay/pay-select";
    }

    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession httpSession) {
        // 添加用户id
        OrdinaryUserVO ordinaryUserVO = (OrdinaryUserVO) httpSession.getAttribute(Constants.LOGIN_SUCCESS_SESSION_KEY);
        // 生成一个订单号，然后根据订单号查询该订单信息
        String orderNo = orderService.saveOrder(ordinaryUserVO);

        // ToDo 生成订单后，购物车中的记录应该是全部删除


        return "redirect:/orders/" + orderNo;
    }

    @RequestMapping("/orders/{orderNo}")
    public String getOrder(@PathVariable("orderNo") String orderNo, Model model) {
        OrderDetailBO orderDetailBO = orderService.findOrderDetail(orderNo);
        if (orderDetailBO == null) {
            ShopException.fail("orderDetailBO 为 null ");
        }
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        BeanUtil.copyProperties(orderDetailBO, orderDetailVO);
        // PayStatus to string
        String payStatusName = PayStatusEnum.getPayStatusName(orderDetailBO.getPayStatus());
        orderDetailVO.setPayStatusString(payStatusName);

        // payMethod to string
        String payMethodName = PayMethodEnum.getPayMethodName(orderDetailBO.getPayMethod());
        System.out.println("payMethodName = " + payMethodName);
        orderDetailVO.setPayMethodString(payMethodName);

        // orderStatus to string
        String orderStatusName = OrderStatusEnum.getOrderStatusName(orderDetailBO.getOrderStatus());
        orderDetailVO.setOrderStatusString(orderStatusName);


        model.addAttribute("orderDetailVO", orderDetailVO);
        return "shop/order/order-detail";


    }

}
