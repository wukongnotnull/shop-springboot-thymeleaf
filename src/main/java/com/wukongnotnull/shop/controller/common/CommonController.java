package com.wukongnotnull.shop.controller.common;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.wukongnotnull.shop.common.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author wukong
 */
@Controller
public class CommonController {

    @GetMapping(value = "/common/captcha")
    public void getCaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        // 接口
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/png");

        ShearCaptcha shearCaptcha= CaptchaUtil.createShearCaptcha(110, 40, 4, 2);
        System.out.println("CommonController-----getCaptcha");
        // 验证码存入 Session 中
        httpServletRequest.getSession().setAttribute(Constants.SHOP_VERIFY_CODE_KEY, shearCaptcha);

        // 输出图片流 ，通过图片 url 路径可以正常访问
        shearCaptcha.write(httpServletResponse.getOutputStream());

    }

}
