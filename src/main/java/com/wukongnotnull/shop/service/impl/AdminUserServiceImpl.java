package com.wukongnotnull.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wukongnotnull.shop.domain.AdminUser;
import com.wukongnotnull.shop.service.AdminUserService;
import com.wukongnotnull.shop.mapper.AdminUserMapper;
import com.wukongnotnull.shop.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author wukong
* @description 针对表【shop_admin_user】的数据库操作Service实现
* @createDate 2023-07-11 15:37:22
*/
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser>
    implements AdminUserService{

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser doLogin(String userName, String password) {
        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
        return adminUserMapper.doLogin(userName, passwordMd5);

    }

    @Override
    public Integer register(AdminUser adminUser) {
        String passwordMd5 = MD5Util.MD5Encode(adminUser.getLoginPassword(), "UTF-8");
        adminUser.setLoginPassword(passwordMd5);
        return adminUserMapper.addAdminUser(adminUser);

    }
}




