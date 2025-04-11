<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Cart - Nest and Rest</title>

<jsp:include page="../head.jsp" />

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
				<div class="circle"></div>
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
				<h5>Cart (3)</h5>
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
						<tr>
							<td>
								<div class="product-info">
									<img
										src="${pageContext.request.contextPath}/resources/system/images/checkout-gecheer.png"
										alt="Gecheer Sofa" />
									<div>
										<strong>Gecheer Sofa with Cushion</strong><br /> size: 9
									</div>
								</div>
							</td>
							<td>Rs 75,000</td>
							<td>
								<div class="qty-control-column">
									<div class="qty-control">
										<button>-</button>
										<span>1</span>
										<button>+</button>
									</div>
									<small>Available: 2</small>
								</div>
							</td>
							<td>Rs 75,000</td>
						</tr>
						<tr>
							<td>
								<div class="product-info">
									<img
										src="${pageContext.request.contextPath}/resources/system/images/checkout-apple.png"
										alt="Apple Stick Sofa" />
									<div>
										<strong>Apple Stick Sofa Set</strong><br /> size: 5
									</div>
								</div>
							</td>
							<td>Rs 40,000</td>
							<td>
								<div class="qty-control-column">
									<div class="qty-control">
										<button>-</button>
										<span>2</span>
										<button>+</button>
									</div>
									<small>Available: 3</small>
								</div>
							</td>
							<td>Rs 80,000</td>
						</tr>
						<tr>
							<td>
								<div class="product-info">
									<img
										src="${pageContext.request.contextPath}/resources/system/images/checkout-cane.png"
										alt="Cane Rattan Couch Set" />
									<div>
										<strong>Cane/Rattan Couch Set</strong><br /> size: 7
									</div>
								</div>
							</td>
							<td>Rs 48,000</td>
							<td>
								<div class="qty-control-column">
									<div class="qty-control">
										<button>-</button>
										<span>3</span>
										<button>+</button>
									</div>
									<small>Available: 6</small>
								</div>
							</td>
							<td>Rs 1,44,000</td>
						</tr>
					</tbody>
				</table>
			</div>

			<!-- Right Section: Order Summary -->
			<div class="order-summary">
				<h4>Order summary</h4>
				<p>
					<span>Subtotal:</span> <strong>Rs 48,000</strong>
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
			<a href="${pageContext.request.contextPath}/shop" class="back-btn">←
				Continue Shopping</a>
		</div>
	</main>
</body>
</html>

