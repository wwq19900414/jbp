/**
 * Copyright (C), 2011-2016 The Store
 * File Name: BaseAction.java
 * Encoding: UTF-8
 * Date: Sep 14, 2011
 * History: 
 */
package com.thestore.eam.web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.thestore.eam.common.AppConstant;
import com.thestore.eam.common.HttpStatusCode;
import com.thestore.eam.common.PageController;
import com.thestore.eam.common.ThreadLocalObject;
import com.thestore.eam.common.ThreadLocalVar;
import com.thestore.eam.exception.BusinessException;

/**
 * Base Action
 * 
 * @author Wayne Wan(bestirwiny@gmail.com)
 * @version Revision: 1.00 Date: Sep 14, 2011
 */
/**
 * 
 * @author Wayne Wan(bestirwiny@gmail.com)
 * @version Revision: 1.00 Date: Oct 25, 2011
 */
public class BaseAction extends ActionSupport implements HttpStatusCode, ServletResponseAware, ServletRequestAware {

	private static final long serialVersionUID = -4889798177797915236L;

	private static Log log = LogFactory.getLog(BaseAction.class);

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	private int pageSize = -1;

	protected int currentPage = -1;

	/**
	 * 用于表示jsonp请求，有值说明是跨域的jsonp请求,jquery的默认callback参数名：jsoncallback
	 */
	protected String jsoncallback;

	protected void writeJSON(String content) {
		writeMessage(content, "text/json;charset=UTF-8", true);
	}

	/**
	 * write JSONP text to client side(cache enabled)
	 * 
	 * @param content
	 */
	protected void writeJSONP(String content) {
		if (StringUtils.isBlank(jsoncallback)) {// jsoncallback无值，则是普通的请求
			this.writeJSON(content);

		} else {// jsoncallback有值，则是跨域名的jsonp请求
			this.writeJSON(jsoncallback + "(" + content + ")");
		}
	}

	/**
	 * write plain text to client side(cache enabled)
	 * 
	 * @param content
	 */
	protected void writePlainText(String content) {
		writeMessage(content, "text/plain;charset=UTF-8", true);
	}

	/**
	 * write HTML to client side(cache enabled)
	 * 
	 * @param content
	 */
	protected void writeHTML(String content) {
		writeMessage(content, "text/html;charset=UTF-8", true);
	}

	/**
	 * write XML to client side(cache enabled)
	 * 
	 * @param content
	 */
	protected void writeXML(String content) {
		writeMessage(content, "application/xml;charset=UTF-8", true);
	}

