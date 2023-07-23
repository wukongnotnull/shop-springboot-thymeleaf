package com.wukongnotnull.shop.common;

/**
 * @author 悟空非空也
 */
public class Constants {

    /**
     * 首页一级分类的最大数量
     */
    public static final int INDEX_CATEGORY_MAX_NUM = 15;

    /**
     * 首页轮播图片的最大数量
     */
    public static final int INDEX_CAROUSEL_NUM = 3;

    /**
     * 首页商品模块中显示最大的商品数量
     */
    public static final int INDEX_MODULE_GOODS_NUM = 10;

    public static final int INDEX_MODULE_RECOMMEND_GOODS_NUM = 5;

    /**
     * 首页商品模块商品名称的最大长度
     */
    public static final int INDEX_MODULE_GOODS_NAME_LENGTH = 20;

    /**
     * 首页商品模块商品描述的最大长度
     */
    public static final int INDEX_MODULE_GOODS_INTRO_LENGTH = 30;

    /**
     * 本地静态图片资源路径，记得路径末尾加 /
     */
    public static final String FILE_UPLOAD_DIC = "/Users/wukong/IdeaProjects/shop-springboot-thymeleaf/src/main/resources/upload/";
    /**
     * 图片验证码 key
     */
    public static final String SHOP_VERIFY_CODE_KEY = "SHOP_VERIFY_CODE_KEY";
    /**
     * 登录成功 Key
     */
    public static final String LOGIN_SUCCESS_SESSION_KEY = "LOGIN_SUCCESS_SESSION_KEY" ;
    /**
     *  background admin account login session
     */
    public static final String ADMIN_LOGIN_SUCCESS_SESSION_KEY = "ADMIN_LOGIN_SUCCESS_SESSION_KEY";
}
