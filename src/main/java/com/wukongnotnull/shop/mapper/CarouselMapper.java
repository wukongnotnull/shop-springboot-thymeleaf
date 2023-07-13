package com.wukongnotnull.shop.mapper;

import com.wukongnotnull.shop.domain.Carousel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 悟空非空也
* @description 针对表【shop_carousel】的数据库操作Mapper
* @Entity com.wukongnotnull.shop.domain.Carousel
*/
public interface CarouselMapper extends BaseMapper<Carousel> {

    /**
     *  获得首页轮播图
     * @param indexCarouselNum 轮播图片数量
     * @return List<Carousel>
     */
    List<Carousel> getIndexCarousel(@Param("num") int indexCarouselNum);
}




