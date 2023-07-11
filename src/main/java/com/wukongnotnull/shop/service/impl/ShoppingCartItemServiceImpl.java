package com.wukongnotnull.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wukongnotnull.shop.domain.ShoppingCartItem;
import com.wukongnotnull.shop.service.ShoppingCartItemService;
import com.wukongnotnull.shop.mapper.ShoppingCartItemMapper;
import org.springframework.stereotype.Service;

/**
* @author wukong
* @description 针对表【shop_shopping_cart_item】的数据库操作Service实现
* @createDate 2023-07-11 15:42:27
*/
@Service
public class ShoppingCartItemServiceImpl extends ServiceImpl<ShoppingCartItemMapper, ShoppingCartItem>
    implements ShoppingCartItemService{

}




