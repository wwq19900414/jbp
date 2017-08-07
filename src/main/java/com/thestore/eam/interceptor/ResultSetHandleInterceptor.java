/**
 * Copyright (C), 2011-2016 The Store
 * File Name: ResultSetHandleInterceptor.java
 * Encoding: UTF-8
 * Date: Sep 9, 2011
 * History: 
 */
package com.thestore.eam.interceptor;

import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.FastResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;

import com.thestore.eam.utils.ReflectUtils;

/**
 * <p>ResultSetHandler interceptor</p>
 * <p>used to switch off the logic paging mechanism in mybatis
 * 
 * @author Wayne Wan(bestirwiny@gmail.com)
 * @version Revision: 1.00 Date: Sep 9, 2011
 */
@Intercepts({@Signature(type= ResultSetHandler.class,method = "handleResultSets", args = {Statement.class})})
public class ResultSetHandleInterceptor implements Interceptor{

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		FastResultSetHandler resultSetHandler = (FastResultSetHandler)invocation.getTarget();
        RowBounds rowBounds = (RowBounds)ReflectUtils.getFieldValue(resultSetHandler, "rowBounds");
        /**
         * if RowBounds was set, then clear the setting
         */
        if (rowBounds.getLimit() > 0 && rowBounds.getLimit() < RowBounds.NO_ROW_LIMIT)
        {
            ReflectUtils.setFieldValue(resultSetHandler, "rowBounds", new RowBounds());
        }
        return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}
}
