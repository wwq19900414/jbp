package com.thestore.eam.web.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.thestore.eam.domain.Tracker;
import com.thestore.eam.service.ITrackerService;
import com.thestore.eam.utils.MD5;

/**
 * DMP标签管理控制器
 * 
 * @author Wu Wenqi(wuwenqi@yihaodian.com)
 * @version Revision: 1.00 Date: 2015-07-21
 */
@Namespace("/")
public class CookieAction extends BaseAction {
	private static final long serialVersionUID = 5838662157923910080L;
	private ITrackerService trackerService;

	private Tracker tracker = new Tracker();
	public static final String COOKIE_ID = "cookie_id";

	@Action(value = "addCookie")
	public void dmpLabel() {
		String cookieId = this.getCookie(COOKIE_ID);
		if (StringUtils.isBlank(cookieId)) {
			cookieId = MD5.generateCookieId();
			addCookie(COOKIE_ID, cookieId);
		}

		String sessionId = this.getServletRequest().getRequestedSessionId();
		tracker.setSessionId(sessionId);

		String url = this.getServletRequest().getHeader("Referer");
		tracker.setUrl(url);

		String userAgent = this.getServletRequest().getHeader("User-Agent");
		tracker.setUserAgent(userAgent);

		tracker.setCookieId(cookieId);
		trackerService.saveTracker(tracker);
	}

	/**
	 * Cookie追加
	 * 
	 * @return
	 * @throws Exception
	 */
	private void addCookie(String name, String value) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(-60 * 60 * 24 * 365 * 100);
		this.getServletResponse().addCookie(cookie);
	}

	/**
	 * Cookie取得
	 * 
	 * @return
	 * @throws Exception
	 */
	private String getCookie(String name) {
		HttpServletRequest request = ServletActionContext.getRequest();
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return "";
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name)) {
				return cookie.getValue();
			}
		}
		return null;
	}

	public void setTrackerService(ITrackerService trackerService) {
		this.trackerService = trackerService;
	}

	public void setTracker(Tracker tracker) {
		this.tracker = tracker;
	}

	public Tracker getTracker() {
		return tracker;
	}
}
