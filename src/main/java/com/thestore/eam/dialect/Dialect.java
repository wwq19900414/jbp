/**
 * Copyright (C), 2011-2016 The Store
 * File Name: Dialect.java
 * Encoding: UTF-8
 * Date: Sep 10, 2011
 * History: 
 */
package com.thestore.eam.dialect;

import com.thestore.eam.common.SortMode;
/**
 * Database Dialect using for paging
 * @author Wayne Wan(bestirwiny@gmail.com)
 * @version Revision: 1.00 Date: Sep 10, 2011
 */
public abstract class Dialect{
	/**
	 * get paging sql
	 * @param sql
	 * @param offset
	 * @param limit
	 * @return
	 */
	public abstract String getLimitString(String sql, int offset, int limit);
	
	/**
	 * get sorting sql
	 * @param sql
	 * @param sortMode
	 * @param orderColumn
	 * @return
	 */
	public abstract String getSortSql(String sql, SortMode sortMode, String orderColumn);
	
	/**
	 * get counting sql
	 * @param sql
	 * @return
	 */
	public String getCountSql(String sql){
		return new StringBuffer( sql.length() + 20 )
			.append("select count(*) from (")
			.append(sql)
			.append(" ) count_table__ ")
			.toString();
	}
}
