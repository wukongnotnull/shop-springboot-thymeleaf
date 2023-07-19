package com.wukongnotnull.shop.common;

/**
 * @author 悟空非空也
 * 订单状态:0.待支付 1.已支付 2.配货完成 3:出库成功 4.交易成功 -1.手动关闭 -2.超时关闭 -3.商家关闭
 */
public enum OrderStatusEnum {
    READY_PAY(0,"待支付"),
    HAVE_PAID(1,"已支付"),
    DISTRIBUTION_COMPLETED(2,"配货完成"),
    DELIVERY_SUCCESS(3,"出库成功"),
    TRANSACTION_SUCCESS(4,"交易成功"),
    MANUAL_CLOSE(-1,"手动关闭"),
    OVERTIME_CLOSE(-2,"超时关闭"),
    SELLER_CLOSE(-3,"商家关闭")
    ;
    private int orderStatus;
    private String name;

    OrderStatusEnum(int orderStatus, String name) {
        this.orderStatus = orderStatus;
        this.name = name;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
