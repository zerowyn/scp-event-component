/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.utils;

import com.eg.egsc.scp.eventcomponent.dto.PageQueryDto;

/**
 * @PackageName com.eg.egsc.scp.eventcomponent.common
 * @Description
 * @Author chenhao
 * @Date 2017/12/13 14:38
 */
public class ValidateParamUtils {
    private ValidateParamUtils() {
    }

    /**
     * 如果校验页数参数合法返回true
     * @param pageQuery
     * @return
     */
    public static boolean isPageLegal(PageQueryDto pageQuery){

       return !(null == pageQuery || pageQuery.getPageNo() < 1 || pageQuery.getPageSize() < 1 ||
                pageQuery.getPageSize()>1000 || pageQuery.getPageNo()>1000);
    }
}
