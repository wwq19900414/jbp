package com.thestore.eam.utils;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import org.apache.commons.lang.time.DateFormatUtils;

import com.thestore.eam.common.AppConstant;

public class DateUtils {
	
	/**
	 * 根据日期和api数据类型获取api数据的月份分表名称<br>
	 * type字段：<br>
	 * 1:PAGE_VIEW<BR>
	 * 2:CART_INFO<BR>
	 * 3:ORDER_INFO<BR>
	 * 4:ORDER_PRODUCT<BR>
	 * 5:TRACK_INFO<BR>
	 * 6:COUPON<BR>
	 */
	public static String getTableName(Date date,int type){
		String monthStr = DateFormatUtils.format(date, "yyyy_MM");
		String tableName = "";
		switch(type){
		case 1:
			tableName = "PAGE_VIEW_"+monthStr;
			break;
		case 2:
			tableName = "CART_INFO_"+monthStr;
			break;
		case 3:
			tableName = "ORDER_INFO_"+monthStr;
			break;
		case 4:
			tableName = "ORDER_PRODUCT_"+monthStr;
			break;
		case 5:
			tableName = "TRACK_INFO_"+monthStr;
			break;
		case 6: 
			tableName = "COUPON_"+monthStr;
			break;
		}
		
		return tableName;
	}
	
	public static String[] HOURS = new String[]{
		"00:00-00:59","01:00-01:59","02:00-02:59","03:00-03:59","04:00-04:59","05:00-05:59","06:00-06:59","07:00-07:59",
        "08:00-08:59","09:00-09:59","10:00-10:59","11:00-11:59","12:00-12:59","13:00-13:59","14:00-14:59","15:00-15:59",
        "16:00-16:59","17:00-17:59","18:00-18:59","19:00-19:59","20:00-20:59","21:00-21:59","22:00-22:59","23:00-23:59"
	};

