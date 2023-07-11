package com.wukongnotnull.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wukongnotnull.shop.domain.Order;
import com.wukongnotnull.shop.service.OrderService;
import com.wukongnotnull.shop.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author wukong
* @description 针对表【shop_order】的数据库操作Service实现
* @createDate 2023-07-11 15:41:29
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}




