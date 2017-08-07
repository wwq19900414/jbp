/**
 * Copyright (C), 2011-2016 The Store
 * File Name: Assert.java
 * Encoding: UTF-8
 * Date: Dec 12, 2011
 * History: 
 */
package com.thestore.eam.utils;

import java.util.Collection;

/**
 * 断言工具类
 * @author Wayne Wan(bestirwiny@gmail.com)
 * @version Revision: 1.00 Date: Dec 12, 2011
 */
public class Assert {
	/**
	 * 
	 * @param obj
	 * @param message
	 */
	public static void isNull(Object obj, String message) {
		if(obj != null){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 
	 * @param obj
	 */
	public static void isNull(Object obj) {
		isNull(obj, "[Assertion failed] - the Object argument must be null");
	}

	/**
	 * 
	 * @param obj
	 * @param message
	 */
	public static void notNull(Object obj, String message) {
		if(obj == null){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 
	 * @param obj
	 */
	public static void notNull(Object obj) {
		notNull(obj, "[Assertion failed] - the Object argument must not be null");
	}

	/**
	 * 
	 * @param array
	 * @param message
	 */
	public static void notEmpty(Object[] array, String message) {
		if (array == null || array.length == 0){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 
	 * @param array
	 */
	public static void notEmpty(Object[] array) {
		notEmpty(array, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
	}

	/**
	 * 
	 * @param c
	 * @param message
	 */
	@SuppressWarnings("rawtypes")
	public static void notEmpty(Collection c, String message) {
		if (c == null || c.isEmpty()){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 
	 * @param c
	 */
	@SuppressWarnings("rawtypes")
	public static void notEmpty(Collection c) {
		notEmpty(c,"[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
	}

	public static void notZero(int n, String message){
		if(n==0){
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void notZero(int n){
		notZero(n, "[Assertion failed] - the number argument should not be 0!");
	}
}
