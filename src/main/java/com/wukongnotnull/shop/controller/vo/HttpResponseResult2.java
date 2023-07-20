package com.wukongnotnull.shop.controller.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 悟空非空也  尝试一下
 */
@Data
public class HttpResponseResult2<T> implements Serializable {
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
    private HttpResponseResult2(T result){
        this.code = CodeMessageEnum2.SUCCESS.getCode();
        this.msg = CodeMessageEnum2.SUCCESS.getServiceResultEnum().getResult();
        this.result = result;
    }

    /**
     * 构造器的设计,用于表示成功状态的响应结果
     */
    private HttpResponseResult2(){
        this.code = CodeMessageEnum2.SUCCESS.getCode();
        this.msg = CodeMessageEnum2.SUCCESS.getServiceResultEnum().getResult();
    }

    /**
     * 根据枚举进行构造器的设计
     * @param codeMsgEnum 状态码响应信息枚举类
     */
    private HttpResponseResult2(CodeMessageEnum2 codeMsgEnum){
        this.code = codeMsgEnum.getCode();
        this.msg = codeMsgEnum.getServiceResultEnum().getResult();
    }

    /**
     *  通用的响应成功(含 result 参数)
     * @param <T> T
     * @return HttpResponseResult<T>
     */
    public  static<T> HttpResponseResult2<T> success(T result){
        return new HttpResponseResult2<>(result);

    }

    /**
     *  通用的响应成功(不含 result 参数)
     * @param <T> T
     * @return HttpResponseResult<T>
     */
    public  static<T> HttpResponseResult2<T> success(){
        return new HttpResponseResult2<>();

    }

    /**
     *  通用的响应失败
     * @return HttpResponseResult<T>
     */
    public  static <T> HttpResponseResult2<T> fail(CodeMessageEnum2 codeMsgEnum){
        return new HttpResponseResult2<T>(codeMsgEnum);

    }



}
