package com.wukongnotnull.shop.service;

import com.wukongnotnull.shop.service.bo.OrderDetailBO;
import com.wukongnotnull.shop.controller.vo.OrdinaryUserVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class OrderServiceTest {

    @Resource
    private OrderService orderService;

    @Test
    void saveOrder() {
        OrdinaryUserVO ordinaryUserVO = new OrdinaryUserVO();
        ordinaryUserVO.setUserId(11L);
        ordinaryUserVO.setTotalPrice(100);
        ordinaryUserVO.setNickName("wwwwukkkk");
        ordinaryUserVO.setPhone("12341234123");
        ordinaryUserVO.setAddress("nanjing");

        String s = orderService.saveOrder(ordinaryUserVO);
        System.out.println("ordinaryUserVO = " + ordinaryUserVO);
        System.out.println("生成订单 ：s = " + s);
    }

    @Test
    void findOrderDetail() {
        String orderNo = "16898177179202429";
        OrderDetailBO orderDetailBO = orderService.findOrderDetail(orderNo);
        System.out.println("orderDetailBO = " + orderDetailBO);
    }


}