package com.wukongnotnull.shop.service;

import com.wukongnotnull.shop.domain.OrdinaryUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author wukong
* @description 针对表【shop_ordinary_user】的数据库操作Service
* @createDate 2023-07-14 16:01:05
*/
public interface OrdinaryUserService extends IService<OrdinaryUser> {

    /**
     *  根据用户名和密码添加用户（注册）
     * @param loginName
     * @param password
     * @return
     */
    String addOrdinaryUser(String loginName, String password);


}
