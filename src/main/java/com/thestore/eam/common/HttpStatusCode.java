/**
 * Copyright (C), 2011-2016 The Store
 * File Name: HttpStatusCode.java
 * Encoding: UTF-8
 * Date: Sep 15, 2011
 * History: 
 */
package com.thestore.eam.common;

/**
 * http status code
 * @author Wayne Wan(bestirwiny@gmail.com)
 * @version Revision: 1.00 Date: Sep 15, 2011
 */
public interface HttpStatusCode {
	final static int STATUS_OK 					 = 200  ;  
	final static int STATUS_CREATED  			 = 201  ;  
	final static int STATUS_MOVED 				 = 301  ;  
	final static int STATUS_REDIRECT  			 = 302  ;  
	final static int STATUS_NOTMODIFIED 		 = 304  ;  
	final static int STATUS_BADREQUEST 			 = 400  ;  
	final static int STATUS_UNAUTHORIZED  		 = 401  ;
	/**
	 * server reject the request
	 */
	final static int STATUS_FORBIDDEN 			 = 403  ;
	final static int STATUS_NOTFOUND  			 = 404  ;
	/**
	 * method could not accept (POST/GET etc..)
	 */
	final static int STATUS_METHODNOTALLOWED     = 405  ;
	/**
	 * accept header specified resource not acceptable
	 */
	final static int STATUS_NOTACCEPTABLE        = 406  ;
	final static int STATUS_REQUESTTIMEOUT       = 408  ;
	/**
	 * request resource not available yet
	 */
	final static int STATUS_GONE                 = 410  ;
	final static int STATUS_INTERNALSERVERERROR  = 500  ;
}
