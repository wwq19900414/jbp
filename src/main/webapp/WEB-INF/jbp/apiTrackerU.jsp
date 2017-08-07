<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<api>
	<!-- 通用的返回状态元素,当返回的状态码代表不正常时文件中就只会有这一个元素的 -->
	<response-status>${responseStatus}</response-status>
	
	<c:if test="${apiAccount != null}">
		<api-account>${apiAccount}</api-account>
	</c:if>
	
	<c:forEach var="track" items="${trackerUList}">
	<tracker>
		<tracker-u>${track.trackerU}</tracker-u>
		<begin-time>${track.beginTimeStr}</begin-time>
		<end-time>${track.endTimeStr}</end-time>
		
		<c:choose>

		<c:when test="${track.status == 0}">
			<status>正常</status>
		</c:when>
		<c:when test="${track.status == 1}">
			<status>已过期</status>
		</c:when>
		<c:when test="${track.status == 2}">
			<status>未开始</status>
		</c:when>
		<c:otherwise>
			<status>未知状态</status>
		</c:otherwise>
		</c:choose>
	</tracker>
	</c:forEach>
</api>

