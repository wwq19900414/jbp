/**
 * Copyright (C), 2011-2016 The Store
 * File Name: SpringContextSupport.java
 * Encoding: UTF-8
 * Date: Nov 4, 2011
 * History: 
 */
package com.thestore.eam.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author Wayne Wan(bestirwiny@gmail.com)
 * @version Revision: 1.00 Date: Nov 4, 2011
 */
public class SpringContextSupport{
	
	private static ApplicationContext context;

	public  static void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext() {
		return context;
	}
	/**
	 * 
	 * @Title: getBean
	 * @Description:
	 * @param @param beanId
	 * @param @return
	 * @return Object
	 * @author Wayne Wan(wanyi@yihaodian.com)
	 * @date  Nov 4, 2011
	 */
	public static Object getBean(String beanId){
		return context.getBean(beanId);
	}
}
