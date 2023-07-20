package com.wukongnotnull.shop.controller.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 悟空非空也
 */
@Data
public class HttpResponseResult<T> implements Serializable {
    /**
     *响应状态码
     */
    private  int code;
    /**
     *响应信息
     */
    private  String  msg;
    /**
     *响应结果
     */
    private  T result;

    /**
     *  根据响应结果进行构造器的设计
     * @param result 响应结果
     */
    private HttpResponseResult(T result){
        this.code = CodeMessageEnum.SUCCESS.getCode();
        this.msg = CodeMessageEnum.SUCCESS.getMsg();
        this.result = result;
    }

    /**
     * 构造器的设计,用于表示成功状态的响应结果
     */
    private HttpResponseResult(){
        this.code = CodeMessageEnum.SUCCESS.getCode();
        this.msg = CodeMessageEnum.SUCCESS.getMsg();
    }

    /**
     * 根据枚举进行构造器的设计
     * @param codeMsgEnum 状态码响应信息枚举类
     */
    private  HttpResponseResult(CodeMessageEnum codeMsgEnum){
        this.code = codeMsgEnum.getCode();
        this.msg = codeMsgEnum.getMsg();
    }

    /**
     *  通用的响应成功(含 result 参数)
     * @param <T> T
     * @return HttpResponseResult<T>
     */
    public  static<T> HttpResponseResult<T> success(T result){
        return new HttpResponseResult<>(result);

    }

    /**
     *  通用的响应成功(不含 result 参数)
     * @param <T> T
     * @return HttpResponseResult<T>
     */
    public  static<T> HttpResponseResult<T> success(){
        return new HttpResponseResult<>();

    }

    /**
     *  通用的响应失败
     * @return HttpResponseResult<T>
     */
    public  static <T> HttpResponseResult<T> fail(CodeMessageEnum codeMsgEnum){
        return new HttpResponseResult<T>(codeMsgEnum);

    }



}
