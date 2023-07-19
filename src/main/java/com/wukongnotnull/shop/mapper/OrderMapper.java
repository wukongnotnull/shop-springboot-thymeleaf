package com.wukongnotnull.shop.mapper;

import com.wukongnotnull.shop.domain.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 悟空非空也
 * @description 针对表【shop_order】的数据库操作Mapper
 */
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 插入订单信息
     *
     * @param order order
     * @return int
     */
    int insertOrder(Order order);

    /**
     * 根据 orderNo 查询订单信息
     *
     * @param orderNo orderNo
     * @return
     */
    Order selectOrder(@Param("orderNo") String orderNo);
}




