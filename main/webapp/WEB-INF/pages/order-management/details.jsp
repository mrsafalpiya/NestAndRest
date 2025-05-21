<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Admin - Orders List</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="../head.jsp" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin-style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin-product-list.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin-orders-management-details.css">
</head>
<body>
	<div class="admin-layout">
		<%-- Header (top right admin icon) --%>
		<jsp:include page="../admin-header.jsp" />
		<%-- Sidebar --%>
		<jsp:include page="../admin-sidebar.jsp" />

		<%-- Main Order Details Container --%>
		<main class="container details-container">
			<c:if test="${param.success == 'true'}">
				<p style="color: green;">Status updated successfully!</p>
			</c:if>

			<!-- Header -->
			<div class="details-header">
				<a href="${contextPath}/admin-orders"><button
						style="background: transparent;">
						<svg width="36" height="36" viewBox="0 0 36 36" fill="none"
							xmlns="http://www.w3.org/2000/svg">
						<path
								d="M19.5247 23.8334C19.2726 23.8342 19.0336 23.7209 18.8747 23.525L14.8497 18.525C14.597 18.2175 14.597 17.7742 14.8497 17.4667L19.0164 12.4667C19.3109 12.1123 19.837 12.0638 20.1914 12.3584C20.5458 12.6529 20.5943 13.179 20.2997 13.5334L16.5747 18L20.1747 22.4667C20.3828 22.7165 20.4266 23.0645 20.287 23.358C20.1474 23.6516 19.8498 23.8372 19.5247 23.8334Z"
								fill="#637381" />
				</svg>
					</button></a>
				<div class="order-title-container">
					<div class="order-title-top-line">
						<h4>Order #${order.orderId}</h4>
						<span class="status ${fn:toLowerCase(order.orderStatus)}">${order.orderStatus}</span>
					</div>
					<p class="body2">
						<fmt:formatDate value="${order.orderDate}" pattern="dd MMM yyyy"
							var="formattedDate" />${formattedDate}
						<fmt:formatDate value="${order.orderDate}" pattern="hh:mm a"
							var="formattedTime" />${formattedTime}</p>
				</div>
				<form method="POST">
					<input type="hidden" name="order_id" value="${order.orderId}" /> <select
						class="order-status" onchange="this.form.submit()"
						name="order_status_id">
						<c:forEach var="orderStatus" items="${allOrderStatuses}">
							<option value="${orderStatus.orderStatusId}"
								<c:if test="${orderStatus.orderStatusId == order.orderStatusId}">selected</c:if>>${orderStatus.name}</option>
						</c:forEach>
					</select>
				</form>
			</div>

			<!-- Details and Customer Info -->
			<div class="details-customer-info-container">

				<!-- Details Container -->
				<div class="details-container">
					<div class="header">
						<h6>Details</h6>
					</div>
					<div class="items-list-container">
						<c:forEach var="product" items="${order.cartProducts}">
							<div class="item-entry">
								<div class="item-image-title">
									<img
										src="${contextPath}/resources/product-images/${product.productId}.png"
										width="48" height="48" />
									<p class="body2" style="color: var(--text-primary)">${product.name}</p>
								</div>
								<div>
									<c:if test="${product.price != product.salePrice}">
										<p class="subtitle2"
											style="color: #888; text-decoration: line-through">Rs
											${product.price}</p>
									</c:if>
									<p class="subtitle2" style="color: var(--text-primary)">Rs
										${product.salePrice}</p>
									<br />
									<p>x ${product.cartQty}</p>
								</div>
							</div>
							<div class="divider"></div>
						</c:forEach>
					</div>
					<div class="order-details-total-container">
						<div class="order-details-text-container">
							<p class="body2">Subtotal</p>
							<p class="body2">Discount</p>
							<p class="subtitle1">Total</p>
						</div>
						<div class="order-details-text-container prices">

							<c:set var="subTotal" value="${0}" />
							<c:forEach var="product" items="${order.cartProducts}">
								<c:set var="subTotal"
									value="${subTotal + (product.price * product.cartQty)}" />
							</c:forEach>

							<p class="subtitle2" style="color: var(--text-primary)">Rs
								${subTotal}</p>

							<c:set var="totalDiscount" value="${0}" />
							<c:forEach var="product" items="${order.cartProducts}">
								<c:set var="totalDiscount"
									value="${totalDiscount + ((product.price - product.salePrice) * product.cartQty)}" />
							</c:forEach>

							<p class="body2" style="color: #FF5630">- Rs ${totalDiscount}</p>
							<p class="subtitle1">Rs ${subTotal - totalDiscount}</p>
						</div>
					</div>
				</div>

				<!-- Customer Info -->
				<div class="customer-info-div">
					<div class="header">
						<h6 style="width: 100%;">Customer Info</h6>
					</div>
					<div class="info-container">
						<div class="user-info-container">
							<img
								src="${pageContext.request.contextPath}/resources/user-images/${order.user.userId}.png"
								onerror="this.src='${pageContext.request.contextPath}/resources/system/images/Avatar.png'"
								width="48" height="48" style="border-radius: 50%;" />
							<div>
								<p class="subtitle2" style="color: var(--primary-text)">${order.user.name}</p>
								<p class="body2">${order.user.email}</p>
								<p class="body2">${order.user.phone}</p>
							</div>
						</div>
						<div class="divider"></div>
						<div class="shipping-header">
							<h6>Shipping</h6>
						</div>
						<div class="shipping-info-container">
							<p class="body2">Address</p>
							<div>
								<p>${order.address.address}</p>
							</div>
							<p class="body2">Phone number</p>
							<p>${order.address.phoneNumber}</p>
							<p class="body2">Name</p>
							<p>${order.address.userName}</p>
						</div>
					</div>
				</div>
			</div>
		</main>
	</div>
</body>
</html>
