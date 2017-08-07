/**
 * Copyright (C), 2011-2016 The Store
 * File Name: MyStringUtils.java
 * Encoding: UTF-8
 * Date: Oct 22, 2011
 * History: 
 */
package com.thestore.eam.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Wayne Wan(bestirwiny@gmail.com)
 * @version Revision: 1.00 Date: Oct 22, 2011
 */
public class MyStringUtils {
	
	/**
	 * format the parameterized exception message<br>
	 * the parameterized string must be in "aaa{0}dad{1}gfgf{2}" style<br>
	 * the count of the parameters must equal to the count of the place holders  
	 * @return
	 */
	public static String formatPlaceHolderMessage(String pErrorMsg, String... params){
		for(int i=0; i<params.length; i++){
			pErrorMsg = StringUtils.replace(pErrorMsg, "{" + i + "}", params[i]);
		}
		return pErrorMsg;
	}
	
	public int indexOfStr(String str1,String str2){
		return str1.indexOf(str2);
	}
	
	public String splitStr(String str1,String str2,int n){
		if(str1 != null && !str1.equals("")){
			String[] str = str1.split(str2);
			return str[n];
		}
		return "";
	}
	
	public String splitStrs(String str){
		if(str != null && !str.equals("")){
			if(str.indexOf("&") == -1){
				String[] strs = str.split("\\?");
				return strs[0];
			}else{
				String[] strs = str.split("&");
				if(strs != null && strs.length > 0){
					StringBuffer s = new StringBuffer("");
					for(int i = 0; i < strs.length - 1; i++){
						if(s.toString().equals("")){
							s.append(strs[i]);
						}else{
							s.append("&" + strs[i]);
						}
					}
					return s.toString();
				}
			}
		}
		return "";
	}
	
	public String getDecimalFormat(String s)   {
		double d = new Double(s).doubleValue();
        DecimalFormat fmt = new DecimalFormat("##,###,###,###,##0.00"); 
        return  fmt.format(d); 
    }
	
	private static Pattern p = Pattern.compile("(\\d+(\\.\\d+)*).*");
	
	/**
	 * get number from a unit, if error return "0"
	 * notes: only can parse positive number
	 * eg: 1.1元/click -> 1.1
	 * @Title: getNumberFromUnit
	 * @Description:
	 * @param 
	 * @return void
	 * @author Wayne Wan(wanyi@yihaodian.com)
	 * @date  Nov 10, 2011
	 */
	public static String getNumberFromUnit(String unit){
		String num = "0";
		Matcher m = p.matcher(unit);
		if(m.matches()){
			num = (m.group(1));
		}
		return num;
	}
	
	 /** 
	  * 返回两个日期相差的天数,有一个时间为null返回-1 
	  * @param d1  长的时间 
	  * @param d2  短的时间 
	  * @return int 
	  */ 
	 public static long diff_in_date(Date d1, Date d2){  
		 if(null == d1 || null == d2){ 
			 return -1; 
		 } 
		 return (long)(d2.getTime() - d1.getTime())/(1000*60*60*24); 
	 }
	 
	 /**
	 * 根据传入的日期以及天数计算并返回日期
	 * 
	 * @param date
	 * @param hours
	 * @return
	 */
	public static Date addDate(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);

