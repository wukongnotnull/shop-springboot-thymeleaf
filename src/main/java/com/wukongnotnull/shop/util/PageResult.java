package com.wukongnotnull.shop.util;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 悟空非空也
 */
@Data
public class PageResult<T> implements Serializable {

    
    /**
     * 总记录数
     */
    private int totalCount;
    
    /**
     * 每页记录数
     */
    private int pageSize;
    
    /**
     * 总页数
     */
    private int totalPages;
    
    /**
     * 当前页码
     */
    private int pageNo;
    
    /**
     * 列表数据
     */
    private List<T> list;

    /**
     * 分页
     *
     * @param list       列表数据
     * @param totalCount 总记录数
     * @param pageSize   每页记录数
     * @param pageNo   当前页数
     */
    public PageResult(List<T> list, int totalCount, int pageSize, int pageNo) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.totalPages = (int) Math.ceil((double) totalCount / pageSize);
    }

    

}