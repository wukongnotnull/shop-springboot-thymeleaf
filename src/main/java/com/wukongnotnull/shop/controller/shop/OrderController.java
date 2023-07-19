package com.wukongnotnull.shop.controller.shop;

import com.wukongnotnull.shop.bo.OrderDetailBO;
import com.wukongnotnull.shop.common.Constants;
import com.wukongnotnull.shop.domain.Order;
import com.wukongnotnull.shop.vo.OrdinaryUserVO;
import com.wukongnotnull.shop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author 悟空非空也
 */
@Controller
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession httpSession) {
        // 添加用户id
        OrdinaryUserVO ordinaryUserVO = (OrdinaryUserVO) httpSession.getAttribute(Constants.LOGIN_SUCCESS_SESSION_KEY);
        // 生成一个订单号，然后根据订单号查询该订单信息
        String orderNo = orderService.saveOrder(ordinaryUserVO);

        // ToDo 生成订单后，购物车中的记录应该是全部删除


        return "redirect:/order/" + orderNo;
    }

    @RequestMapping("/order/{orderNo}")
    public String getOrder(@PathVariable("orderNo") String orderNo, Model model) {
        OrderDetailBO orderDetailBO = orderService.findOrderDetail(orderNo);
        return  "shop/order-detail";


    }

}
