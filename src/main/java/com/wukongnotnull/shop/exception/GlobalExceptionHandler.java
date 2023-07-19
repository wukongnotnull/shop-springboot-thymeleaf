package com.wukongnotnull.shop.exception;


import com.wukongnotnull.shop.vo.CodeMessageEnum;
import com.wukongnotnull.shop.vo.HttpResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 悟空非空也
 * 全局异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     *  统一处理异常的方法,一般用在前后端分离的项目中，不会用在前后端一体的项目中
     */
    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    public HttpResponseResult<Object> handleGlobalException(Exception e){
        if(e instanceof ParamsException pe){
            pe.printStackTrace();
           return HttpResponseResult.fail(CodeMessageEnum.PARAMS_ERROR);
        }else {
            System.out.println("全局异常测试语句");
            e.printStackTrace();
            return HttpResponseResult.fail(CodeMessageEnum.SYSTEM_ERROR);
        }
    }

}
