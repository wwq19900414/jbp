/**
 * Copyright (C), 2011-2016 The Store
 * File Name: ThreadLocalCreateInterceptor.java
 * Encoding: UTF-8
 * Date: Sep 15, 2011
 * History: 
 */
package com.thestore.eam.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 
 * @author Wayne Wan(bestirwiny@gmail.com)
 * @version Revision: 1.00 Date: Sep 15, 2011
 */
public class CookieInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 7948201343109013118L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com
	 * .opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		return invocation.invoke();
	}

}
