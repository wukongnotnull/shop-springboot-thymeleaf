package com.wukongnotnull.shop.service;

import com.wukongnotnull.shop.domain.AdminUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdminUserServiceTest {
    @Resource
    private AdminUserService adminUserService;

    @Test
    void doLogin() {
    }

    @Test
    void register() {
        AdminUser adminUser = new AdminUser();
        adminUser.setLoginUserName("admin");
        adminUser.setLoginPassword("admin");
        adminUser.setNickName("悟空非空也");
        adminUser.setLocked(0);
        Integer register = adminUserService.register(adminUser);

    }
}