<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<a href="${contextPath}/product-details?id=${param.id}">
	<div class="item-card item-card-product">
		<c:if test="${param.discountedPrice != '0.0'}">
			<span class="badge sale">SALE</span>
		</c:if>

		<div class="item-img">
			<img src="${contextPath}/resources/product-images/${param.id}.png" />
		</div>
		<div class="item-info">
			<p class="subtitle1">${param.name}</p>
			<div class="price-info">
				<c:if test="${param.discountedPrice != '0.0'}">
					<span class="discounted-price subtitle1">Rs ${param.price}</span>
					<span class="subtitle1">Rs ${param.discountedPrice}</span>
				</c:if>
				<c:if test="${param.discountedPrice == '0.0'}">
					<span class="subtitle1">Rs ${param.price}</span>
				</c:if>
			</div>
		</div>
	</div>
</a>