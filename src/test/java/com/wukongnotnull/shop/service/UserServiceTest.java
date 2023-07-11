package com.wukongnotnull.shop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


// 悟空非空也（B站/公众号/知乎）
@SpringBootTest

class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void countTest(){
        long count = userService.count();
        System.out.println("count = " + count);
    }

}