		return cal.getTime();
	}
	
	/**
	  * //根据日期判断是星期几
	  * @param DateStr
	  * @return
	  */
	 public static String getWeekDay(String DateStr){
	     SimpleDateFormat formatYMD=new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);//formatYMD表示的是yyyy-MM-dd格式 
	     SimpleDateFormat formatD=new SimpleDateFormat("E",Locale.ENGLISH);//"E"表示"day in week" 
	     Date d=null;
	     String weekDay="";
	     try{
	        d=formatYMD.parse(DateStr);//将String 转换为符合格式的日期 
	        weekDay=formatD.format(d);
	     }catch (Exception e){
	        e.printStackTrace();
	     } 
	    return weekDay;
	}
	
	 /**
	 * 获取上周时间段
	 * @param n 为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
	 * @param day1,day2 周几到周几
	 * @return
	 */
	public static String getLastWeek(int n,int day1,int day2){
		Calendar cal = Calendar.getInstance();
		Calendar cal1 = Calendar.getInstance();
		String monday;
		cal.add(Calendar.DATE, n*7);
		cal1.add(Calendar.DATE, n*7);
		cal.set(Calendar.DAY_OF_WEEK,day1);
		cal1.set(Calendar.DAY_OF_WEEK,day2);
		monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return monday + "至" + new SimpleDateFormat("yyyy-MM-dd").format(cal1.getTime());
	}
	
	public static String getLastWeek1(int n,int day1,int day2){
		Calendar cal = Calendar.getInstance();
		Calendar cal1 = Calendar.getInstance();
		String monday;
		cal.add(Calendar.DATE, n*7);
		cal1.add(Calendar.DATE, n*7);
		cal.set(Calendar.DAY_OF_WEEK,day1);
		cal1.set(Calendar.DAY_OF_WEEK,day2);
		cal1.add(Calendar.DATE, 1);
		
		monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return monday + "至" + new SimpleDateFormat("yyyy-MM-dd").format(cal1.getTime());
	}
	
	public static String getYYYYMMDD(Date date) {
		DateFormat yyyymmddFormat = new SimpleDateFormat("yyyy-MM-dd");
		return yyyymmddFormat.format(date);
	}
	
	/**
	 * 根据输入的日期及天数来提前或拖后日期
	 * @param date
	 * @return
	 */
	public static String getYesterDay(Date date,int dayNum){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, dayNum);
		return getYYYYMMDD(cal.getTime());
	}
	
	/**
	 * 根据2个日期获取相隔的天数
	 * @author ljian
	 * @date 2010-7-24
	 * @param dateA
	 * @param dateB
	 * @return
	 */
	public static int getBetweenDayNumber(String dateA,String dateB){
		long dayNumber = 0;
		long DAY = 24L * 60L * 60L * 1000L;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date d1 = df.parse(dateA);
			Date d2 = df.parse(dateB);
			dayNumber = (d2.getTime() - d1.getTime())/DAY;	
		}catch(Exception e){
			e.printStackTrace();
		}
		return (int)dayNumber;
	}
	
	/**
	 * 获取指定月时间段
	 * @return
	 */
	public static String getFirstDayOfMonth(Date date){    
		String str = "";  
		String end = "";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");      

		Calendar lastDate = Calendar.getInstance();  
		Calendar lastDate1 = Calendar.getInstance();
		lastDate.setTime(date);
		lastDate1.setTime(date);
		lastDate.set(Calendar.DATE,1);//设为当前月的1号  
		int daynum = lastDate1.getActualMaximum(Calendar.DATE);
		
		lastDate1.set(Calendar.DATE,daynum);
		str=sdf.format(lastDate.getTime());  
		end = sdf.format(lastDate1.getTime());
		return str + ":" + end;    
	}  
	
	public static String join(String[] s, String spliter){
		StringBuffer sb = new StringBuffer();
		if(s == null){
			return "";
		}
		for(String ss : s){
			sb.append(ss + spliter);
		}
		return sb.substring(0, sb.length()-3);
	}
	
	public static String join(int[] i, String spliter){
		StringBuffer sb = new StringBuffer();
		if(i == null){
			return "";
		}
		for(int ii : i){
			sb.append(ii + spliter);
		}
		return sb.substring(0, sb.length()-1);
	}
	
	/**
	 * 将要放入xml中的字符串中xml特殊字符替换,避免打乱xml格式
	 * @Title: replaceXMLChar
	 * @Description:
	 * @param 
	 * @return String
	 * @author Wu Wenqi(wuwenqi@yihaodian.com)
	 * @date  2013-4-12
	 */
	public static String replaceXMLChar(String str){
		if(str != null && !str.isEmpty()){
			String str1 = str.replaceAll("&", "&amp;");
			String str2 = str1.replaceAll("<", "&lt;");
			String str3 = str2.replaceAll(">", "&gt;");
			String str4 = str3.replaceAll("'", "&apos;");
			String str5 = str4.replaceAll("\"", "&quot;");
			return str5;
		}else{
			return "";
		}
	}
}
