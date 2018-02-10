/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.handler;

import com.eg.egsc.common.component.utils.JsonUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.postgresql.util.PGobject;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes({Object.class})
public class JsonbTypeHandler extends BaseTypeHandler <Object> {

    private static final PGobject jsonObject = new PGobject();
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {
        jsonObject.setType("jsonb");
        jsonObject.setValue(JsonUtil.toJsonString(o));
        preparedStatement.setObject(i,jsonObject);

    }

    @Override
    public Object getNullableResult(ResultSet resultSet, String s) throws SQLException {
        if(null != resultSet.getString(s)){
            return JsonUtil.fromJson(resultSet.getString(s), Object.class);
        }
        return null ;

    }

    @Override
    public Object getNullableResult(ResultSet resultSet, int i) throws SQLException {
        if(null != resultSet.getString(i)){
            return JsonUtil.fromJson(resultSet.getString(i), Object.class);
        }
        return null ;

    }

    @Override
    public Object getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        if(null != callableStatement.getString(i)){
            return JsonUtil.fromJson(callableStatement.getString(i), Object.class);
        }
        return null ;

    }
}
