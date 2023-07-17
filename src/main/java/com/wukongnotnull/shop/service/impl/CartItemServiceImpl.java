package com.wukongnotnull.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wukongnotnull.shop.common.Constants;
import com.wukongnotnull.shop.common.ServiceResultEnum;
import com.wukongnotnull.shop.controller.vo.CartItemVO;
import com.wukongnotnull.shop.domain.CartItem;
import com.wukongnotnull.shop.domain.GoodsDetail;
import com.wukongnotnull.shop.domain.OrdinaryUser;
import com.wukongnotnull.shop.mapper.GoodsDetailMapper;
import com.wukongnotnull.shop.service.CartItemService;
import com.wukongnotnull.shop.mapper.CartItemMapper;
import com.wukongnotnull.shop.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
* @author wukong
* @description 针对表【shop_cart_item】的数据库操作Service实现
* @createDate 2023-07-15 17:57:27
*/
@Service
public class CartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItem>
    implements CartItemService{

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private GoodsDetailMapper goodsDetailMapper;


    @Override
    public Integer getCartItemCount(Long userId){
        return cartItemMapper.selectCartItemCount(userId);
    }

    /**
     * 根据购物车记录id 删除该记录
     *
     * @param cartItemId cartItemId
     * @return String
     */
    @Override
    public String deleteCartItem(Long cartItemId) {
       int i = cartItemMapper.deleteCartItem(cartItemId);
        if (i == 1) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.ERROR.getResult();
    }

    /**
     * 修改购物车记录中的商品数量
     *
     * @param cartItem cartItem
     * @return String
     */
    @Override
    public String updateCartItem(CartItem cartItem) {
       int i =  cartItemMapper.updateCartItem(cartItem);
       if(i == 1) {
           return  ServiceResultEnum.SUCCESS.getResult();
       }
           return  ServiceResultEnum.ERROR.getResult();

    }


    /**
     * 查询指定用户下的购物车明细记录列表（分页）
     *
     * @param userId userId
     * @param pageNo 当前页码
     * @param pageSize  页容量
     * @return List<CartItemVO>
     */
    @Override
    public List<CartItemVO> findCartItemListPage(Long userId,Integer pageNo,Integer pageSize
                                                 ) {

        // 从第几条记录开始
        Integer pageIndex = (pageNo - 1) * pageSize;
        // 根据用户id 分页查询 购物车明细记录列表
        List<CartItem> cartItemListPage =  cartItemMapper.selectCartItemListPage(userId,pageIndex,pageSize);
        // 非空判断
        if (CollectionUtils.isEmpty(cartItemListPage)) {
            return  null;
        }

        // 获得对应的商品id列表
        List<Long> goodsIdList = cartItemListPage.stream().map(CartItem::getGoodsId).collect(Collectors.toList());

        // 获得对应的商品列表(商品处于在售中)
        List<GoodsDetail> goodsDetailList = goodsDetailMapper.getGoodsDetailListOnSale(goodsIdList);

        // 非空判断
        if (CollectionUtils.isEmpty(goodsDetailList)) {
            return  null;
        }

        // 转成<k,v> map 类型 k: goodsId
        Map<Long, GoodsDetail> goodsDetailMap = goodsDetailList.stream().collect(Collectors.toMap(
                GoodsDetail::getGoodsId,
                Function.identity(),
                (entity1, entity2) -> entity1));

        //  to vo list
        List<CartItemVO> cartItemVOList = BeanUtil.copyList(cartItemListPage, CartItemVO.class);
        for(CartItemVO cartItemVO: cartItemVOList){
            if (goodsDetailMap.containsKey(cartItemVO.getGoodsId())) {
                GoodsDetail tmpGoodsDetail = goodsDetailMap.get(cartItemVO.getGoodsId());

                // 设置 3 个属性
                cartItemVO.setGoodsName(tmpGoodsDetail.getGoodsName());
                cartItemVO.setGoodsCoverImg(tmpGoodsDetail.getGoodsCoverImg());
                cartItemVO.setSellingPrice(tmpGoodsDetail.getSellingPrice());

            }
        }

        return cartItemVOList;
    }

    /**
     * 某用户添加购物车明细记录
     *
     * @param cartItem    cartItem
     * @return String
     */
    @Override
    public String addCartItem(CartItem cartItem) {

        int i = cartItemMapper.insertCartItem(cartItem);

        if (i == 1) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.ERROR.getResult();
    }
}




