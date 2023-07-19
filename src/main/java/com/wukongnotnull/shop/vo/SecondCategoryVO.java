package com.wukongnotnull.shop.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
/**
 * @author 悟空非空也
 */
@Data
public class SecondCategoryVO  implements Serializable {

    private Long categoryId;
    private  Long parentId;
    private Long categoryLevel;
    private  String categoryName;
    private List<ThirdCategoryVO> thirdCategoryVOList;


}
