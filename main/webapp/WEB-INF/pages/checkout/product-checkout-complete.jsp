<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<jsp:include page="../head.jsp" />

<link rel="stylesheet"
	href="${contextPath}/css/styles.css" />
<link rel="stylesheet"
	href="${contextPath}/css/product-checkout-complete.css" />

<title>Checkout - Nest and Rest Furniture</title>
</head>
<body>
	<div class="container">
		<h1>Thank you for your purchase!</h1>

		<div class="image-container">
			<img
				src="${contextPath}/resources/system/images/illustration-order_complete.png"
				alt="illustration-order_complete" class="auth-header-logo" />
		</div>

		<p class="text-muted">Thanks for placing order :</p>
		<p class="order-id">Order ID: #${orderId}</p>

		<p class="text-muted">
			We will send you a notification within 5 days when it ships. <br />
			If you have any question or queries then fell to get in contact us.
		</p>

		<p class="signature">All the best,</p>

		<div class="button-group">
			<button class="btn-outline"
				onclick="location.href='${contextPath}/'">←
				Continue shopping</button>
		</div>
	</div>
</body>
</html>
