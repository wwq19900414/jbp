/**
 * Copyright (C), 2011-2016 The Store
 * File Name: Contract.java
 * Encoding: UTF-8
 * Date: Oct 18, 2011
 * History: 
 */
package com.thestore.eam.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
*
* @author Wayne Wan(bestirwiny@gmail.com)
* @version Revision: 1.00 Date: Oct 18, 2011
*/
public class MessageDigester {
	private static MessageDigest md ;
	static{
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public static int getMd5Int(String s)
	{
		String ss = Md5Digest(s);
		return Integer.parseInt(ss.substring(0,7), 16);
	}
	
	public static String Md5Digest(String s)
	{
		byte [] result ;
		md.update(s.getBytes());
		result = md.digest();
		
		return getMd5String(result);
	}
	
	public static String Md5Digest(InputStream input)
	{
		byte [] result = null;
		byte[] buf = new byte[1024*5];
		int readCount = 0;
		try
		{
			while((readCount = input.read(buf))>0){
				md.update(buf, 0 , readCount);
			}
			result = md.digest();
		}catch (IOException e){
			e.printStackTrace();
		}
		if(result != null){
			return getMd5String(result);
		}else{
			return "";
		}
		
	}
	
	private static String getMd5String(byte[] result)
	{
		StringBuffer sb = new StringBuffer();
		for(byte b : result)
		{
			String temp = Integer.toHexString(b&0xff);
			sb.append(temp.length()<2?"0"+temp:temp);
		}
		return sb.toString();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try
		{
			System.out.println(Md5Digest("123456"));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
