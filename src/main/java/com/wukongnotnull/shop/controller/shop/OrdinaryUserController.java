package com.wukongnotnull.shop.controller.shop;

import cn.hutool.captcha.ShearCaptcha;
import com.wukongnotnull.shop.common.Constants;
import com.wukongnotnull.shop.common.ServiceResultEnum;
import com.wukongnotnull.shop.controller.vo.CodeMessageEnum;
import com.wukongnotnull.shop.controller.vo.HttpResponseResult;
import com.wukongnotnull.shop.service.OrdinaryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 悟空非空也
 */
@Controller
public class OrdinaryUserController {
    @Autowired
    private OrdinaryUserService ordinaryUserService;

    /**
     * 进行退出操作
     * @param httpSession httpSession
     */
    @GetMapping(value = "/logout")
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       HttpSession httpSession) throws ServletException, IOException {
        httpSession.invalidate();
        response.sendRedirect(request.getContextPath()+"/index");

    }


    /**
     *  处理登录
     * @param loginName loginName
     * @param password password
     * @param verifyCode verifyCode
     * @param httpSession httpSession
     * @return HttpResponseResult
     */
    @PostMapping(value = "/doLogin")
    @ResponseBody
    public HttpResponseResult<Object>  doLogin(
           @RequestParam("loginName") String loginName,
           @RequestParam("password") String password,
           @RequestParam("verifyCode")  String verifyCode,
           HttpSession httpSession
    ){
        // 进行后端数据校验,todo 后续使用验证框架
        if(!StringUtils.hasText(loginName)){
            return HttpResponseResult.fail(CodeMessageEnum.PARAM_IS_NULL);
        }

        if(!StringUtils.hasText(password)){
            return HttpResponseResult.fail(CodeMessageEnum.PARAM_IS_NULL);
        }

        if(!StringUtils.hasText(verifyCode)){
            return HttpResponseResult.fail(CodeMessageEnum.PARAM_IS_NULL);
        }

        // 验证图片验证码是否合法
        ShearCaptcha shearCaptcha =(ShearCaptcha) httpSession.getAttribute(Constants.SHOP_VERIFY_CODE_KEY);
        if (shearCaptcha == null || !shearCaptcha.verify(verifyCode)) {
            HttpResponseResult.fail(CodeMessageEnum.CAPTCHA_IS_ILLEGAL);
        }
        // 清除 session 中的验证码对象,因为下面逻辑不在需要它
        httpSession.removeAttribute(Constants.SHOP_VERIFY_CODE_KEY);

        // 调用 service 层进行登录操作
        String result = ordinaryUserService.handleLogin(loginName,password,httpSession);
        if(!ServiceResultEnum.SUCCESS.getResult().equals(result)){
            return  HttpResponseResult.fail(CodeMessageEnum.LOGIN_FAIL);
        }

        return HttpResponseResult.success();
    }

    /**
     * 访问注册页面
     * @return String
     */
    @GetMapping(value = "/register")
    public String register(){
        return "shop/register";
    }

    /**
     *  进行注册操作
     * @param registerName registerName
     * @param password password
     * @param verifyCode verifyCode
     * @return HttpResponseResult
     */
    @PostMapping(value = "/doRegister")
    @ResponseBody
    public HttpResponseResult<Object> doRegisterOrdinaryUser(@RequestParam("registerName") String registerName,
                                                     @RequestParam("password") String password,
                                                     @RequestParam("verifyCode") String verifyCode,
                                                     HttpSession httpSession
                                       ){
        // 进行后端数据校验,todo 后续使用验证框架
        if(!StringUtils.hasText(registerName)){
           return HttpResponseResult.fail(CodeMessageEnum.PARAM_IS_NULL);
        }

        if(!StringUtils.hasText(password)){
            return HttpResponseResult.fail(CodeMessageEnum.PARAM_IS_NULL);
        }

        if(!StringUtils.hasText(verifyCode)){
            return HttpResponseResult.fail(CodeMessageEnum.PARAM_IS_NULL);
        }

        // 处理验证码是否合法
        ShearCaptcha shearCaptcha =(ShearCaptcha) httpSession.getAttribute(Constants.SHOP_VERIFY_CODE_KEY);
        if (shearCaptcha == null || !shearCaptcha.verify(verifyCode)) {
            HttpResponseResult.fail(CodeMessageEnum.CAPTCHA_IS_ILLEGAL);
        }


        // 根据用户名和密码进行注册操作
        String userAddResult =  ordinaryUserService.addOrdinaryUser(registerName,password);

        if(ServiceResultEnum.SUCCESS.getResult().equals(userAddResult)){
           return HttpResponseResult.success();
        }
        return HttpResponseResult.fail(CodeMessageEnum.REGISTER_ERROR);


    }


}
