package com.wukongnotnull.shop.util;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 悟空非空也
 * 使用含参构造器对接受到的 Map<String, Object> params  进行二次封装
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageQueryMapUtil extends LinkedHashMap<String, Object> {
    //当前页码
    private int page;
    //每页条数
    private int limit;

    public PageQueryMapUtil(Map<String, Object> params) {
        this.putAll(params);

        //分页参数
        this.page = Integer.parseInt(params.get("page").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
        this.put("start", (page - 1) * limit);
        this.put("page", page);
        this.put("limit", limit);
    }





}
