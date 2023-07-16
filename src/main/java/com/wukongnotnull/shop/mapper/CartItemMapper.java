package com.wukongnotnull.shop.mapper;

import com.wukongnotnull.shop.domain.CartItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 悟空非空也
* @description 针对表【shop_cart_item】的数据库操作Mapper
* @Entity com.wukongnotnull.shop.domain.CartItem  购物车明细表
*/
@Mapper
public interface CartItemMapper extends BaseMapper<CartItem> {

    /**
     * 根据 用户id 查询名下购物车中记录数量
     * @param userId 用户id
     * @return Integer
     */
    Integer selectCartItemCount(@Param("userId") Long userId);

    /**
     * 查询指定用户下的购物车明细记录列表（分页）
     * @param userId  userId
     * @param pageIndex 记录索引，从 0 开始
     * @param pageSize 页容量
     * @return List<CartItem>
     */
    List<CartItem> selectCartItemListPage(@Param("userId") Long userId,
                                          @Param("pageIndex") Integer pageIndex,
                                          @Param("pageSize") Integer pageSize);
}




