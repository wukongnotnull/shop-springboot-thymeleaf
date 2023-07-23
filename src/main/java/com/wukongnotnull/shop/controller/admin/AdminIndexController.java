package com.wukongnotnull.shop.controller.admin;

import com.wukongnotnull.shop.common.IndexConfigTypeEnum;
import com.wukongnotnull.shop.exception.ShopException;
import com.wukongnotnull.shop.service.IndexConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author wukong
 */
@Controller
@RequestMapping("/admin")
public class AdminIndexController {

    @Resource
    private IndexConfigService indexConfigService;

    @GetMapping("/indexConfigs")
    public String indexConfigsPage(HttpServletRequest request, @RequestParam("configType") int configType) {
        IndexConfigTypeEnum indexConfigTypeEnum = IndexConfigTypeEnum.getIndexConfigTypeEnumByType(configType);
        if (indexConfigTypeEnum.equals(IndexConfigTypeEnum.INDEX_DEFAULT)) {
            ShopException.fail("参数异常");
        }

        request.setAttribute("path", indexConfigTypeEnum.getName());
        request.setAttribute("configType", configType);
        return "admin/index-config";
    }


}
