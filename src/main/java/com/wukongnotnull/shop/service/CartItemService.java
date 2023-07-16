package com.wukongnotnull.shop.service;

import com.wukongnotnull.shop.controller.vo.CartItemVO;
import com.wukongnotnull.shop.domain.CartItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author wukong
* @description 针对表【shop_cart_item】的数据库操作Service
*/
public interface CartItemService extends IService<CartItem> {

    /**
     * 查询指定用户下的购物车明细记录列表（分页）
     *
     * @param userId userId
     * @param pageNo 当前页码
     * @param pageSize  页容量
     * @return List<CartItemVO>
     */
    List<CartItemVO> findCartItemListPage(Long userId,Integer pageNo,Integer pageSize);
}
