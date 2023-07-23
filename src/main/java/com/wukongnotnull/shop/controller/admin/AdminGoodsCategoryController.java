package com.wukongnotnull.shop.controller.admin;

import com.wukongnotnull.shop.common.CategoryLevelEnum;
import com.wukongnotnull.shop.domain.GoodsCategory;
import com.wukongnotnull.shop.exception.ShopException;
import com.wukongnotnull.shop.service.GoodsCategoryService;
import com.wukongnotnull.shop.util.Result;
import com.wukongnotnull.shop.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wukong
 */
@Controller
@RequestMapping("/admin")
public class AdminGoodsCategoryController {

    @Resource
    private GoodsCategoryService goodsCategoryService;


    /**
     *
     * @param request
     * @param categoryLevel
     * @param parentId
     * @param backParentId
     * @return
     */
    @GetMapping("/categories")
    public String showCategoriesPage(HttpServletRequest request,
                                 @RequestParam("categoryLevel") Byte categoryLevel,
                                 @RequestParam("parentId") Long parentId,
                                 @RequestParam("backParentId") Long backParentId) {
        if (categoryLevel == null || categoryLevel < 1 || categoryLevel > 3) {
            ShopException.fail("参数异常");
        }
        request.setAttribute("path", "category");
        request.setAttribute("parentId", parentId);
        request.setAttribute("backParentId", backParentId);
        request.setAttribute("categoryLevel", categoryLevel);
        return "admin/category";
    }

    @GetMapping("/categories/listForSelect")
    @ResponseBody
    public Result getSubCategories(@RequestParam("categoryId") Long categoryId,
                                   HttpServletRequest request) {
        // 数据校验：非空判断 ，不能是三级分类的实体
        if (categoryId == null) {
            return ResultGenerator.genFailResult("参数为空！");
        }
        GoodsCategory goodsCategory = goodsCategoryService.queryCategoryBy(categoryId);
        if (goodsCategory == null || goodsCategory.getCategoryLevel().equals(CategoryLevelEnum.CATEGORY_LEVEL_THIRD.getLevel())) {
           return ResultGenerator.genFailResult("分类为三级分类，无子分类！");
        }

        Map<String,Object> categoryResult = new HashMap<>(4);

        // 分析： 参数 categoryId 可能是一级分类的实体 也可能是二级分类的实体

        // categoryId 是一级分类
        if (goodsCategory.getCategoryLevel().equals(CategoryLevelEnum.CATEGORY_LEVEL_FIRST.getLevel())) {
            //查询一级分类列表中指定实体的所有二级分类
            List<GoodsCategory> secondLevelCategories = goodsCategoryService
                    .selectByLevelAndParentIdsAndNumber(
                            CategoryLevelEnum.CATEGORY_LEVEL_SECOND.getLevel(),
                            // Collections.singletonList 表示单元素列表
                            Collections.singletonList(categoryId)
                    );
            if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                //查询二级分类列表中第一个实体的所有三级分类
                List<GoodsCategory> thirdLevelCategories = goodsCategoryService.
                        selectByLevelAndParentIdsAndNumber(
                                CategoryLevelEnum.CATEGORY_LEVEL_THIRD.getLevel(),
                                Collections.singletonList(secondLevelCategories.get(0).getCategoryId())
                        );
                System.out.println("categoryId 是一级分类 ");
                System.out.println("secondLevelCategories = " + secondLevelCategories);
                System.out.println("thirdLevelCategories = " + thirdLevelCategories);
                categoryResult.put("secondLevelCategories",secondLevelCategories);
                categoryResult.put("thirdLevelCategories",thirdLevelCategories);
            }

        }

        // categoryId 是二级分类
        if (goodsCategory.getCategoryLevel().equals(CategoryLevelEnum.CATEGORY_LEVEL_SECOND.getLevel())) {
            //查询二级分类列表中指定实体的所有三级分类
            List<GoodsCategory> thirdLevelCategories = goodsCategoryService
                    .selectByLevelAndParentIdsAndNumber(
                            CategoryLevelEnum.CATEGORY_LEVEL_THIRD.getLevel(),
                            // Collections.singletonList 表示单元素列表
                            Collections.singletonList(categoryId)
                    );
            System.out.println("categoryId 是二级分类 ");
            System.out.println("thirdLevelCategories = " + thirdLevelCategories);
            categoryResult.put("thirdLevelCategories",thirdLevelCategories);
        }

        return ResultGenerator.genSuccessResult(categoryResult);
    }


}
