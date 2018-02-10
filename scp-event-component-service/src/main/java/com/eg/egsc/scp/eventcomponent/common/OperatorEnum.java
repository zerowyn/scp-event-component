/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.common;

/**
 * 操作类型枚举类
 *
 * @author chenhao
 * @since 2017-12-29
 */
public enum OperatorEnum {

    EQ("eq","EqualTo"),
    GT("gt","GreaterThan"),
    GTE("gte","GreaterThanOrEqualTo"),
    LT("lt","LessThan"),
    LTE("lte","LessThanOrEqualTo"),
    NEQ("neq","NotEqualTo"),

    LIKE("like","Like"),
    IN("in","In") ;

    private String orginOperator ;
    private String targetOperator ;

    OperatorEnum(String orginOperator, String targetOperator) {
        this.orginOperator = orginOperator;
        this.targetOperator = targetOperator;
    }

    public String getOrginOperator() {
        return orginOperator;
    }

    public String getTargetOperator() {
        return targetOperator;
    }
}
