/**
 * Copyright (C), 2011-2016 The Store
 * File Name: StatementHandleInterceptor.java
 * Encoding: UTF-8
 * Date: Sep 9, 2011
 * History: 
 */
package com.thestore.eam.interceptor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.CallableStatementHandler;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.SimpleStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;

import com.thestore.eam.common.PageController;
import com.thestore.eam.common.SortMode;
import com.thestore.eam.common.ThreadLocalObject;
import com.thestore.eam.common.ThreadLocalVar;
import com.thestore.eam.dialect.Dialect;
import com.thestore.eam.utils.ReflectUtils;


/**
 * <p>Base Statement handler interceptor for pagging</p>
 * <li> used to change the logical paging mechanism to physical paging mechanism.
 * <li> used to auto calculate total record count and then update the page control component.
 * 
 * @author Wayne Wan(bestirwiny@gmail.com)
 * @version Revision: 1.00 Date: Sep 9, 2011
 */
@Intercepts({@Signature(type= StatementHandler.class,method = "prepare", args = {Connection.class})})
public abstract class BaseStatementHandleInterceptor implements Interceptor {
	private static final Log log = LogFactory.getLog(BaseStatementHandleInterceptor.class);
	protected abstract Dialect getDialect();
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		RoutingStatementHandler targetStmtHandler = (RoutingStatementHandler)invocation.getTarget();
		Connection conn = (Connection)invocation.getArgs()[0];
		BaseStatementHandler delefatedSmtmHandler = (BaseStatementHandler)ReflectUtils.getFieldValue(targetStmtHandler, "delegate");
        RowBounds rowBounds = (RowBounds)ReflectUtils.getFieldValue(delefatedSmtmHandler, "rowBounds");
        BoundSql boundSql = targetStmtHandler.getBoundSql();
        String sql = boundSql.getSql();
        
        Dialect dialect = getDialect();
        SortMode sortMode = getPageCtrl()==null?SortMode.CLOSE:getPageCtrl().getOrderMode();
        String orderColumn = getPageCtrl()==null?"":getPageCtrl().getOrderColumn();
        if(sortMode != SortMode.CLOSE){
        	String orderSql = dialect.getSortSql(sql, sortMode, orderColumn);
        	ReflectUtils.setFieldValue(boundSql, "sql", orderSql);
        	sql = orderSql;
        	log.debug("------- order sql " + sql);
        	getPageCtrl().setOrderMode(SortMode.CLOSE);
        }
        
        /**
         * if RowBounds had been set, then we have to do our own paging logic
         */
        if (rowBounds.getLimit() > 0 && rowBounds.getLimit() < RowBounds.NO_ROW_LIMIT)
        {
            String pagingSql = dialect.getLimitString(sql, rowBounds.getOffset(), rowBounds.getLimit());
            final String countingSql = dialect.getCountSql(sql);
            ReflectUtils.setFieldValue(boundSql, "sql", countingSql);
            
            
            //替换原来的populateCountInPageCtrl方法
            PreparedStatement stmt = (PreparedStatement)delefatedSmtmHandler.prepare(conn);
            delefatedSmtmHandler.parameterize(stmt);
    		if(SimpleStatementHandler.class.isInstance(delefatedSmtmHandler)){
    			stmt.execute(countingSql);
    		}else if(PreparedStatementHandler.class.isInstance(delefatedSmtmHandler)){
    			PreparedStatement prep = (PreparedStatement)stmt;
    			prep.execute();
    		}else if(CallableStatementHandler.class.isInstance(delefatedSmtmHandler)){
    			 CallableStatement cs = (CallableStatement) stmt;
    			 cs.execute();
    		}else{
    			throw new ExecutorException("Unknown statement type !");
    		}
    		
    		ResultSet rs = stmt.getResultSet();
    		if(rs.next()){
    			int count = rs.getInt(1);
    			log.debug("Total Count : " + count);
    			getPageCtrl().setRecordCount(count);
    		}
    		else{
    			getPageCtrl().setRecordCount(0);
    			log.warn("No record found!");
    		}
            
            
            
            
            ReflectUtils.setFieldValue(boundSql, "sql", pagingSql);
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
	
	
	private PageController getPageCtrl() {
		ThreadLocalObject tlo = ThreadLocalVar.get();
		if (tlo != null){
			return tlo.getPageContrller();
		}
		return null;
	}
}
