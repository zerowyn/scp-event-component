/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.dto;

import com.eg.egsc.framework.client.dto.BaseBusinessDto;

import java.util.List;

/**
 * @PackageName com.eg.egsc.scp.eventcomponent.dto
 * @Description
 * @Author chenhao
 * @Date 2017/12/13 14:21
 */
public class PageQueryDto extends BaseBusinessDto {

    private int pageSize ;

    private int pageNo ;

    private List<FilterDto> filters ;

    private OrderDto order ;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<FilterDto> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterDto> filters) {
        this.filters = filters;
    }

    public OrderDto getOrder() {
        return order;
    }

    public void setOrder(OrderDto order) {
        this.order = order;
    }
}
