package com.wukongnotnull.shop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrdinaryUserServiceTest {
    @Autowired
    private OrdinaryUserService ordinaryUserService;

    @Test
    void addOrdinaryUser() {
        String s = ordinaryUserService.addOrdinaryUser("15311111111", "1234");
        System.out.println("s = " + s);
    }

    @Test
    void handleLogin() {
    }
}