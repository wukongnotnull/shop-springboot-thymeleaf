package com.wukongnotnull.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wukongnotnull.shop.common.*;
import com.wukongnotnull.shop.service.bo.CartItemBO;
import com.wukongnotnull.shop.service.bo.OrderDetailBO;
import com.wukongnotnull.shop.domain.GoodsDetail;
import com.wukongnotnull.shop.domain.OrderItem;
import com.wukongnotnull.shop.exception.ShopException;
import com.wukongnotnull.shop.mapper.GoodsDetailMapper;
import com.wukongnotnull.shop.service.bo.OrderDetailPageBO;
import com.wukongnotnull.shop.util.BeanUtil;
import com.wukongnotnull.shop.controller.vo.OrdinaryUserVO;
import com.wukongnotnull.shop.domain.CartItem;
import com.wukongnotnull.shop.domain.Order;
import com.wukongnotnull.shop.mapper.CartItemMapper;
import com.wukongnotnull.shop.mapper.OrderItemMapper;
import com.wukongnotnull.shop.service.OrderService;
import com.wukongnotnull.shop.mapper.OrderMapper;
import com.wukongnotnull.shop.util.NumberUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wukong
 * @description 针对表【shop_order】的数据库操作Service实现
 * @createDate 2023-07-11 15:41:29
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
        implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private CartItemMapper cartItemMapper;
    @Resource
    private OrderItemMapper orderItemMapper;
    @Resource
    private GoodsDetailMapper goodsDetailMapper;

    /**
     * 生成订单，并返回订单单号
     *
     * @return
     */
    @Override
    public String saveOrder(OrdinaryUserVO ordinaryUserVO) {

        Order order = new Order();

        // user_id
        order.setUserId(ordinaryUserVO.getUserId());

        // 生成订单单号
        String orderNo = NumberUtil.genOrderNo();
        order.setOrderNo(orderNo);

        // 设置总价格
        order.setTotalPrice(ordinaryUserVO.getTotalPrice());

        // 支付状态：未支付
        order.setPayStatus(PayStatusEnum.PAY_READY.getPayStatus());

        // 支付方法
        order.setPayMethod(PayMethodEnum.NO_TYPE_PAY.getPayMethod());

        // 支付时间
        order.setPayTime(new Date());

        // 订单状态
        order.setOrderStatus(OrderStatusEnum.READY_PAY.getOrderStatus());

        // 订单body 调用第三方支付接口
        String extraInfo = "";
        order.setExtraInfo(extraInfo);

        // 用户名 手机号 地址
        order.setUserName(ordinaryUserVO.getNickName());
        order.setUserPhone(ordinaryUserVO.getPhone());
        order.setUserAddress(ordinaryUserVO.getAddress());

        int i = orderMapper.insertOrder(order);
        if (i != 1) {
            // 添加失败
            System.out.println("添加失败");
            ShopException.fail("在 shop_order 表中添加失败");
        }

        // 根据 userId 查询购物车中的商品列表
        List<CartItem> cartItemList = cartItemMapper.selectCartItemList(ordinaryUserVO.getUserId());

        List<CartItemBO> cartItemBOList = new ArrayList<>();
        for (CartItem cartItem : cartItemList) {
            CartItemBO cartItemBO = new CartItemBO();
            BeanUtil.copyProperties(cartItem, cartItemBO);
            // goods_id 查询对应的goodsDetail
            GoodsDetail goodsDetail = goodsDetailMapper.selectGoodsDetail(cartItem.getGoodsId());
            cartItemBO.setGoodsName(goodsDetail.getGoodsName());
            cartItemBO.setGoodsCoverImg(goodsDetail.getGoodsCoverImg());
            cartItemBO.setSellingPrice(goodsDetail.getSellingPrice());
            // order_id
            cartItemBO.setOrderId(order.getOrderId());
            cartItemBOList.add(cartItemBO);
        }

        // 在 shop_order_item 表中批量插入订单信息
        //  int i = orderMapper.insertOrder(order); 使用 useGeneratedKeys = true  propertyKey =order_id
        int result = orderItemMapper.insertBatch(cartItemBOList);
        if (result < 1) {
            System.out.println("在 shop_order_item 表中批量插入订单信息失败");
            ShopException.fail("在 shop_order_item 表中批量插入订单信息失败");
        }


        return order.getOrderNo();
    }

    /**
     * 根据 orderNo 获得对应的订单详情
     *
     * @param orderNo orderNo
     * @return OrderDetailBO
     */
    @Override
    public OrderDetailBO findOrderDetail(String orderNo) {

        OrderDetailBO orderDetailBO = new OrderDetailBO();
        Order order = orderMapper.selectOrder(orderNo);
        BeanUtil.copyProperties(order, orderDetailBO);
        List<OrderItem> orderItemList = orderItemMapper.selectOrderItems(order.getOrderId());
        orderDetailBO.setOrderItemList(orderItemList);
        return orderDetailBO;
    }

    /**
     * 当支付成功后，修改订单信息
     *
     * @param orderNo   orderNo
     * @param payMethod payMethod
     * @return String
     */
    @Override
    public String modifyOrderWhenPaySuccess(String orderNo, Integer payMethod) {
        Order order = orderMapper.selectOrder(orderNo);
        if (order == null) {
            ShopException.fail("根据订单号，查询的订单不存在。。");
        }
        if (!order.getPayStatus().equals(PayStatusEnum.PAY_READY.getPayStatus())) {
            ShopException.fail("该订单不是未支付状态下~~");
        }
        // modify pay_status , pay_method ,pay_time , order_status , update_time ,attribute etc.
        Order orderWhenPaySuccess = new Order();
        orderWhenPaySuccess.setOrderId(order.getOrderId());
        orderWhenPaySuccess.setPayStatus(PayStatusEnum.PAY_SUCCESS.getPayStatus());
        orderWhenPaySuccess.setPayMethod(payMethod);
        orderWhenPaySuccess.setPayTime(new Date());
        orderWhenPaySuccess.setOrderStatus(OrderStatusEnum.HAVE_PAID.getOrderStatus());
        orderWhenPaySuccess.setUpdateTime(new Date());
        // making a modification
        int i = orderMapper.updateOrder(orderWhenPaySuccess);
        if (i == 1) {
            return ServiceResultEnum.SUCCESS.getResult();
        }

        return ServiceResultEnum.ERROR.getResult();
    }

    /**
     * query orders of login user by page
     *
     * @param pageNo   current page no
     * @param pageSize record count per page
     * @param userId   id of login user
     * @return OrderDetailBO
     * <p>
     * {
     * OrderDetailBOList:[
     * { // OrderDetailBO
     * key: value,
     * key2:value,
     * orderItemList：[
     * {},{},{}
     * ]
     * }
     * ],
     * totalPages: 10,
     * totalCounts: 100
     * }
     */
    @Override
    public OrderDetailPageBO getOrdersPage(Integer pageNo, Integer pageSize, Long userId) {
        int pageIndex = (pageNo - 1) * pageSize;
        List<Order> orderList = orderMapper.selectOrdersPage(pageIndex, pageSize, userId);
        // by orderId in orderList, query order_item table
        if (orderList == null) {
            ShopException.fail("orderList is  null .....");
        }

        List<OrderDetailBO> orderDetailBOList = new ArrayList<>();
        for (Order order : orderList) {
            OrderDetailBO orderDetailBO = new OrderDetailBO();
            BeanUtil.copyProperties(order, orderDetailBO);

            List<OrderItem> orderItems = orderItemMapper.selectOrderItems(order.getOrderId());
            orderDetailBO.setOrderItemList(orderItems);
            // OrderStatus to OrderStatusString
            orderDetailBO.setOrderStatusString(OrderStatusEnum.getOrderStatusName(order.getOrderStatus()));
            orderDetailBOList.add(orderDetailBO);
        }

        //   totalCounts
        int totalCounts = orderMapper.selectTotalCounts(userId);

        // totalPages
        int totalPages = 0;
        if (totalCounts % pageSize == 0) {
            totalPages = totalCounts / pageSize;
        } else {
            totalPages = totalCounts / pageSize + 1;
        }

        OrderDetailPageBO orderDetailPageBO = new OrderDetailPageBO();
        orderDetailPageBO.setTotalPages(totalPages);
        orderDetailPageBO.setTotalCounts(totalCounts);
        orderDetailPageBO.setPageNo(pageNo);
        orderDetailPageBO.setList(orderDetailBOList);

        return orderDetailPageBO;
    }
}






