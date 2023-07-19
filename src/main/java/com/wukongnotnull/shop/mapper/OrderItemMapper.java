package com.wukongnotnull.shop.mapper;

import com.wukongnotnull.shop.bo.CartItemBO;
import com.wukongnotnull.shop.domain.CartItem;
import com.wukongnotnull.shop.domain.OrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 悟空非空也
* @description 针对表【shop_order_item】的数据库操作Mapper
*/
public interface OrderItemMapper extends BaseMapper<OrderItem> {

    /**
     *  批量插入数据到 order_item 表中
     * @param cartItemBOList cartItemBOList
     * @return 是否插入成功
     */
    int insertBatch(@Param("cartItemBOList") List<CartItemBO> cartItemBOList);


}




