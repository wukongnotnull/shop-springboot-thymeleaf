package com.wukongnotnull.shop.controller.shop;

import com.wukongnotnull.shop.common.ServiceResultEnum;
import com.wukongnotnull.shop.controller.vo.CodeMessageEnum;
import com.wukongnotnull.shop.controller.vo.HttpResponseResult;
import com.wukongnotnull.shop.domain.OrdinaryUser;
import com.wukongnotnull.shop.service.OrdinaryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 悟空非空也
 */
@Controller
public class OrdinaryUserController {
    @Autowired
    private OrdinaryUserService ordinaryUserService;

    @RequestMapping(value = "/register")
    @ResponseBody
    public HttpResponseResult doRegisterOrdinaryUser(@RequestParam("registerName") String registerName,
                                                     @RequestParam("password") String password,
                                                     @RequestParam("verifyCode") String verifyCode
                                       ){
        // 进行后端数据校验
        if(!StringUtils.hasText(registerName)){
           return HttpResponseResult.fail(CodeMessageEnum.PARAM_IS_NULL);
        }

        if(!StringUtils.hasText(password)){
            return HttpResponseResult.fail(CodeMessageEnum.PARAM_IS_NULL);
        }

        if(!StringUtils.hasText(verifyCode)){
            return HttpResponseResult.fail(CodeMessageEnum.PARAM_IS_NULL);
        }

        // ToDo 处理验证码是否合法


        // 根据用户名和密码进行注册操作
        String userAddResult =  ordinaryUserService.addOrdinaryUser(registerName,password);

        if(ServiceResultEnum.SUCCESS.getResult().equals(userAddResult)){
           return HttpResponseResult.success();
        }
        return HttpResponseResult.fail(CodeMessageEnum.REGISTER_ERROR);


    }


}
