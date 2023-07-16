package com.wukongnotnull.shop.controller.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 悟空非空也
 */
@Data
public class OrdinaryUserVO implements Serializable {

    private Long userId;

    private String nickName;

    private String loginName;

    private String introduceSign;

    private String address;

    private Integer cartItemCount;


}
