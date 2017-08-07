<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<coupon>
	<!-- 通用的返回状态元素,当返回的状态码代表不正常时文件中就只会有这一个元素的 -->
	<response-status>${responseStatus}</response-status>

	<c:if test="${totalPage != null}">
	<!-- 总页数 -->
	<total-page>${totalPage}</total-page>
	</c:if>

	<c:if test="${currentPage != null && currentPage != -1}">
	<!-- 当前页 -->
	<current-page>${currentPage}</current-page>
	</c:if>

	<c:if test="${countDate != null}">
	<!-- 数据统计日期 -->
	<count-date>${countDate}</count-date>
	</c:if>
	
	<c:forEach var="coupon" items="${couponList}">
	<coupon-info>
		<tracker-u>${coupon.trackU}</tracker-u>
		<clickid></clickid>
		<coupon-id>${coupon.couponId}</coupon-id>
		<coupon-type>${coupon.couponType}</coupon-type>
		<coupon-amount>${coupon.couponAmount}</coupon-amount>
		<order-code>${coupon.orderCode}</order-code>
		<member-id>${coupon.memberId}</member-id>
	</coupon-info>
	</c:forEach>
</coupon>

