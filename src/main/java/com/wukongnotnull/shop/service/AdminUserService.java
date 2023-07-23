package com.wukongnotnull.shop.service;

import com.wukongnotnull.shop.domain.AdminUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wukongnotnull.shop.service.impl.AdminUserServiceImpl;

/**
* @author wukong
* @description 针对表【shop_admin_user】的数据库操作Service
* @createDate 2023-07-11 15:37:22
*/
public interface AdminUserService extends IService<AdminUser> {


    AdminUser doLogin(String userName, String password);

    Integer register(AdminUser adminUser);
}
