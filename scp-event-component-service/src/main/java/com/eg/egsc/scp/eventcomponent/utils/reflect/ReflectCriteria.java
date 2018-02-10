/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.utils.reflect;

import com.eg.egsc.common.component.utils.CollectionUtil;
import com.eg.egsc.scp.eventcomponent.common.CommonConstant;
import com.eg.egsc.scp.eventcomponent.common.OperatorEnum;
import com.eg.egsc.scp.eventcomponent.dto.FilterDto;
import com.eg.egsc.scp.eventcomponent.common.exception.EventComponentException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

/**
 * @PackageName com.eg.egsc.scp.eventcomponent
 * @Description
 * @Author chenhao
 * @Date 2017/12/13 16:29
 */
public class ReflectCriteria {

    private ReflectCriteria() {
    }

    /**
     *@methodName invoke
     *@Description 反射查询条件语句
     *@Author chenhao
     *@Date 2017/12/13 20:07
     * @Param criteria
     * @Param filterInfos
     * @Param clazz
     *@return
     */
    public static void invoke(Object oredCriteria, List<FilterDto> filterDtos, Class clazz) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException, InstantiationException {

        if(CollectionUtil.isBlank(filterDtos)){
            return;
        }

        StringBuilder methodName ;

        Method criteriaMethod ;
        Object fieldValue ;
        Object getOredCriteria = oredCriteria.getClass().getMethod("getOredCriteria").invoke(oredCriteria);
        Object criteria = oredCriteria.getClass().getMethod("createCriteria").invoke(oredCriteria) ;
        String like ;
        Class parameterClazz ;
        for (FilterDto filterInfo:filterDtos){
            methodName = new StringBuilder("and") ;
            methodName.append(Character.toUpperCase(filterInfo.getItem().charAt(0))).append(filterInfo.getItem().substring(1)) ;

            methodName.append(getOperator(filterInfo.getOperator())) ;
            fieldValue = getFieldType(clazz,filterInfo.getItem(),filterInfo.getVal(),filterInfo.getOperator()) ;

            if(OperatorEnum.LIKE.getOrginOperator().equals(filterInfo.getOperator())){
                like = CommonConstant.OPERATOR_LIKE ;
            }else {
                like = "";
            }

            if (fieldValue.getClass().isAssignableFrom(String.class)){
                fieldValue = like + fieldValue +like ;
            }

            if(OperatorEnum.IN.getOrginOperator().equals(filterInfo.getOperator())){
                parameterClazz =fieldValue.getClass().getSuperclass().getInterfaces()[0] ;
            }else {
                parameterClazz = fieldValue.getClass() ;
            }


            if ("or".equalsIgnoreCase(filterInfo.getSeparator())){
                Object orCriteria = oredCriteria.getClass().getMethod("createCriteria").invoke(oredCriteria) ;
                getOredCriteria.getClass().getInterfaces()[0].getMethod("add",Object.class).invoke(getOredCriteria,orCriteria) ;

                criteriaMethod = orCriteria.getClass().getMethod(methodName.toString(),parameterClazz);
                criteriaMethod.invoke(orCriteria,fieldValue);
            }else{
                criteriaMethod = criteria.getClass().getMethod(methodName.toString(),parameterClazz);
                criteriaMethod.invoke(criteria,fieldValue);
            }

        }
    }

    /**
     *@methodName getOperator
     *@Description 运算符转换
     *@Author chenhao
     *@Date 2017/12/13 20:06
     * @Param operator
     *@return
     */
    private static String getOperator(String operator){

        if (OperatorEnum.EQ.getOrginOperator().equals(operator)) {
            return OperatorEnum.EQ.getTargetOperator() ;
        }else if(OperatorEnum.GT.getOrginOperator().equals(operator)){
            return OperatorEnum.GT.getTargetOperator() ;

        }else if(OperatorEnum.GTE.getOrginOperator().equals(operator)){
            return OperatorEnum.GTE.getTargetOperator() ;
        }else if(OperatorEnum.LT.getOrginOperator().equals(operator)){
            return OperatorEnum.LT.getTargetOperator() ;
        }else if(OperatorEnum.LTE.getOrginOperator().equals(operator)){
            return OperatorEnum.LTE.getTargetOperator() ;
        }else if(OperatorEnum.NEQ.getOrginOperator().equals(operator)){
            return OperatorEnum.NEQ.getTargetOperator() ;
        }else if(OperatorEnum.LIKE.getOrginOperator().equals(operator)){
            return OperatorEnum.LIKE.getTargetOperator() ;
        }else if(OperatorEnum.IN.getOrginOperator().equals(operator)){
            return OperatorEnum.IN.getTargetOperator() ;
        }
        throw new EventComponentException("Operator is empty !") ;
    }

    private static Object getFieldType(Class clazz, String fieldName, String fieldValue, String operator) throws NoSuchFieldException {
        Field field = clazz.getDeclaredField(fieldName);

        // 如果类型是String
        if (field.getGenericType().toString().equals(
                "class java.lang.String")) {
            if(OperatorEnum.IN.getOrginOperator().equals(operator)){
                return Arrays.asList(fieldValue.split(","));
            }
            return fieldValue ;
        }

        // 如果类型是Integer
        if (field.getGenericType().toString().equals(
                "class java.lang.Integer")) {
            return Integer.valueOf(fieldValue) ;
        }

        // 如果类型是Double
        if (field.getGenericType().toString().equals(
                "class java.lang.Double")) {
            return Double.valueOf(fieldValue) ;
        }

        // 如果类型是Boolean 是封装类
        if (field.getGenericType().toString().equals(
                "class java.lang.Boolean")) {
            return Boolean.valueOf(fieldValue) ;
        }

        // 如果类型是boolean 基本数据类型不一样 这里有点说名如果定义名是 isXXX的 那就全都是isXXX的
        // 反射找不到getter的具体名
        if (field.getGenericType().toString().equals("boolean")) {
            return Boolean.valueOf(fieldValue) ;

        }
        // 如果类型是Date
        if (field.getGenericType().toString().equals(
                "class java.util.Date")) {
            String pattern = "yyyy-MM-dd";
            if(fieldValue.contains(":")){
                pattern = "yyyy-MM-dd HH:mm:ss";
            }
            DateFormat dateFormat = new SimpleDateFormat(pattern);
            try {
                return dateFormat.parse(fieldValue);
            } catch (ParseException e) {

                throw new EventComponentException("Query field(Time) is invalid,"+fieldValue) ;
            }

        }
        // 如果类型是Short
        if (field.getGenericType().toString().equals(
                "class java.lang.Short")) {
            return Short.valueOf(fieldValue);
        }
        throw new EventComponentException("Query field is invalid(No matches)") ;

    }
}
