package com.wukongnotnull.shop.config;

import com.wukongnotnull.shop.common.Constants;
import com.wukongnotnull.shop.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 悟空非空也
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
               registry.addViewController("/login").setViewName("shop/login");
             //  registry.addViewController("/").setViewName("shop/index");

    }

    @Autowired
    private MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 访问 /** 路径，进入 myInterceptor 拦截器，除了 /login.html 不进入该拦截器
        registry.addInterceptor(myInterceptor)
                // 如下 url 进入拦截器
                .addPathPatterns("/**")
                // 符合上述的 url 中排除如下模式
                .excludePathPatterns(
                        "/goods-img/**",
                        "/login",
                        "/",
                        "/index",
                        "/index.html",
                        "/upload/**",
                        "/admin/**",
                        "/shop/**",
                        "/favicon.ico");
    }


    // url 路径映射本地的静态资源
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/goods-img/**")
                .addResourceLocations("file:"+ Constants.FILE_UPLOAD_DIC);
    }

}