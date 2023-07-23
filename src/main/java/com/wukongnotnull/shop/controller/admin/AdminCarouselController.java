package com.wukongnotnull.shop.controller.admin;

import com.wukongnotnull.shop.service.CarouselService;
import com.wukongnotnull.shop.util.PageQueryMapUtil;
import com.wukongnotnull.shop.util.Result;
import com.wukongnotnull.shop.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminCarouselController {

    @Resource
    private CarouselService carouselService;

    @GetMapping("/carousels")
    public String carouselPage(HttpServletRequest request) {
        request.setAttribute("path", "carousel");
        return "admin/carousel";
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/carousels/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (ObjectUtils.isEmpty(params.get("page")) || ObjectUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryMapUtil pageUtil = new PageQueryMapUtil(params);
        return ResultGenerator.genSuccessResult(carouselService.getCarouselPage(pageUtil));
    }
}
