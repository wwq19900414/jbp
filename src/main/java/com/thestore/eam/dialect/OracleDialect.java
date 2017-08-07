/**
 * Copyright (C), 2011-2016 The Store
 * File Name: OracleDialect.java
 * Encoding: UTF-8
 * Date: Sep 10, 2011
 * History: 
 */
package com.thestore.eam.dialect;

import com.thestore.eam.common.SortMode;

/**
 *
 * @author Wayne Wan(bestirwiny@gmail.com)
 * @version Revision: 1.00 Date: Sep 10, 2011
 */
public class OracleDialect extends Dialect {

	/* (non-Javadoc)
	 * @see com.thestore.sem.dialect.Dialect#getLimitString(java.lang.String, int, int)
	 */
	@Override
	public String getLimitString(String sql, int offset, int limit) {
		sql = sql.trim();
		boolean isForUpdate = false;
		if ( sql.toLowerCase().endsWith(" for update ") ) {
			sql = sql.substring( 0, sql.length()-11 );
			isForUpdate = true;
		}
		
		StringBuffer pagingSelect = new StringBuffer( sql.length()+130 );
		pagingSelect.append(" select * from ( select row_.*, rownum rownum_ from ( ");
		pagingSelect.append(sql);
		pagingSelect.append(" ) row_ ) where rownum_ <= ")
		.append(offset + limit)
		.append(" and rownum_ > ")
		.append(offset);
		if ( isForUpdate ) {
			pagingSelect.append( " for update " );
		}
		return pagingSelect.toString();
	}

	@Override
	public String getSortSql(String sql, SortMode sortMode, String sortColumn) {
	StringBuffer sb = new StringBuffer( sql.length() + 30 ).append(sql);
		if(sortMode == SortMode.ASC){
			sb.append(" ORDER BY " + sortColumn + " ASC ");
		}else if(sortMode == SortMode.DESC){
			sb.append(" ORDER BY " + sortColumn + " DESC ");
		}
		return sb.toString();
	}

}
