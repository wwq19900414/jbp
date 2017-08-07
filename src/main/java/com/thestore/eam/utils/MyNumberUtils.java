/**
 * Copyright (C), 2011-2016 The Store
 * File Name: MyNumberUtils.java
 * Encoding: UTF-8
 * Date: Dec 26, 2011
 * History: 
 */
package com.thestore.eam.utils;

import org.apache.commons.lang.math.NumberUtils;

/**
 *
 * @author Wayne Wan(bestirwiny@gmail.com)
 * @version Revision: 1.00 Date: Dec 26, 2011
 */
public class MyNumberUtils {
	public static long addInt(String i1, String i2){
		return NumberUtils.toInt(i1, 0) + NumberUtils.toInt(i2, 0);
	}
	
	public static long addLong(String i1, String i2){
		return NumberUtils.toLong(i1, 0) + NumberUtils.toLong(i2, 0);
	}
	
	public static String addStr(String i1, String i2){
		return (NumberUtils.toDouble(i1, 0d) + NumberUtils.toDouble(i2, 0d)) + "";
	}
	
	public static long add(Long i1, Long i2){
		if(i1==null){
			i1=0L;
		}
		if(i2==null){
			i2=0L;
		}
		return i1+i2;
	}
	
	public static long add(Integer i1, Integer i2){
		if(i1==null){
			i1=0;
		}
		if(i2==null){
			i2=0;
		}
		return i1+i2;
	}
	
	public static long subtractInt(String i1, String i2){
		return NumberUtils.toInt(i1, 0) - NumberUtils.toInt(i2, 0);
	}
	
	public static long subtractLong(String i1, String i2){
		return NumberUtils.toLong(i1, 0) - NumberUtils.toLong(i2, 0);
	}
	
	public static String subtractStr(String i1, String i2){
		return (NumberUtils.toDouble(i1, 0d) - NumberUtils.toDouble(i2, 0d)) + "";
	}
	
	public static String subtractStr2(String i1, String i2){
		double d = (NumberUtils.toDouble(i1, 0d) - NumberUtils.toDouble(i2, 0d));
		return d<=0?"0":(d + "");
	}
	
	public static long subtract(Long i1, Long i2){
		if(i1==null){
			i1=0L;
		}
		if(i2==null){
			i2=0L;
		}
		return i1 - i2;
	}
	
	public static long subtract2(Long i1, Long i2){
		long result = subtract(i1, i2); 
		return result<=0?0:result;
	}
	
	public static long subtract(Integer i1, Integer i2){
		if(i1==null){
			i1=0;
		}
		if(i2==null){
			i2=0;
		}
		return i1+i2;
	}
}
