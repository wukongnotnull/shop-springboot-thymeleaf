package com.wukongnotnull.shop.service.bo;

import lombok.Data;

import java.util.List;

/**
 * @author 悟空非空也
 */
@Data
public class OrderDetailPageBO {

    private List<OrderDetailBO>  list;
    private Integer totalCounts;
    private Integer totalPages;
    private  Integer pageNo;


}
