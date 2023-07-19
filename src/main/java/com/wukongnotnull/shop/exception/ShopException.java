package com.wukongnotnull.shop.exception;

/**
 * @author 悟空非空也
 * 自定义异常类
 */
public class ShopException extends  RuntimeException {

    private ShopException(){
        super();
    }

    private ShopException(String msg){
        super(msg);
    }

    public static void fail(String message) {
        throw   new ShopException(message);
    }

}
