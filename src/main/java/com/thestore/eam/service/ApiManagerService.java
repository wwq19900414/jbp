package com.thestore.eam.service;

/**
 * 后台管理员用来重跑数据,管理
 * 
 * @author wuwenqi
 * 
 */
public interface ApiManagerService {

	/**
	 * 执行sql
	 * 
	 * @param sql
	 * @return
	 */
	public int executeSql(String sql);
}
