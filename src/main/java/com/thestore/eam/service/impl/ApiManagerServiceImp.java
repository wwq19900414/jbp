package com.thestore.eam.service.impl;

import com.thestore.eam.dao.RemoteInvokerDao;
import com.thestore.eam.service.ApiManagerService;

public class ApiManagerServiceImp implements ApiManagerService {
	// private static Log log = LogFactory.getLog(ApiManagerServiceImp.class);
	private RemoteInvokerDao remoteInvokerDao;

	@Override
	public int executeSql(String sql) {
		return remoteInvokerDao.excuteSql(sql);
	}

	// ---------------setter---------------------------------------
	public void setRemoteInvokerDao(RemoteInvokerDao remoteInvokerDao) {
		this.remoteInvokerDao = remoteInvokerDao;
	}

}
