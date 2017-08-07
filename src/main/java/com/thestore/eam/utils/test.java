package com.thestore.eam.utils;

import java.util.Calendar;
import java.util.Date;

public class test {
	public static void main(String[] args) throws Exception {	
		Integer a=DateUtils.getWeekInYear(new Date());
		System.out.println(a);
		Date[] dates = DateUtils.getWeekScale(new Date(),a);
		System.out.println(dates[0]);
		System.out.println(dates[1]);
		Date startDate=new Date();
		startDate=DateUtils.add(startDate, Calendar.DAY_OF_YEAR, 1);
		System.out.println(startDate);
	}
	 
}
