package com.wukongnotnull.shop.mapper;

import com.wukongnotnull.shop.domain.Order;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderMapperTest {

    @Resource
    private OrderMapper orderMapper;

    @Test
    void insertOrder() {
        Order order = new Order();
        order.setUserName("wukong3");
        order.setOrderNo("23331523");
        order.setUserId(11l);
        order.setTotalPrice(1223);
        int i = orderMapper.insertOrder(order);
        System.out.println("i = " + i);
        // sqlxml 文件上添加了 useGeneratedkeys = "true" 属性 ,添加的实例，返回主键的值
        System.out.println("order = " + order);
    }
}