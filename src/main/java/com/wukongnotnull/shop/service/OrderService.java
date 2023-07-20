package com.wukongnotnull.shop.service;

import com.wukongnotnull.shop.service.bo.OrderDetailBO;
import com.wukongnotnull.shop.controller.vo.OrdinaryUserVO;
import com.wukongnotnull.shop.domain.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 悟空非空也
* @description 针对表【shop_order】的数据库操作Service
*/
public interface OrderService extends IService<Order> {
    /**
     * 生成订单，并返回订单单号
     * @return
     */
    String saveOrder(OrdinaryUserVO ordinaryUserVO);

    /**
     * 根据 orderNo 获得对应的订单详情
     * @param orderNo orderNo
     * @return OrderDetailBO
     */
    OrderDetailBO findOrderDetail(String orderNo);

}
