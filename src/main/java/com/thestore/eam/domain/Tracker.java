package com.thestore.eam.domain;

import java.util.Date;

public class Tracker extends BaseEntity {

	private static final long serialVersionUID = -6780502076376854965L;
	private String cookieId;// COOKIE_ID,唯一标识同一台机器上的同一个浏览器
	private String sessionId;
	private String userAgent;
	private String url;// 当前访问的url
	private String referer;// 跳转到当前页面之前一个页面的url
	private Date trackTime;// 记录时间
	private String os;

	public void setCookieId(String cookieId) {
		this.cookieId = cookieId;
	}

	public String getCookieId() {
		return cookieId;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getReferer() {
		return referer;
	}

	public void setTrackTime(Date trackTime) {
		this.trackTime = trackTime;
	}

	public Date getTrackTime() {
		return trackTime;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getOs() {
		return os;
	}
}
