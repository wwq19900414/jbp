package com.thestore.eam.dao;


public interface RemoteInvokerDao {

	/**
	 * 直接执行Sql
	 * 
	 * @param sql
	 * @return
	 */
	public int excuteSql(String sql);

}
