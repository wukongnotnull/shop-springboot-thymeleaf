package com.wukongnotnull.shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class MyInterceptor  implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // 访问某 url，当发现在未登录状态下，拦截，跳转到登录页
        Object username = request.getSession().getAttribute("userSession");
        if (username == null) {
            System.out.println("未登录，拦截器不通过");
            response.sendRedirect(request.getContextPath()+"/login");
            // 拦截
            return false;
        }
        // 放行
        return  true;
    }


}