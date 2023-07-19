package com.wukongnotnull.shop.service;

import com.wukongnotnull.shop.vo.IndexCarouselVO;
import com.wukongnotnull.shop.domain.Carousel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author wukong
* @description 针对表【shop_carousel】的数据库操作Service
* @createDate 2023-07-11 15:39:53
*/
public interface CarouselService extends IService<Carousel> {

    /**
     * 获得轮播图数据
     * @return  List<Carousel>
     */
    List<IndexCarouselVO>getIndexCarousel();
}
