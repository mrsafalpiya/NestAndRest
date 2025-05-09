<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Cart - Nest and Rest</title>

<jsp:include page="../head.jsp" />

<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/checkout-cart.css" />
</head>
<body>
	<jsp:include page="../header.jsp" />

	<main class="cart-page container">
		<h4>Checkout</h4>

		<%
		String currentStep = "cart";
		%>

		<div class="checkout-progress">
			<div class="step <%=currentStep.equals("cart") ? "active" : ""%>">
				<%
				if (currentStep.equals("checkout-address") || currentStep.equals("complete")) {
				%>
				<div class="tick-mark">✓</div>
				<%
				} else {
				%>
				<div class="circle"></div>
				<%
				}
				%>

				<span>Cart</span>
			</div>
			<div
				class="line <%=currentStep.equals("billing & address") || currentStep.equals("complete") ? "active-line" : ""%>"></div>
			<div
				class="step <%=currentStep.equals("billing & address") || currentStep.equals("complete") ? "active" : ""%>">
				<div class="circle"></div>
				<span>Billing & address</span>
			</div>
			<div
				class="line <%=currentStep.equals("complete") ? "active-line" : ""%>"></div>
			<div class="step <%=currentStep.equals("complete") ? "active" : ""%>">
				<div class="circle"></div>
				<span>Complete</span>
			</div>
		</div>


		<div class="checkout-container">
			<!-- Cart Box -->
			<div class="cart-box">
				<h5>Cart (${products.size()})</h5>
				<table>
					<thead>
						<tr>
							<th>Product</th>
							<th>Price</th>
							<th>Quantity</th>
							<th>Total price</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="product" items="${products}">
							<tr>
								<td>
									<div class="product-info">
										<img
											src="${pageContext.request.contextPath}/resources/product-images/${product.getProductId()}.png"
											alt="${product.getName()}" />
										<div>
											<strong>${product.getName()}</strong><br />
											<c:if test="${not empty product.getVariants()}">
												<c:forEach var="variant" items="${product.getVariants()}">
													<span style="font-size: 10px">${variant.key}: ${variant.value}</span><br />
												</c:forEach>
											</c:if>
											<c:if test="${empty product.getVariants()}">
												No variants available
											</c:if>
										</div>
									</div>
								</td>
								<td>Rs ${product.getSalePrice()}</td>
								<td>
									<div class="qty-control-column">
										<span>${product.getCartQty()}</span>
									</div>
								</td>
								<td>Rs ${product.getSalePrice() * product.getCartQty()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<!-- Right Section: Order Summary -->
			<div class="order-summary">
				<h4>Order summary</h4>
				<p>
					<c:set var="subtotal" value="0" />
					<c:forEach var="product" items="${products}">
						<c:set var="subtotal" value="${subtotal + (product.getSalePrice() * product.getCartQty())}" />
					</c:forEach>
					<span>Subtotal:</span> <strong>Rs ${subtotal}</strong>
				</p>
				<p>
					<span>Payment Method:</span> <strong>Cash on Delivery</strong>
				</p>
				<div class="checkout-wrapper">
					<button class="checkout-btn"
						onclick="location.href='${pageContext.request.contextPath}/checkout-address'">
						Check out</button>
				</div>
			</div>
		</div>

		<!-- Bottom Actions -->
		<div class="address-actions">
			<a href="${pageContext.request.contextPath}/products" class="back-btn">←
				Continue Shopping</a>
		</div>
	</main>
</body>
</html>

