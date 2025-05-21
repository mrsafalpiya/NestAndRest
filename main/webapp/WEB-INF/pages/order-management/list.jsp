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
	href="${contextPath}/css/admin-style.css">
<link rel="stylesheet"
	href="${contextPath}/css/admin-product-list.css">
<link rel="stylesheet"
	href="${contextPath}/css/admin-orders-management-list.css">
</head>
<body>
	<div class="admin-layout">
		<%-- Header (top right admin icon) --%>
		<jsp:include page="../admin-header.jsp" />
		<%-- Sidebar --%>
		<jsp:include page="../admin-sidebar.jsp" />

		<%-- Main Orders List Container --%>
		<main class="container">
			<div class="page-title">
				<h4>Orders List</h4>
				<div class="links">
					<a href="${contextPath}/admin">Dashboard</a>
					<svg width="4" height="4" viewBox="0 0 4 4" fill="none"
						xmlns="http://www.w3.org/2000/svg">
						<circle cx="2" cy="2" r="2" fill="#919EAB" />
					</svg>
					<a href="#">Orders List</a>
				</div>
			</div>

			<div class="list-table-container">
				<!-- Filters -->
				<div class="table-filters">
					<form style="width: 100%">
						<p class="input-label">Search by Order ID</p>
						<input type="text" class="input-text" name="order_id"
							value="${orderId}" />
					</form>
				</div>

				<!-- Results Count -->
				<div class="results-count">
					<p class="body2">
						<span class="subtitle2" style="color: #1c252e">${orders.size()}</span>
						results found
					</p>
				</div>

				<!-- Results Table -->
				<div class="results-table-container">
					<table class="results-table">
						<tr>
							<th>Order</th>
							<th class="max">Customer</th>
							<th>Date</th>
							<th>Items</th>
							<th>Price</th>
							<th>Status</th>
						</tr>
						<c:forEach var="order" items="${orders}">
							<tr
								onclick="window.location='${contextPath}/admin-order-details?id=${order.orderId}';"
								style="cursor: pointer;">
								<td>#${order.orderId}</td>
								<td>
									<div class="customer-info">
										<img
											src="${contextPath}/resources/user-images/${order.user.userId}.png"
											onerror="this.src='${contextPath}/resources/system/images/Avatar.png'"
											width="40" height="40" />
										<div class="customer-details">
											<p class="body2 name">${order.user.name}</p>
											<p class="body2 phone">${order.user.phone}</p>
										</div>
									</div>
								</td>
								<td>
									<div>
										<p class="body2" style="margin-bottom: 4px">
											<fmt:formatDate value="${order.orderDate}"
												pattern="dd MMM yyyy" var="formattedDate" />${formattedDate}
										</p>
										<p class="caption">
											<fmt:formatDate value="${order.orderDate}" pattern="hh:mm a"
												var="formattedTime" />${formattedTime}
										</p>
									</div>
								</td>

								<c:set var="itemsCount" value="${0}" />
								<c:forEach var="product" items="${order.cartProducts}">
									<c:set var="itemsCount" value="${itemsCount + product.cartQty}" />
								</c:forEach>

								<td>${itemsCount}</td>
								<td>Rs ${order.totalPrice}</td>
								<td>
									<div style="display: flex; gap: 32px">
										<div
											class="tab-item-count ${fn:toLowerCase(order.orderStatus)}">${order.orderStatus}</div>
									</div>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>

		</main>
	</div>
</body>
</html>
