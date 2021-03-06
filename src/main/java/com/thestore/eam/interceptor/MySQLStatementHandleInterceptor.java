/**
 * Copyright (C), 2011-2016 The Store
 * File Name: MySQLStatementHandleInterceptor.java
 * Encoding: UTF-8
 * Date: Sep 9, 2011
 * History: 
 */
package com.thestore.eam.interceptor;

import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;

import com.thestore.eam.dialect.Dialect;

/**
 * Statement handler interceptor for MySQL
 * 
 * @author Wayne Wan(bestirwiny@gmail.com)
 * @version Revision: 1.00 Date: Sep 9, 2011
 */
@Intercepts({@Signature(type= StatementHandler.class,method = "prepare", args = {Connection.class})})
public class MySQLStatementHandleInterceptor extends BaseStatementHandleInterceptor {
	private static final Log log = LogFactory.getLog(MySQLStatementHandleInterceptor.class);
	private static final String DIALECT = "com.thestore.eam.dialect.MySQLDialect";
	
	/* (non-Javadoc)
	 * @see com.thestore.sem.interceptor.BaseStatementHandleInterceptor#getDialect()
	 */
	@Override
	protected Dialect getDialect() {
		Dialect dialect = null;
		try {
			dialect = (Dialect) Class.forName(DIALECT).newInstance();
		}catch (Exception e) {
			log.error("Error in instantiating " + DIALECT, e);
		}
		return dialect;
	}
}
