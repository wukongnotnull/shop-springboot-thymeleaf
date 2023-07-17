package com.wukongnotnull.shop.service;

import com.wukongnotnull.shop.domain.OrdinaryUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrdinaryUserServiceTest {
    @Autowired
    private OrdinaryUserService ordinaryUserService;

    @Test
    void updateAddress() {
        OrdinaryUser ordinaryUser = new OrdinaryUser();
        ordinaryUser.setUserId(6L);
        ordinaryUser.setAddress("XXXXXXXXXXXXXX");
       // String s = ordinaryUserService.updateAddress(ordinaryUser);
        //System.out.println("修改地址：s = " + s);
    }

    @Test
    void addOrdinaryUser() {
        String s = ordinaryUserService.addOrdinaryUser("15311111111", "1234");
        System.out.println("s = " + s);
    }

    @Test
    void handleLogin() {
    }
}