package com.wukongnotnull.shop.controller.shop;

import com.wukongnotnull.shop.common.OrderStatusEnum;
import com.wukongnotnull.shop.common.PayMethodEnum;
import com.wukongnotnull.shop.common.PayStatusEnum;
import com.wukongnotnull.shop.controller.vo.OrderDetailVO;
import com.wukongnotnull.shop.service.bo.OrderDetailBO;
import com.wukongnotnull.shop.common.Constants;
import com.wukongnotnull.shop.exception.ShopException;
import com.wukongnotnull.shop.controller.vo.OrdinaryUserVO;
import com.wukongnotnull.shop.service.OrderService;
import com.wukongnotnull.shop.util.BeanUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author 悟空非空也
 */
@Controller
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/select/payMethod")
    public String selectPayMethod(@RequestParam("orderNo") String orderNo) {

        return "shop/pay-select";
    }

    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession httpSession) {
        // 添加用户id
        OrdinaryUserVO ordinaryUserVO = (OrdinaryUserVO) httpSession.getAttribute(Constants.LOGIN_SUCCESS_SESSION_KEY);
        // 生成一个订单号，然后根据订单号查询该订单信息
        String orderNo = orderService.saveOrder(ordinaryUserVO);

        // ToDo 生成订单后，购物车中的记录应该是全部删除


        return "redirect:/order-detail/" + orderNo;
    }

    @RequestMapping("/order-detail/{orderNo}")
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
        orderDetailVO.setPayMethodString(payStatusName);

        // orderStatus to string
        String orderStatusName = OrderStatusEnum.getOrderStatusName(orderDetailBO.getOrderStatus());
        orderDetailVO.setOrderStatusString(orderStatusName);


        model.addAttribute("orderDetailVO", orderDetailVO);
        return "shop/order-detail";


    }

}
