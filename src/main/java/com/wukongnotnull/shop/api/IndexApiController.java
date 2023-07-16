package com.wukongnotnull.shop.api;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 悟空非空也
 */
@Controller
@Profile(value = "api")
public class IndexApiController {

    @RequestMapping(value = "/test")
    @ResponseBody
    public Object testApi(){
        return "我是 API 测试";
    }


}
