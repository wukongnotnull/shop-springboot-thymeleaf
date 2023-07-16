package com.wukongnotnull.shop.service;

import com.wukongnotnull.shop.domain.OrdinaryUser;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpSession;

/**
* @author wukong
* @description 针对表【shop_ordinary_user】的数据库操作Service
* @createDate 2023-07-14 16:01:05
*/
public interface OrdinaryUserService extends IService<OrdinaryUser> {

    /**
     *  根据用户名和密码添加用户（注册）
     * @param loginName loginName
     * @param password password
     * @return  String
     */
    String addOrdinaryUser(String loginName, String password);


    /**
     *  进行登录操作
     * @param loginName 登录账号名
     * @param password  登录密码
     * @return String
     */
    String handleLogin(String loginName,String password, HttpSession httpSession);
}
