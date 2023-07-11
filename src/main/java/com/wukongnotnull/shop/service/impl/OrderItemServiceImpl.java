package com.wukongnotnull.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wukongnotnull.shop.domain.OrderItem;
import com.wukongnotnull.shop.service.OrderItemService;
import com.wukongnotnull.shop.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;

/**
* @author wukong
* @description 针对表【shop_order_item】的数据库操作Service实现
* @createDate 2023-07-11 15:41:29
*/
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem>
    implements OrderItemService{

}




