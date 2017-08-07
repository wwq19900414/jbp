/**
 * Copyright (C), 2011-2016 The Store
 * File Name: BaseDao.java
 * Encoding: UTF-8
 * Date: Sep 7, 2011
 * History: 
 */
package com.thestore.eam.common;


/**
 * 
 * @author Wayne Wan (bestirwiny@gmail.com)
 * @version Revision: 1.00 Date: Sep 7, 2011
 */
public class ThreadLocalObject {
	private PageController pageContrller;
	private Boolean isExport = false;

	public PageController getPageContrller() {
		return pageContrller;
	}

	public void setPageContrller(PageController pageContrller) {
		this.pageContrller = pageContrller;
	}

	public void setExport(Boolean b) {
		isExport = b;
	}

	public Boolean isExportRequest() {
		return isExport;
	}
}
