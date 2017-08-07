/**
 * Copyright (C), 2011-2016 The Store
 * File Name: ThreadLocalCreateInterceptor.java
 * Encoding: UTF-8
 * Date: Sep 15, 2011
 * History: 
 */
package com.thestore.eam.web.interceptor;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.thestore.eam.common.AppConstant;
import com.thestore.eam.common.PageController;
import com.thestore.eam.common.SortMode;
import com.thestore.eam.common.ThreadLocalObject;
import com.thestore.eam.common.ThreadLocalVar;

/**
 * 
 * @author Wayne Wan(bestirwiny@gmail.com)
 * @version Revision: 1.00 Date: Sep 15, 2011
 */
public class ThreadLocalCreateInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 7948201343109013118L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com
	 * .opensymphony.xwork2.ActionInvocation)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// must be removed when user authorization module added
		Map params = invocation.getInvocationContext().getParameters();
		Object pageSizeObj = params.get(AppConstant.KEY_PAGE_SIZE);
		Object currentPageObj = params.get(AppConstant.KEY_CURRENT_PAGE);
		Object sortModeObj = params.get(AppConstant.KEY_SORT_MODE);
		Object sortColumnObj = params.get(AppConstant.KEY_SORT_COLUMN);
		Object exportFlagObj = params.get(AppConstant.KEY_EXPORT);
		ThreadLocalObject tlo = new ThreadLocalObject();
		PageController pageCtrl = new PageController();
		tlo.setPageContrller(pageCtrl);

		if (sortModeObj != null && sortColumnObj != null) {
			int sortMode = Integer.parseInt(((String[]) sortModeObj)[0]);
			String sortColumn = ((String[]) sortColumnObj)[0];

			tlo.getPageContrller().setOrderColumn(sortColumn);
			switch (sortMode) {
			case AppConstant.SORT_CLOSE:
				tlo.getPageContrller().setOrderMode(SortMode.CLOSE);
				break;
			case AppConstant.SORT_ASC:
				tlo.getPageContrller().setOrderMode(SortMode.ASC);
				break;
			case AppConstant.SORT_DESC:
				tlo.getPageContrller().setOrderMode(SortMode.DESC);
				break;
			}
		}

		if (pageSizeObj != null && currentPageObj != null) {
			int pageSize = Integer.parseInt(((String[]) pageSizeObj)[0]);
			int currentPage = Integer.parseInt(((String[]) currentPageObj)[0]);
			tlo.getPageContrller().setEnabled(true);
			tlo.getPageContrller().setPageSize(pageSize);
			tlo.getPageContrller().setCurrentPage(currentPage);
		}
		/**
		 * 如果是带有导出参数的，则将分页关闭掉
		 */
		if (exportFlagObj != null) {
			String exportFlag = ((String[]) exportFlagObj)[0];
			if (StringUtils.isNotEmpty(exportFlag)) {
				tlo.setExport(Boolean.TRUE);
				tlo.getPageContrller().setEnabled(false);
			}
		}
		ThreadLocalVar.set(tlo);

		return invocation.invoke();
	}

}
