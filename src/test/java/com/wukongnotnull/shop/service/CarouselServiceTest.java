package com.wukongnotnull.shop.service;

import com.wukongnotnull.shop.controller.vo.IndexCarouselVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CarouselServiceTest {

    @Autowired
    private  CarouselService carouselService;
    @Test
    void getIndexCarousel() {
        List<IndexCarouselVO> indexCarouselVOList = carouselService.getIndexCarousel();
        System.out.println("indexCarouselVOList = " + indexCarouselVOList);

    }
}