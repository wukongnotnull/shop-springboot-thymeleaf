package com.wukongnotnull.shop.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 悟空非空也
 */
@Data
public class ParamsException extends  RuntimeException{

    /**
     * 自定义异常类型码
     */
    private Integer type = 300;
    /**
     * 自定义异常的说明信息
     */
    private String msg = "默认自定义异常类型";





}
