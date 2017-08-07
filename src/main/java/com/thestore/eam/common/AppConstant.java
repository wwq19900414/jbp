/**
 * Copyright (C), 2011-2016 The Store
 * File Name: AppConstant.java
 * Encoding: UTF-8
 * Date: Sep 15, 2011
 * History: 
 */
package com.thestore.eam.common;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * application Constant definition
 * 
 * @author Wayne Wan(bestirwiny@gmail.com)
 * @version Revision: 1.00 Date: Sep 15, 2011
 */
public class AppConstant {
	public static final String DSP_TRACKER_U = "10948789617";
	public static final Locale LOCALE = Locale.CHINA;
	
	public static final String DOMAIN = "yihaodian.com";
	
	public static final String LOG_DIR ;
	public static final int PAGE_SIZE = 5000;
	public static final String APP_PROPERTIES = "app";
	
	static{
		ResourceBundle rs = ResourceBundle.getBundle(APP_PROPERTIES);
		LOG_DIR = rs.getString("log_dir");
	}
	
	/**
	 * second
	 */
	public static final int SESSION_TIMEOUT = 3600;
	
	public static final int BUF_SIZE = 1024 * 50;
	
	public static final String UPLOAD_FOLDER_NAME = "Upload";
	
	public static final String KEY_PAGE_SIZE = "pageSize";
	
	public static final String KEY_CURRENT_PAGE = "currentPage";
	
	public static final String KEY_SORT_MODE = "sortMode";
	
	public static final String KEY_SORT_COLUMN = "sortColumn";
	
	public static final String KEY_EXPORT      = "exportExcel";
	
	public static final int SORT_CLOSE = 0;
	
	public static final int SORT_ASC = 1;
	
	public static final int SORT_DESC = -1;
	
	public static final String RESPONSE_EXP_PREFIX = "EAM_EXCEPTION:";
	/**
	 * "YYYY-MM-DD"
	 */
	public static String DATE_FORMAT_PATTERN1 = "yyyy-MM-dd";
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static String TIME_FORMAT_PATTERN1 = "yyyy-MM-dd HH:mm:ss";
	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static String TIME_FORMAT_PATTERN2 = "yyyy-MM-dd HH:mm";
	
	public static String[] DATETIME_PARSER = new String[]{DATE_FORMAT_PATTERN1, TIME_FORMAT_PATTERN1, TIME_FORMAT_PATTERN2};
	
	public static final String KEY_USER_LOGIN = "eam.user";
	
	public static final String LOG_FORMAT_PATTERN = "[Operation Type]: {0},[Operation target]: {1},[Remark]: {2}";
}
