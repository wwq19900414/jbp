<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<order-detail>
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
	
	<c:forEach var="orderProduct" items="${orderProductList}">
	<order-product>
		<tracker-u>${orderProduct.trackU}</tracker-u>
		<clickid>${orderProduct.clickId}</clickid>
		<order-id>${orderProduct.orderCode}</order-id>
		<product-code>${orderProduct.productCode}</product-code>
		<product-name>${orderProduct.productName}</product-name>
		<brand>${orderProduct.brandName}</brand>
		<category-level1>${orderProduct.firstCategory}</category-level1>
		<category-level2>${orderProduct.secondCategory}</category-level2>
		<totalprice-in-cart>${orderProduct.salesOnPrice}</totalprice-in-cart >
		<totalprice-in-order>${orderProduct.salesOnFact}</totalprice-in-order >
		<count>${orderProduct.count}</count>
	</order-product>
	</c:forEach>
</order-detail>

