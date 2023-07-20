package com.wukongnotnull.shop.controller.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
/**
 * @author 悟空非空也
 */
@Data
public class IndexCategoryVO  implements Serializable {

    private Long categoryId;
    private Byte categoryLevel;
    private  String categoryName;
    private List<SecondCategoryVO> secondCategoryVOList;


}
