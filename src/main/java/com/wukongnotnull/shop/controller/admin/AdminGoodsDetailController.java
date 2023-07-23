package com.wukongnotnull.shop.controller.admin;

import com.wukongnotnull.shop.common.CategoryLevelEnum;
import com.wukongnotnull.shop.common.ServiceResultEnum;
import com.wukongnotnull.shop.domain.GoodsCategory;
import com.wukongnotnull.shop.domain.GoodsDetail;
import com.wukongnotnull.shop.exception.ShopException;
import com.wukongnotnull.shop.service.GoodsCategoryService;
import com.wukongnotnull.shop.service.GoodsDetailService;
import com.wukongnotnull.shop.util.PageQueryMapUtil;
import com.wukongnotnull.shop.util.PageResult;
import com.wukongnotnull.shop.util.Result;
import com.wukongnotnull.shop.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 悟空非空也
 */
@Controller
@RequestMapping("/admin")
public class AdminGoodsDetailController {

    @Resource
    private GoodsDetailService goodsDetailService;
    @Resource
    private GoodsCategoryService goodsCategoryService;


    @PutMapping("goods/status/{sellStatus}")
    @ResponseBody
    public Result modifyGoodsSellStatusBatch(@PathVariable("sellStatus") Integer goodsSellStatus,
                                        @RequestBody List<Long> ids
                                        ){
        // data validation

        // to modify
        String result =goodsDetailService.modifyGoodsSellStatusBatch(ids,goodsSellStatus);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        }
        return  ResultGenerator.genFailResult("修改上下架操作失败");

    }

    
    @PostMapping("/goods/save")
    @ResponseBody
    public Result saveGoodsDetail(@RequestBody GoodsDetail goodsDetail){
        if (!StringUtils.hasText(goodsDetail.getGoodsName())
                || !StringUtils.hasText(goodsDetail.getGoodsIntro())
                || !StringUtils.hasText(goodsDetail.getTag())
                || Objects.isNull(goodsDetail.getOriginalPrice())
                || Objects.isNull(goodsDetail.getGoodsCategoryId())
                || Objects.isNull(goodsDetail.getSellingPrice())
                || Objects.isNull(goodsDetail.getStockNum())
                || Objects.isNull(goodsDetail.getGoodsSellStatus())
                || !StringUtils.hasText(goodsDetail.getGoodsCoverImg())
                || !StringUtils.hasText(goodsDetail.getGoodsDetailContent())) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        String result = goodsDetailService.saveGoodsDetail(goodsDetail);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    @GetMapping("/goods")
    public String showGoodsDetailPage(HttpServletRequest request) {
        request.setAttribute("path", "goods");
        return "admin/goods";
    }

    @GetMapping("/goods/list")
    @ResponseBody
    public Result findAdminGoodsListPage(@RequestParam Map<String, Object> params) {
        if (ObjectUtils.isEmpty(params.get("page")) || ObjectUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        System.out.println("params = " + params);
        PageQueryMapUtil pageUtil = new PageQueryMapUtil(params);
        PageResult<Object> goodsListPage = goodsDetailService.getGoodsListPage(pageUtil);
        return ResultGenerator.genSuccessResult(goodsListPage);

    }

    /**
     * 显示修改页面，对指定商品进行修改
     * @param goodsId
     */
    @GetMapping("/goods/edit/{goodsId}")
    public String showGoodsDetailModifyPage(@PathVariable("goodsId") Long goodsId,
                                            Model model){
        // data validation

        // by  goodsId , get goods detail information
       GoodsDetail goodsDetail = goodsDetailService.findGoodsDetail(goodsId);
       model.addAttribute("goods",goodsDetail);

       // by goodsId , get category_id , by category id ,get selected category name
        //  meanwhile ,show all level categories
        Long thirdLevelCategoryId = goodsDetail.getGoodsCategoryId();

        // by thirdLevelCategoryId ,find secondLevelCategoryId
        Long secondLevelCategoryId = goodsCategoryService.findFatherCategoryId(thirdLevelCategoryId);

        // by secondLevelCategoryId ,find firstLevelCategoryId
        Long firstLevelCategoryId = goodsCategoryService.findFatherCategoryId(secondLevelCategoryId);


        model.addAttribute("firstLevelCategoryId",firstLevelCategoryId);
        model.addAttribute("secondLevelCategoryId",secondLevelCategoryId);
        model.addAttribute("thirdLevelCategoryId",thirdLevelCategoryId);

        //查询所有的一级分类
        List<GoodsCategory> firstLevelCategories = goodsCategoryService
                .selectByLevelAndParentIdsAndNumber(
                        CategoryLevelEnum.CATEGORY_LEVEL_FIRST.getLevel(),
                        Collections.singletonList(0L));

        //查询一级分类列表中被选中的实体的所有二级分类
        List<GoodsCategory> secondLevelCategories = goodsCategoryService
                .selectByLevelAndParentIdsAndNumber(
                        CategoryLevelEnum.CATEGORY_LEVEL_SECOND.getLevel(),
                        // Collections.singletonList 表示单元素列表
                        Collections.singletonList(firstLevelCategoryId)
                );

        //查询二级分类列表中被选中的实体的所有三级分类
        List<GoodsCategory> thirdLevelCategories = goodsCategoryService.
                selectByLevelAndParentIdsAndNumber(
                        CategoryLevelEnum.CATEGORY_LEVEL_THIRD.getLevel(),
                        Collections.singletonList(secondLevelCategoryId)
                );
        model.addAttribute("firstLevelCategories",firstLevelCategories);
        model.addAttribute("secondLevelCategories",secondLevelCategories);
        model.addAttribute("thirdLevelCategories",thirdLevelCategories);

        return "admin/goods-edit";
    }


    /**
     * modify goods detail content
     */
    @PostMapping("/goods/update")
    @ResponseBody
    public Result modifyGoodsDetail(@RequestBody GoodsDetail goodsDetail){
        // data validation

       String result = goodsDetailService.modifyGoodsDetail(goodsDetail);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("商品修改失败~！");
    }

    /**
     *  显示空白的编辑页面（含有分类数据）
     * @param request
     * @return
     */
    @GetMapping("/goods/edit")
    public String edit(HttpServletRequest request) {
        request.setAttribute("path", "edit");
        // 迭代显示分类筛选下拉框
        //查询所有的一级分类
        List<GoodsCategory> firstLevelCategories = goodsCategoryService
                .selectByLevelAndParentIdsAndNumber(
                        CategoryLevelEnum.CATEGORY_LEVEL_FIRST.getLevel(),
                        Collections.singletonList(0L));

        if (!CollectionUtils.isEmpty(firstLevelCategories)) {
            //查询一级分类列表中第一个实体的所有二级分类
            List<GoodsCategory> secondLevelCategories = goodsCategoryService
                    .selectByLevelAndParentIdsAndNumber(
                            CategoryLevelEnum.CATEGORY_LEVEL_SECOND.getLevel(),
                            // Collections.singletonList 表示单元素列表
                            Collections.singletonList(firstLevelCategories.get(0).getCategoryId())
                    );
            if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                //查询二级分类列表中第一个实体的所有三级分类
                List<GoodsCategory> thirdLevelCategories = goodsCategoryService.
                        selectByLevelAndParentIdsAndNumber(
                                CategoryLevelEnum.CATEGORY_LEVEL_THIRD.getLevel(),
                                Collections.singletonList(secondLevelCategories.get(0).getCategoryId())
                        );
                request.setAttribute("firstLevelCategories", firstLevelCategories);
                request.setAttribute("secondLevelCategories", secondLevelCategories);
                request.setAttribute("thirdLevelCategories", thirdLevelCategories);
                request.setAttribute("path", "goods-edit");
                return "admin/goods-edit";
            }
        }
        ShopException.fail("分类数据不完善");
        return null;
    }



}
