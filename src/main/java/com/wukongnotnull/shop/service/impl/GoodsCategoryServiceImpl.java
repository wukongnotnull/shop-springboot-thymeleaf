package com.wukongnotnull.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wukongnotnull.shop.common.CategoryLevelEnum;
import com.wukongnotnull.shop.common.Constants;
import com.wukongnotnull.shop.vo.IndexCategoryVO;
import com.wukongnotnull.shop.vo.SecondCategoryVO;
import com.wukongnotnull.shop.vo.ThirdCategoryVO;
import com.wukongnotnull.shop.domain.GoodsCategory;
import com.wukongnotnull.shop.service.GoodsCategoryService;
import com.wukongnotnull.shop.mapper.GoodsCategoryMapper;
import com.wukongnotnull.shop.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
* @author 悟空非空也
*/
@Service
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryMapper, GoodsCategory>
    implements GoodsCategoryService{
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;


    /**
     * 查询首页侧边分类导航数据
     * @return List<IndexCategoryVO>
     */
    @Override
    public List<IndexCategoryVO> getIndexCategoryNav() {
        // TODO 坏味道，将来优化 , 存在代码逻辑嵌套，可以尝试抽取出一个通用的方法
        // * 获得首页一级分类列表
        List<GoodsCategory> goodsFirstCategoryList = goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(
                CategoryLevelEnum.CATEGORY_LEVEL_FIRST.getLevel(),
                Collections.singletonList(0L),
                Constants.INDEX_CATEGORY_MAX_NUM);

        ArrayList<IndexCategoryVO> indexCategoryVOList = new ArrayList<>();

        if(!CollectionUtils.isEmpty(goodsFirstCategoryList)){
            // 将一级分类列表中的元素 GoodsCategory 提取出，构成 id 列表
            List<Long> firstCategoryIdList = goodsFirstCategoryList.stream()
                    .map(GoodsCategory::getCategoryId).collect(Collectors.toList());

            // ** 获取二级分类列表
            List<GoodsCategory> goodsSecondCategoryList = goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(
                    CategoryLevelEnum.CATEGORY_LEVEL_SECOND.getLevel(),
                    firstCategoryIdList,
                    0);
            if(!CollectionUtils.isEmpty(goodsSecondCategoryList)){
                // 将二级分类列表中的元素 GoodsCategory 提取出，构成 id 列表
                List<Long> secondCategoryIdList = goodsSecondCategoryList.stream()
                        .map(GoodsCategory::getCategoryId).collect(Collectors.toList());

                // *** 获取三级分类列表
                List<GoodsCategory> goodsThirdCategoryList = goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(
                        CategoryLevelEnum.CATEGORY_LEVEL_THIRD.getLevel(),
                        secondCategoryIdList,
                        0);

                if(!CollectionUtils.isEmpty(goodsThirdCategoryList)){
                    // 将三级分类列表按照 父id 进行分组，转换成 Map 类型
                    Map<Long, List<GoodsCategory>> goodsThirdCategoryMap = goodsThirdCategoryList.stream()
                            .collect(groupingBy(GoodsCategory::getParentId));

                    // 新建二级分栏视图列表
                    List<SecondCategoryVO> secondCategoryVOList = new ArrayList<>();
                    for(GoodsCategory goodsSecondCategory: goodsSecondCategoryList){
                        // 新建 二级分类视图实例
                        SecondCategoryVO secondCategoryVO = new SecondCategoryVO();

                        // 给 secondCategoryVO 中的属性赋值，除了 thirdCategoryVOList 属性
                        BeanUtil.copyProperties(goodsSecondCategory,secondCategoryVO);

                        // 给 ThirdCategoryVOList 属性复制
                        if(goodsThirdCategoryMap.containsKey(goodsSecondCategory.getCategoryId())){
                            // 二级下三级列表是什么
                            List<GoodsCategory> tempThirdCategories = goodsThirdCategoryMap.get(goodsSecondCategory.getCategoryId());
                            // 记得将 三级实体对象转换成 三级vo对象的格式
                            secondCategoryVO.setThirdCategoryVOList(BeanUtil.copyList(tempThirdCategories, ThirdCategoryVO.class));
                            // 二级vo 加入到二级vo列表中
                            secondCategoryVOList.add(secondCategoryVO);
                        }
                    }

                    // 一级视图
                    // 一级视图列表，用于将 pojo 转换成vo
                    if(!CollectionUtils.isEmpty(secondCategoryVOList)){
                        // 根据二级vo中 父id进行分组
                        Map<Long, List<SecondCategoryVO>> secondCategoryVOMap = secondCategoryVOList.stream().collect(groupingBy(SecondCategoryVO::getParentId));
                        // 一级对象 to 一级vo
                        for(GoodsCategory goodsFirstCategory: goodsFirstCategoryList){
                            IndexCategoryVO firstCategoryVO = new IndexCategoryVO();
                            // 给 firstCategoryVO 中的属性赋值，除了 SecondCategoryVOList 属性
                            BeanUtil.copyProperties(goodsFirstCategory,firstCategoryVO);

                            // 给 SecondCategoryVOList 属性列表赋值
                            if(secondCategoryVOMap.containsKey(goodsFirstCategory.getCategoryId())){
                                List<SecondCategoryVO> tempSecondCategoryVOList = secondCategoryVOMap.get(goodsFirstCategory.getCategoryId());
                                firstCategoryVO.setSecondCategoryVOList(tempSecondCategoryVOList);
                            }
                            indexCategoryVOList.add(firstCategoryVO);
                        }


                    }
                }
            }
        }
        return  indexCategoryVOList;
    }
}




