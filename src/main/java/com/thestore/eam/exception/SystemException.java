/**
 * Copyright (C), 2011-2016
 * File Name: SystemException.java
 * Encoding: UTF-8
 * Date: Sep 14, 2011
 * History: 
 */
package com.thestore.eam.exception;

/**
 * <p>System Exception <br>
 * It means we can't process it or we can't restore the state of our application
 * @author WayneWan(waynewan.com)
 * @version Revision: 1.00 Date: Sep 14, 2011
 */
public class SystemException extends RuntimeException {

	private static final long serialVersionUID = 3493847175277647916L;

	/**
	 * 
	 */
	public SystemException() {
	}

	/**
	 * @param message
	 */
	public SystemException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public SystemException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

}