	/**
	 * write character message to client side using specified content type<br>
	 * note: the character set in the content type should be "UTF-8"
	 * 
	 * @param msg
	 * @param contentType
	 * @param cacheEnable
	 */
	protected void writeMessage(String msg, String contentType, boolean cacheEnable) {
		response.setContentType(contentType);
		if (!cacheEnable) {
			// disable browser cache
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		}
		try {
			write(response.getOutputStream(), msg.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			log.error("Unsupported Encoding Exception!", e);
		} catch (IOException e) {
			log.error("Geting OutputSteam from HttpServletResponse failed!", e);
		}
	}

	/**
	 * Sets the status code for this response.<br>
	 * This method is used to set the return status code when there is no error<br>
	 * (for example, for the status codes SC_OK or SC_MOVED_TEMPORARILY). <br>
	 * If there is an error, and the caller wishes to invoke an error-page
	 * defined<br>
	 * in the web application, the sendError method should be used instead.<br>
	 * 
	 * @param statusCode
	 */
	protected void setResponseStatusCode(int statusCode) {
		response.setStatus(statusCode);
	}

	/**
	 * Sends an standard HTTP error status code<br>
	 * to the client and clearing the buffer.
	 * 
	 * @param errorStatusCode
	 */
	protected void setErrorStatusCode(int errorStatusCode) {
		try {
			response.sendError(errorStatusCode);
		} catch (IOException e) {
			log.error("send error status code to client error! ", e);
		}
	}

	/**
	 * send the exception detail message to client side.
	 * 
	 * @param e
	 */
	protected void sendErrorMessage(BusinessException e) {
		this.writePlainText(AppConstant.RESPONSE_EXP_PREFIX + e.getMessage());
	}

	/**
	 * write byte stream to client side<br>
	 * notes: this method should not used to write big data, <br>
	 * for the big data situation, we should split the big data into smaller
	 * one.
	 * 
	 * @param out
	 * @param bytes
	 */
	protected void write(OutputStream out, byte[] bytes) {
		try {
			out.write(bytes);
			out.flush();
		} catch (IOException e) {
			log.error("Output data to client side failed!", e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				log.error("Closing OutputSteam failed!", e);
			}
		}
	}

	/**
	 * wrap the original json collection with paging informations for DHTMLX
	 * framework
	 * 
	 * @param jsonCollection
	 * @return
	 */
	protected String dHTMLXJsonCollectionWrapper(String jsonCollection) {
		PageController pageCtrl = getPageCtrl();
		if (pageCtrl == null) {
			return jsonCollection;
		}

		StringBuffer sb = new StringBuffer();
		sb.append("{\"response\":{").append("\"totalRecords\":").append(pageCtrl.getRecordCount())
				.append(",\"currentPage\":").append(pageCtrl.getCurrentPage()).append(",\"pageSize\":")
				.append(pageCtrl.getPageSize()).append(",\"pageCount\":").append(pageCtrl.getPageCount())
				.append(", \"rows\":").append(jsonCollection).append("}}");

		return sb.toString();
	}

	/**
	 * get the paging component from current thread
	 * 
	 * @return
	 */
	protected PageController getPageCtrl() {
		ThreadLocalObject tlo = ThreadLocalVar.get();
		if (tlo != null) {
			return tlo.getPageContrller();
		}
		return null;
	}

	/**
	 * 标示该请求是否是一个导出请求
	 * 
	 * @Title: isExportRequest
	 * @Description:
	 * @param
	 * @return Boolean
	 * @author Wayne Wan(wanyi@yihaodian.com)
	 * @date Jan 3, 2012
	 */
	protected Boolean isExportRequest() {
		ThreadLocalObject tlo = ThreadLocalVar.get();
		if (tlo != null) {
			return tlo.isExportRequest();
		}
		return false;
	}

	protected HttpSession getSession() {
		return this.getServletRequest().getSession();
	}

	/**
	 * get a cookie item by key
	 * 
	 * @param key
	 * @return
	 */
	protected Cookie getCookieItem(String key) {
		Cookie[] cookies = getServletRequest().getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().toUpperCase().equals(key.toUpperCase())) {
					return c;
				}
			}
		}
		return null;
	}

	/**
	 * <pre>
	 * add a cookie item 
	 * duration ， cookie的有效时间，单位是秒
	 * </pre>
	 * 
	 * @param key
	 * @param value
	 * @param duration
	 */
	protected void addCookie(String key, String value, int duration) {
		Cookie cookie = new Cookie(key, value);
		if (duration > 0) {
			cookie.setMaxAge(duration);
		}
		cookie.setDomain(AppConstant.DOMAIN);
		cookie.setPath("/");
		this.getServletResponse().addCookie(cookie);
	}

	/**
	 * 
	 * @Title: copy
	 * @Description: 上传文件
	 * @param @param src 临时上传地址
	 * @param @param dest 目标上传地址
	 * @param @param size 文件限制大小
	 * @return void 返回类型
	 * @author
	 * @date 2011-10-24
	 * @throws
	 */
	protected void copy(File src, File dest, int size) {
		InputStream in = null;
		OutputStream out = null;
		byte[] buffer = new byte[size];
		try {
			if (!dest.exists()) {
				dest.createNewFile();
			}
			in = new BufferedInputStream(new FileInputStream(src), size);
			out = new BufferedOutputStream(new FileOutputStream(dest), size);
			while (in.read(buffer) > 0) {
				out.write(buffer);
			}
		} catch (FileNotFoundException e) {
			log.error("file not found exception!", e);
		} catch (IOException e) {
			log.error("copy file failed!", e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				log.error("Close input stream failed!", e);
			}
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				log.error("Close output stream failed!", e);
			}
		}
	}

	/**
	 * 文件将被上传到WebRoot/Upload/目录下，上传后浏览器端可以直接显示出上传文件(若可以被浏览器解析)
	 * 
	 * @Title: getUploadPath
	 * @Description: 获取上次文件目标地址
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author
	 * @date 2011-10-24
	 * @throws
	 */
	protected String getUploadPath() {
		String uploadPath = getServletContext().getRealPath("/") + File.separator + AppConstant.UPLOAD_FOLDER_NAME;
		File uploadFilePath = new File(uploadPath);
		if (!uploadFilePath.exists()) {
			uploadFilePath.mkdirs();
		}
		return uploadPath;
	}

	/**
	 * get the related HttpServletRequest object
	 * 
	 * @return
	 */
	protected HttpServletRequest getServletRequest() {
		return this.request;
	}

	/**
	 * get the related HttpServletResponse object
	 * 
	 * @return
	 */
	protected HttpServletResponse getServletResponse() {
		return this.response;
	}

	/**
	 * get related ServletContext
	 * 
	 * @return
	 */
	protected ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.ServletResponseAware#setServletResponse
	 * (javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setJsoncallback(String jsoncallback) {
		this.jsoncallback = jsoncallback;
	}

	public String getJsoncallback() {
		return jsoncallback;
	}

}
