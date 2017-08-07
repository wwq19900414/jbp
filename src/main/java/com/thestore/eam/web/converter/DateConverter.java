/**
 * Copyright (C), 2011-2016 The Store
 * File Name: DateConverter.java
 * Encoding: UTF-8
 * Date: Oct 20, 2011
 * History: 
 */
package com.thestore.eam.web.converter;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.util.StrutsTypeConverter;

import com.thestore.eam.common.AppConstant;

/**
 *
 * @author Wayne Wan(bestirwiny@gmail.com)
 * @version Revision: 1.00 Date: Oct 20, 2011
 */
public class DateConverter extends StrutsTypeConverter {
	private static Log log = LogFactory.getLog(DateConverter.class);
	/* (non-Javadoc)
	 * @see org.apache.struts2.util.StrutsTypeConverter#convertFromString(java.util.Map, java.lang.String[], java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		Date date = null;  
        String dateString = null;  
        if (values != null && values.length > 0) {  
            dateString = values[0];  
            if (dateString != null && !dateString.equals("")) {  
                try {
                	date = DateUtils.parseDate(dateString, new String[]{AppConstant.DATE_FORMAT_PATTERN1, AppConstant.TIME_FORMAT_PATTERN1});
				} catch (ParseException e) {
					log.error("date parser error!", e);
				}
            }  
        }  
        return date;  
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.util.StrutsTypeConverter#convertToString(java.util.Map, java.lang.Object)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map arg0, Object arg1) {
		/**
		 * date to string function can be realized in another place
		 */
		return null;
	}

}
