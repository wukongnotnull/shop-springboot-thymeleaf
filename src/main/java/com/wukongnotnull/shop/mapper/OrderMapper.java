package com.wukongnotnull.shop.mapper;

import com.wukongnotnull.shop.domain.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 悟空非空也
 * @description 针对表【shop_order】的数据库操作Mapper
 */
@Mapper
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

    /**
     * modify order table
     * @param order
     * @return
     */
    int updateOrder(Order order);

    /**
     * select Orders Page by userId
     * @param pageIndex pageIndex
     * @param pageSize pageSize
     * @param userId userId
     * @return List<Order>
     */
    List<Order> selectOrdersPage(@Param("pageIndex") int pageIndex,
                                 @Param("pageSize") Integer pageSize,
                                 @Param("userId") Long userId);


    /**
     *  total counts in the name of the  login-in  user
     * @param userId userId
     * @return Integer
     */
    Integer selectTotalCounts(@Param("userId") Long userId);
}




