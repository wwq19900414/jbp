package com.thestore.eam.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.thestore.eam.dao.RemoteInvokerDao;

/**
 * 对外宝洁用户数据接口持久层实现
 * 
 * @author Wu Wenqi(wuwenqi@yihaodian.com)
 * @version Revision: 1.00 Date: 2013-2-21
 */
@SuppressWarnings("rawtypes")
public class RemoteInvokerDaoImp extends BaseIbatisDao implements RemoteInvokerDao {

	@Override
	public int excuteSql(String sql) {
		// 不支持drop操作
		if (sql == null || sql.toLowerCase().trim().startsWith("drop")) {
			return -503;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sql", sql);

		try {
			return this.getSqlSession().update("com.thestore.eam.domain.RemoteInvoker.executeSql", params);
		} catch (Exception e) {
			e.printStackTrace();
			return -404;
		}
	}

}
