<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />

<jsp:include page="../head.jsp" />

<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>Checkout - Billing & Address</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/checkout-address.css">
</head>
<body>
	<jsp:include page="../header.jsp" />
	<main class="checkout-page container">
		<h4>Checkout</h4>

		<!-- Set currentStep without scriptlet -->
		<c:set var="currentStep" value="checkout-address" />

		<div class="checkout-progress">
			<!-- Cart step -->
			<div
				class="step <c:if test='${currentStep == "checkout-address" or currentStep == "complete"}'>completed</c:if>">
				<c:choose>
					<c:when
						test="${currentStep == 'checkout-address' or currentStep == 'complete'}">
						<div class="tick-mark">✔</div>
					</c:when>
					<c:otherwise>
						<div class="circle"></div>
					</c:otherwise>
				</c:choose>
				<span>Cart</span>
			</div>
			<div
				class="line <c:if test='${currentStep == "checkout-address" or currentStep == "complete"}'>active-line</c:if>"></div>

			<!-- Billing & Address step -->
			<div
				class="step <c:if test='${currentStep == "checkout-address" or currentStep == "complete"}'>active</c:if>">
				<div class="circle"></div>
				<span>Billing & Address</span>
			</div>
			<div
				class="line <c:if test='${currentStep == "complete"}'>active-line</c:if>"></div>

			<!-- Complete step -->
			<div
				class="step <c:if test='${currentStep == "complete"}'>active</c:if>">
				<div class="circle"></div>
				<span>Complete</span>
			</div>
		</div>


		<!-- Checkout Layout -->
		<div class="checkout-wrapper">
			<!-- Left Section: Addresses + Actions -->
			<div class="left-section">
				<c:if test="${success != null}">
					<p style="margin-bottom: 20px; color: green;">${success}</p>
				</c:if>
				<c:if test="${error != null}">
					<p style="margin-bottom: 20px; color: red;">${error}</p>
				</c:if>

				<!-- Address List -->
				<div class="address-list">
					<c:forEach var="address" items="${addresses}">
						<div class="address-card">
							<div>
								<h5>${address.getUserName()}</h5>
								<p>${address.getAddress()}</p>
								<p>${address.getPhoneNumber()}</p>
							</div>
							<button
								onclick="location.href='${pageContext.request.contextPath}/product-checkout-complete?address_id=${address.getUserAddressId()}'">Deliver
								to this address</button>
						</div>
					</c:forEach>
				</div>

				<!-- Bottom Actions -->
				<div class="address-actions">
					<a href="${pageContext.request.contextPath}/checkout-cart"
						class="back-btn">← Back</a> <a href="#" class="new-address">➕
						New address</a>
				</div>
			</div>

			<!-- Right Section: Order Summary -->
			<div class="order-summary">
				<h5>Order summary</h5>
				<p>
					<c:set var="subtotal" value="0" />
					<c:forEach var="product" items="${products}">
						<c:set var="subtotal"
							value="${subtotal + (product.getSalePrice() * product.getCartQty())}" />
					</c:forEach>
					<span>Subtotal:</span> <strong>Rs ${subtotal}</strong>
				</p>
				<p>
					<span>Payment Method:</span> <strong>Cash on Delivery</strong>
				</p>
			</div>
		</div>
	</main>

	<!-- Address Modal -->
	<div id="addressModal" class="modal-overlay" style="display: none;">
		<div class="modal">
			<h5>New address</h5>
			<form method="POST">
				<div class="input-row">
					<input type="text" placeholder="Full name" name="full-name" /> <input
						type="text" placeholder="Phone number" name="phone" />
				</div>
				<input type="text" placeholder="Address" name="address" /> <input
					type="text" placeholder="Town/city" name="city" />
				<div class="modal-actions">
					<button type="button" class="cancel-btn">Cancel</button>
					<button type="submit" class="submit-btn">Deliver to this
						address</button>
				</div>
			</form>
		</div>
	</div>

	<script>
	  const modal = document.getElementById("addressModal");
	  const newAddressBtn = document.querySelector(".new-address");
	  const cancelBtn = document.querySelector(".cancel-btn");

	  newAddressBtn.addEventListener("click", (e) => {
	    e.preventDefault();
	    modal.style.display = "flex";
	  });

	  cancelBtn.addEventListener("click", () => {
	    modal.style.display = "none";
	  });

	  window.addEventListener("click", (e) => {
	    if (e.target === modal) {
	      modal.style.display = "none";
	    }
	  });
	</script>
</body>
</html>



