/**
 * Copyright (C), 2011-2016 The Store
 * File Name: MySQLDialect.java
 * Encoding: UTF-8
 * Date: Sep 10, 2011
 * History: 
 */
package com.thestore.eam.dialect;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.thestore.eam.common.SortMode;

/**
 *
 * @author Wayne Wan(bestirwiny@gmail.com)
 * @version Revision: 1.00 Date: Sep 10, 2011
 */
public class MySQLDialect extends Dialect {
	
	private static Pattern p = Pattern.compile(".*(ORDER\\s+BY\\s+\\S+\\s+(ASC|DESC)).*", 
			Pattern.DOTALL|Pattern.MULTILINE);
	
	/* (non-Javadoc)
	 * @see com.thestore.sem.dialect.Dialect#getLimitString(java.lang.String, int, int)
	 */
	@Override
	public String getLimitString(String sql, int offset, int limit) {
		return new StringBuffer( sql.length() + 20 )
				.append(sql)
				.append(" limit ").append(offset).append(",")
				.append(limit).toString();
	}

	@Override
	public String getSortSql(String sql, SortMode sortMode, String orderColumn) {
		//sql = sql.toUpperCase();
		StringBuffer sb = new StringBuffer( sql.length() + 20 );
		String originalOrderSnippet = getOrderBySql(sql);
		if(StringUtils.isNotEmpty(originalOrderSnippet)){
			if(SortMode.ASC == sortMode){
				sql = sql.replace(originalOrderSnippet, " ORDER BY " + orderColumn + " ASC ");
			}
			if(SortMode.DESC == sortMode){
				sql = sql.replace(originalOrderSnippet, " ORDER BY " + orderColumn + " DESC ");
			}
			return sql;
		}
		sb.append(sql);
		if(SortMode.ASC == sortMode){
			sb.append(" ORDER BY " + orderColumn + " ASC ");
		}
		if(SortMode.DESC == sortMode){
			sb.append(" ORDER BY " + orderColumn + " DESC ");
		}
		return sb.toString();
	}

	/**
	 * notes: the order by must directly indicate AESC or DESC
	 * @Title: getOrderBySql
	 * @Description: get the order by snippet, if no order by clause, return empty string
	 * @param @return
	 * @return String
	 * @author Wayne Wan(wanyi@yihaodian.com)
	 * @date  Nov 3, 2011
	 */
	private String getOrderBySql(String sql){
		
		Matcher m = p.matcher(sql);
		if(m.matches()){
			return m.group(1);
		}
		return "";
	}
}
