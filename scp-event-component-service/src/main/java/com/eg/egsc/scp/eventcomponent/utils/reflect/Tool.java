/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.utils.reflect;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @PackageName com.eg.egsc.scp.eventcomponent.reflect
 * @Description
 * @Author chenhao
 * @Date 2017/12/14 17:16
 */
public class Tool {
    private Tool() {
    }

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     *@methodName humpToLine
     *@Description 驼峰标记转为下划线
     *@Author chenhao
     *@Date 2017/12/14 17:19
     *@Param stringWord
     *@return
     */
    public static String humpToLine(String stringWord){

        Matcher matcher = humpPattern.matcher(stringWord);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, "_"+matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
