package com.wukongnotnull.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wukongnotnull.shop.common.Constants;
import com.wukongnotnull.shop.controller.vo.IndexCarouselVO;
import com.wukongnotnull.shop.domain.Carousel;
import com.wukongnotnull.shop.service.CarouselService;
import com.wukongnotnull.shop.mapper.CarouselMapper;
import com.wukongnotnull.shop.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
* @author wukong
* @description 针对表【shop_carousel】的数据库操作Service实现
*/
@Service
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel>
    implements CarouselService{

    @Autowired
    private CarouselMapper carouselMapper;

    /**
     * 获得轮播图数据
     *
     * @return List<Carousel>
     */
    @Override
    public List<IndexCarouselVO> getIndexCarousel() {
        // do
        List<Carousel> carouselList = carouselMapper.getIndexCarousel(Constants.INDEX_CAROUSEL_NUM);
        List<IndexCarouselVO> indexCarouselVOList = new ArrayList<>();

        if(!CollectionUtils.isEmpty(carouselList)){
            // do to vo
            indexCarouselVOList = BeanUtil.copyList(carouselList, IndexCarouselVO.class);
        }
        return indexCarouselVOList;
    }
}




