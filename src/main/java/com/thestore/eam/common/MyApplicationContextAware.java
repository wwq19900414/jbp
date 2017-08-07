/**
 * Copyright (C), 2011-2016 The Store
 * File Name: MyApplicationContextAware.java
 * Encoding: UTF-8
 * Date: 2012-12-19
 * History: 
 */
package com.thestore.eam.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *
 * @author Wu Wenqi(wuwenqi@yihaodian.com)
 * @version Revision: 1.00 Date: 2012-12-19
 */
public class MyApplicationContextAware implements ApplicationContextAware{

	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		SpringContextSupport.setApplicationContext(ctx);
	}

}
