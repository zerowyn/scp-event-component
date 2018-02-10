/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.dto;

/**
 * Created by xulei on 2017/12/11.
 */
public class OrderDto {
    private String orderBy;
    private String sort;
    
     public OrderDto(){}

    public OrderDto(String orderBy,String sort){
        this.orderBy = orderBy;
        this.sort = sort;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
