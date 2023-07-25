package com.wukongnotnull.shop.controller.admin;

import cn.hutool.captcha.ShearCaptcha;
import com.wukongnotnull.shop.common.Constants;
import com.wukongnotnull.shop.domain.AdminUser;
import com.wukongnotnull.shop.service.AdminUserService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@RequestMapping("/admin")
public class AdminUserController {
    @Resource
    private AdminUserService adminUserService;

    @GetMapping("/users")
    public String usersPage(HttpServletRequest request) {
        request.setAttribute("path", "users");
        return "admin/user";
    }

    @GetMapping("/profile")
    public String profile(HttpServletRequest request) {
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Constants.ADMIN_LOGIN_SUCCESS_SESSION_KEY);
        if (adminUser == null) {
            return "admin/login";
        }
        request.setAttribute("path", "profile");
        request.setAttribute("loginUserName", adminUser.getLoginUserName());
        request.setAttribute("nickName", adminUser.getNickName());
        return "admin/profile";
    }

    /**
     *  login operation ,authentication
     * @param userName userName
     * @param password password
     * @param verifyCode verifyCode
     * @param session session
     * @return String
     */
    @PostMapping(value = "/login")
    public String doLogin(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode,
                        HttpSession session) {
        if (!StringUtils.hasText(verifyCode)) {
            session.setAttribute("errorMsg", "验证码不能为空");
            return "admin/login";
        }
        if (!StringUtils.hasText(userName) || !StringUtils.hasText(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return "admin/login";
        }
        ShearCaptcha shearCaptcha = (ShearCaptcha) session.getAttribute(Constants.SHOP_VERIFY_CODE_KEY);
        if (shearCaptcha == null || !shearCaptcha.verify(verifyCode)) {
            session.setAttribute("errorMsg", "验证码错误~~");
            return "admin/login";
        }

        AdminUser adminUser = adminUserService.doLogin(userName, password);

        if (adminUser != null) {
            session.setAttribute(Constants.ADMIN_LOGIN_SUCCESS_SESSION_KEY, adminUser);
            //session过期时间设置为7200秒 即两小时
            //session.setMaxInactiveInterval(60 * 60 * 2);
            return "redirect:/admin/index";
        } else {
            session.setAttribute("errorMsg", "登录失败");
            return "admin/login";
        }
    }

    @GetMapping({"/login"})
    public String login() {
        return "admin/login";
    }


    @GetMapping({"", "/", "/index", "/index.html"})
    public String index(HttpServletRequest request) {
        request.setAttribute("path", "index");
        return "admin/index";
    }

    @GetMapping("/logout")
    public String doLogout(HttpSession httpSession){
        httpSession.removeAttribute(Constants.ADMIN_LOGIN_SUCCESS_SESSION_KEY);
        return "redirect:/admin/login";
    }
}
