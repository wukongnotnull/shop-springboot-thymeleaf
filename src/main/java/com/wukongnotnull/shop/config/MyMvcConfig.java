package com.wukongnotnull.shop.config;

import com.wukongnotnull.shop.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 悟空非空也
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/login.html").setViewName("login");

    }

    @Autowired
    private MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 访问 /** 路径，进入 myInterceptor 拦截器，除了 /login.html 不进入该拦截器
        registry.addInterceptor(myInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/login.html");
    }

}