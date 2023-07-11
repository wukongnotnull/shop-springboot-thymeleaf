package com.wukongnotnull.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wukongnotnull.shop.mapper")
public class ShopSpringbootThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopSpringbootThymeleafApplication.class, args);
    }

}
