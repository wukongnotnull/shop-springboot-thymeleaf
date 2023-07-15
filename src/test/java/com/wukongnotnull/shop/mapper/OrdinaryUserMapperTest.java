package com.wukongnotnull.shop.mapper;

import com.wukongnotnull.shop.domain.OrdinaryUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrdinaryUserMapperTest {

    @Autowired
    private OrdinaryUserMapper ordinaryUserMapper;

    @Test
    void selectOrdinaryUser() {
        OrdinaryUser ordinaryUser = ordinaryUserMapper.selectOrdinaryUser("13700002703");
        System.out.println("ordinaryUser = " + ordinaryUser);
    }

    @Test
    void insertOrdinaryUser() {
        OrdinaryUser registerUser = new OrdinaryUser();
        registerUser.setLoginName("悟空非空也");
        registerUser.setNickName("齐天大圣");
        registerUser.setIntroduceSign("我是至尊宝");
        int i = ordinaryUserMapper.insertOrdinaryUser(registerUser);
        System.out.println("i = " + i);
    }
}