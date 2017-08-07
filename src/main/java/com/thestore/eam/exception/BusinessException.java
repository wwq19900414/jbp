/**
 * Copyright (C), 2011-2016
 * File Name: BusinessException.java
 * Encoding: UTF-8
 * Date: Sep 14, 2011
 * History: 
 */
package com.thestore.eam.exception;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.util.LocalizedTextUtil;
import com.thestore.eam.common.AppConstant;
import com.thestore.eam.utils.MyStringUtils;

/**
 * <p>Business Exception <br>
 * It should be processed.
 * @author WayneWan(waynewan.com)
 * @version Revision: 1.00 Date: Sep 14, 2011
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 2425980138916479629L;

	private Log log = LogFactory.getLog(BusinessException.class);
	
	private String resourceKey;
	private String[] params ;
	
	/**
	 * use the specified resource key and parameters to generate a exception description<br> 
	 * notes: place holder can be used
	 * @param resourceKey
	 * @param params
	 */
	public BusinessException(String resourceKey, String... params) {
		this.resourceKey = resourceKey;
		this.params = params;
	}
	
	/**
	 * use the specified resource key to generate a exception description<br>
	 * notes: no place holder can be used 
	 * @param message
	 */
	public BusinessException(String resourceKey) {
		this.resourceKey = resourceKey;
		this.params = new String[0];
	}
	
	@Override
	public String getMessage(){
		String parameterizedMsg = LocalizedTextUtil.findDefaultText(this.getResourceKey(), AppConstant.LOCALE);
		return MyStringUtils.formatPlaceHolderMessage(parameterizedMsg, this.getParams());
	}
	
	/**
	 * @param cause
	 */
	public BusinessException(Throwable cause) {
		super(cause);
		log.error(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		log.error(cause);
	}
	
	public String getResourceKey() {
		return resourceKey;
	}

	public void setResourceKey(String resourceKey) {
		this.resourceKey = resourceKey;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] newMyArray) {
		if(newMyArray == null) { 
			this.params = new String[0]; 
		}else{ 
			this.params = Arrays.copyOf(newMyArray, newMyArray.length); 
		} 
		//this.params = params;
	}
}