	/**
	 * @return
	 */
	public static Date getNowDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(0);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}
	
	public static Timestamp getNowTimestamp() {
		return new Timestamp(new Date().getTime());
	}
	public static java.sql.Date getSqlTimestamp() {
		return new java.sql.Date(new Date().getTime());
	}

	/**
	 * @return
	 */
	public static Date getNowDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(0);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}

	/**
	 */
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 */
	public static String getStringDateShort(Date date) {
		if(date == null){
			return "";
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * @return
	 */
	public static String getTimeShort() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		if (strDate != null) {
			Date strtodate = formatter.parse(strDate, pos);
			return strtodate;
		}
		return null;
	}

	/**
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrLong(Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 
	 * @param dateDate
	 * @param k
	 * @return
	 */
	public static String dateToStr(Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(dateDate);
		return dateString;
	}
	
	/**
	 * use the specified pattern formating a date
	 * @Title: dateToStr
	 * @Description:
	 * @param @param dateDate
	 * @param @param format
	 * @param @return
	 * @return String
	 * @author Wayne Wan(wanyi@yihaodian.com)
	 * @date  Oct 27, 2011
	 */
	public static String dateToStr(Date dateDate,String format) {
		if(dateDate==null){
			return "";
		}
		return DateFormatUtils.format(dateDate, format);
	}
	/**
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		if (strDate == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 
	 * @return
	 */
	public static Date getNow() {
		Date currentTime = new Date();
		return currentTime;
	}

	/**
	 * 
	 * @param day
	 * @return
	 */
	public static Date getLastDate(long day) {
		Date date = new Date();
		long date_3_hm = date.getTime() - 3600000 * 24 * day;
		Date date_3_hm_date = new Date(date_3_hm);
		return date_3_hm_date;
	}

	/**
	 * 
	 */
	public static String getStringToday() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 */
	public static String getHour() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String hour;
		hour = dateString.substring(11, 13);
		return hour;
	}

	/**
	 * 
	 * @return
	 */
	public static String getTime() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String min;
		min = dateString.substring(14, 16);
		return min;
	}

	/**
	 * ����û������ʱ���ʾ��ʽ�����ص�ǰʱ��ĸ�ʽ �����yyyyMMdd��ע����ĸy���ܴ�д��
	 * 
	 * @param sformat
	 *            yyyyMMddhhmmss
	 * @return
	 */
	public static String getUserDate(String sformat) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(sformat);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * ����Сʱʱ���Ĳ�ֵ,���뱣֤����ʱ�䶼��"HH:MM"�ĸ�ʽ�������ַ��͵ķ���
	 */
	public static String getTwoHour(String st1, String st2) {
		String[] kk = null;
		String[] jj = null;
		kk = st1.split(":");
		jj = st2.split(":");
		if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0])){
			return "0";
		}else {
			double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1])
					/ 60;
			double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1])
					/ 60;
			if ((y - u) > 0){
				return y - u + "";
			}else{
				return "0";
			}
		}
	}

	/**
	 * �õ��������ڼ�ļ������
	 */
	public static String getTwoDay(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		try {
			Date date = myFormatter.parse(sj1);
			Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return "";
		}
		return day + "";
	}

	/**
	 * ʱ��ǰ�ƻ���Ʒ���,����JJ��ʾ����.
	 */
	public static String getPreTime(String sj1, String jj) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mydate1 = "";
		try {
			Date date1 = format.parse(sj1);
			long time = ( date1.getTime() / 1000L ) + Integer.parseInt(jj) * 60L;
			date1.setTime(time * 1000);
			mydate1 = format.format(date1);
		} catch (Exception e) {
		}
		return mydate1;
	}

	/**
	 * �õ�һ��ʱ���Ӻ��ǰ�Ƽ����ʱ��,nowdateΪʱ��,delayΪǰ�ƻ���ӵ�����
	 */
	public static String getNextDay(String nowdate, String delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String mdate = "";
			Date d = strToDate(nowdate);
			long myTime = (d.getTime() / 1000L) + Integer.parseInt(delay) * 24L	* 60L * 60L;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * �ж��Ƿ�����
	 * 
	 * @param ddate
	 * @return
	 */
	public static boolean isLeapYear(String ddate) {

		/**
		 * ��ϸ��ƣ� 1.��400��������꣬���� 2.���ܱ�4�����������
		 * 3.�ܱ�4���ͬʱ���ܱ�100����������� 3.�ܱ�4���ͬʱ�ܱ�100�����������
		 */
		Date d = strToDate(ddate);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 400) == 0){
			return true;
		}else if ((year % 4) == 0) {
			if ((year % 100) == 0){
				return false;
			}else{
				return true;
			}
		}else{
			return false;
		}
	}

	/**
	 * ��������ʱ���ʽ 26 Apr 2006
	 * 
	 * @param str
	 * @return
	 */
	public static String getEDate(String str) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(str, pos);
		String j = strtodate.toString();
		String[] k = j.split(" ");
		return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
	}

	/**
	 * ��ȡһ���µ����һ��
	 * 
	 * @param dat
	 * @return
	 */
	public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
		String str = dat.substring(0, 8);
		String month = dat.substring(5, 7);
		int mon = Integer.parseInt(month);
		if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8
				|| mon == 10 || mon == 12) {
			str += "31";
		} else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
			str += "30";
		} else {
			if (isLeapYear(dat)) {
				str += "29";
			} else {
				str += "28";
			}
		}
		return str;
	}

	/**
	 * �ж϶���ʱ���Ƿ���ͬһ����
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)){
				return true;
			}
		} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
			// ���12�µ����һ�ܺ�������һ�ܵĻ������һ�ܼ���������ĵ�һ��
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)){
				return true;
			}
		} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)){
				return true;
			}
		}
		return false;
	}

	/**
	 * ����������,���õ���ǰʱ�����ڵ�����ǵڼ���
	 * 
	 * @return
	 */
	public static String getSeqWeek() {
		Calendar c = Calendar.getInstance();
		String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
		if (week.length() == 1){
			week = "0" + week;
		}
		String year = Integer.toString(c.get(Calendar.YEAR));
		return year + week;
	}

	/**
	 * ���һ���������ڵ��ܵ����ڼ������ڣ���Ҫ�ҳ�2002��2��3�������ܵ�����һ�Ǽ���
	 * 
	 * @param sdate
	 * @param num
	 * @return
	 */
	public static String getWeek(String sdate, String num) {
		// ��ת��Ϊʱ��
		Date dd = DateUtils.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(dd);
		if (num.equals("1")){
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		}else if (num.equals("2")){
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		}else if (num.equals("3")){
			c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		}else if (num.equals("4")){
			c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		}else if (num.equals("5")){
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		}else if (num.equals("6")){
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		}else if (num.equals("0")){
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		}
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	/**
	 * ���һ�����ڣ����������ڼ����ַ�
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeek(String sdate) {
		// ��ת��Ϊʱ��
		Date date = DateUtils.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour�д�ľ������ڼ��ˣ��䷶Χ 1~7
		// 1=������ 7=��������������
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	public static String getWeekStr(String sdate) {
		String str = "";
		str = DateUtils.getWeek(sdate);
		if ("1".equals(str)) {
			str = "������";
		} else if ("2".equals(str)) {
			str = "����һ";
		} else if ("3".equals(str)) {
			str = "���ڶ�";
		} else if ("4".equals(str)) {
			str = "������";
		} else if ("5".equals(str)) {
			str = "������";
		} else if ("6".equals(str)) {
			str = "������";
		} else if ("7".equals(str)) {
			str = "������";
		}
		return str;
	}

	/**
	 * ����ʱ��֮�������
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDays(String date1, String date2) {
		if (date1 == null || date1.equals("")){
			return 0;
		}
			
		if (date2 == null || date2.equals("")){
			return 0;
		}
			
		// ת��Ϊ��׼ʱ��
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {
		}
		
		long day = 0;
		if(date != null){
			day = ( date.getTime() - mydate.getTime() ) / (24 * 60 * 60 * 1000);
		}
		return day;
	}

	/**
	 * �γ����µ����� �� ��ݴ����һ��ʱ�䷵��һ���ṹ ������ ����һ ���ڶ� ������ ������
	 * ������ ������ �����ǵ��µĸ���ʱ�� �˺���ظ������һ�����������ڵ�����
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getNowMonth(String sdate) {
		// ȡ��ʱ�������µ�һ��
		sdate = sdate.substring(0, 8) + "01";

		// �õ�����µ�1�������ڼ�
		Date date = DateUtils.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int u = c.get(Calendar.DAY_OF_WEEK);
		String newday = DateUtils.getNextDay(sdate, (1 - u) + "");
		return newday;
	}

	/**
	 * ȡ����ݿ����� ��ɸ�ʽΪyyyymmddhhmmss+kλ�����
	 * 
	 * @param k
	 *            ��ʾ��ȡ��λ���������Լ���
	 */

	public static String getNo(int k) {

		return getUserDate("yyyyMMddhhmmss") + getRandom(k);
	}

	/**
	 * ����һ�������
	 * 
	 * @param i
	 * @return
	 */
	public static String getRandom(int i) {
		Random jjj = new Random();
		// int suiJiShu = jjj.nextInt(9);
		if (i == 0){
			return "";
		}
			
		StringBuffer jj = new StringBuffer("");
		for (int k = 0; k < i; k++) {
			jj.append(jjj.nextInt(9));
		}
		return jj.toString();
	}

	/**
	 * 
	 * @param args
	 */
	public static boolean RightDate(String date) {

		SimpleDateFormat sdf = null;
		if (date == null){
			return false;
		}
		if (date.length() > 10) {
			sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		} else {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		}
		try {
			sdf.parse(date);
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

	/**
	 * ��formBean ����ַ�ʱ��(yyyy-MM-dd) ת����Date����
	 * 
	 * @param formDate
	 * @return
	 */
	public static Date formBeanDateToPODate(String formDate) {
		try {
			if (formDate != null) {
				if (!formDate.trim().equals("")) {
					System.out.println("---------formdate:" + formDate);
					return java.sql.Date.valueOf(formDate);
				}
			}
		} catch (Exception e) {
			System.out.println("DateUtils:ʱ��ת���쳣");
			return new Date();
		}
		return null;

	}

	/**
	 * get the week start date and end date in a year
	 * @Title: getWeekScale
	 * @Description: get the week start date and end date in a year
	 * @param @param date  the date which contains this week
	 * @param @param weekInYear
	 * @param @return
	 * @return Date[]
	 * @author Wayne Wan(wanyi@yihaodian.com)
	 * @date  Oct 28, 2011
	 */
	public static Date[] getWeekScale(Date date, int weekInYear){
		Date[] dates = new Date[2];
		Calendar cal = Calendar.getInstance(AppConstant.LOCALE);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(date);
		/**
		 * if previous week's weekIndex > weekInYear, then means
		 * the input weekInYear is the next year's first week
		 */
		cal.add(Calendar.WEEK_OF_YEAR, -1);
		if(getWeekInYear(cal.getTime())>weekInYear){
			cal.add(Calendar.YEAR, 1);
			cal.set(Calendar.WEEK_OF_YEAR, 1);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			dates[0] = cal.getTime();
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			dates[1] = cal.getTime();
		}else{
			cal.add(Calendar.WEEK_OF_YEAR, 1);
			cal.set(Calendar.WEEK_OF_YEAR, weekInYear);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			dates[0] = cal.getTime();
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			dates[1] = cal.getTime();
		}
		return dates;
	}
	
	public static int getWeekInYear(Date date){
		Calendar cal = Calendar.getInstance(AppConstant.LOCALE);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}
	
	public static int getWeekIndex(Date date){
		Calendar cal = Calendar.getInstance(AppConstant.LOCALE);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(date);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return dayOfWeek==0?7:dayOfWeek;
	}
	
	public static int getMonthInYear(Date date){
		Calendar cal = Calendar.getInstance(AppConstant.LOCALE);
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}
	
	/**
	 * get the month start date and end date in a year
	 * @Title: getMonthScale
	 * @Description: get the month start date and end date in a year
	 * @param @param date  the date which contains this month
	 * @param @param monthInYear
	 * @param @return
	 * @return Date[]
	 * @author Wayne Wan(wanyi@yihaodian.com)
	 * @date  Oct 28, 2011
	 */
	public static Date[] getMonthScale(Date date, int monthInYear){
		Date[] dates = new Date[2];
		Calendar cal = Calendar.getInstance(AppConstant.LOCALE);
		cal.setTime(date);
		cal.set(Calendar.MONTH, monthInYear);
		int dayInMonth = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, dayInMonth);
		dates[0] = cal.getTime();
		dayInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, dayInMonth);
		dates[1] = cal.getTime();
		return dates;
	}
	
	/**
	 * get yesterday date
	 * @Title: getYesterday
	 * @Description: get yesterday date
	 * @param 
	 * @return Date
	 * @author Wayne Wan(wanyi@yihaodian.com)
	 * @date  Nov 7, 2011
	 */
	public static Date getYesterday(){
		Calendar cal = Calendar.getInstance(AppConstant.LOCALE);
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return cal.getTime();
	}
	
	public static Date getBeforeNDay(int offset){
		Calendar cal = Calendar.getInstance(AppConstant.LOCALE);
		cal.add(Calendar.DAY_OF_YEAR, offset);
		return cal.getTime();
	}
	
	public static Date getSomeDayWithHour(Date date, int hour){
		Calendar cal = Calendar.getInstance(AppConstant.LOCALE);
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		return cal.getTime();
	}
	
	public static Date getYesterdayWithHour(int hour){
		Calendar cal = Calendar.getInstance(AppConstant.LOCALE);
		cal.add(Calendar.DAY_OF_YEAR, -1);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		return cal.getTime();
	}
	
	public static Date getNextYear(){
		Calendar cal = Calendar.getInstance(AppConstant.LOCALE);
		cal.add(Calendar.YEAR, 1);
		return cal.getTime();
	}
	
	/**
	 * Adds or subtracts the specified amount of time to the given calendar field
	 * @Title: add
	 * @Description: Adds or subtracts the specified amount of time to the given calendar field
	 * @param 
	 * @return Date
	 * @author Wayne Wan(wanyi@yihaodian.com)
	 * @date  Nov 7, 2011
	 */
	public static Date add(Date date, int type, int count){
		Calendar cal = Calendar.getInstance(AppConstant.LOCALE);
		cal.setTime(date);
		cal.add(type, count);
		return cal.getTime();
	}
	
	public static int getHour(Date date){
		if(date==null){
			return 0;
		}
		Calendar cal = Calendar.getInstance(AppConstant.LOCALE);
		cal.setTime(date);
		return cal.get(Calendar.HOUR_OF_DAY);
	}
	
	public static Date setHour(Date date, int hour){
		if(date==null){
			return null;
		}
		Calendar cal = Calendar.getInstance(AppConstant.LOCALE);
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		return cal.getTime();
	}
	
	public static Date getFirstDayOfYear(Date date){
		Calendar cal = Calendar.getInstance(AppConstant.LOCALE);
		cal.setTime(date);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
		
	}
	
	public static Date getLastDayOfYear(Date date){
		Calendar cal = Calendar.getInstance(AppConstant.LOCALE);
		cal.setTime(date);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		return cal.getTime();
	}
	
	public static void main(String[] args){
		System.out.println(dateToStrLong(getFirstDayOfYear(new Date())));
		System.out.println(dateToStrLong(getLastDayOfYear(new Date())));
	}
}
