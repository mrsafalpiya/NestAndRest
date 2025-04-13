<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

		<%
		// Dynamically set the current step for the progress bar
		String currentStep = "checkout-address"; // Set dynamically based on the page (e.g., "cart", "checkout-address", "complete")
		%>

		<div class="checkout-progress">
			<!-- Cart step -->
			<div
				class="step <%=currentStep.equals("checkout-address") || currentStep.equals("complete") ? "completed" : ""%>">
				<%
				if (currentStep.equals("checkout-address") || currentStep.equals("complete")) {
				%>
				<div class="tick-mark">✔</div>
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
				class="line <%=currentStep.equals("checkout-address") || currentStep.equals("complete") ? "active-line" : ""%>"></div>

			<!-- Billing & Address step -->
			<div
				class="step <%=currentStep.equals("checkout-address") || currentStep.equals("complete") ? "active" : ""%>">
				<div class="circle"></div>
				<span>Billing & Address</span>
			</div>
			<div
				class="line <%=currentStep.equals("complete") ? "active-line" : ""%>"></div>

			<!-- Complete step -->
			<div class="step <%=currentStep.equals("complete") ? "active" : ""%>">
				<div class="circle"></div>
				<span>Complete</span>
			</div>
		</div>


		<!-- Checkout Layout -->
		<div class="checkout-wrapper">
			<!-- Left Section: Addresses + Actions -->
			<div class="left-section">
				<!-- Address List -->
				<div class="address-list">
					<div class="address-card">
						<div>
							<h5>Lucian Obrien</h5>
							<p>1147 Rohan Drive Suite 819 - Burlington, VT / 82021</p>
							<p>904-966-2836</p>
						</div>
						<button
							onclick="location.href='${pageContext.request.contextPath}/product-checkout-complete'">Deliver
							to this address</button>
					</div>
					<div class="address-card">
						<div>
							<h5>Deja Brady</h5>
							<p>18605 Thompson Circle Apt. 086 - Idaho Falls, WV / 50337</p>
							<p>399-757-9909</p>
						</div>
						<button
							onclick="location.href='${pageContext.request.contextPath}/product-checkout-complete'">Deliver
							to this address</button>
					</div>
					<div class="address-card">
						<div>
							<h5>Jayvion Simon</h5>
							<p>19034 Verna Unions Apt. 164 - Honolulu, RI / 87535</p>
							<p>365-374-4961</p>
						</div>
						<button
							onclick="location.href='${pageContext.request.contextPath}/product-checkout-complete'">Deliver
							to this address</button>
					</div>
					<div class="address-card">
						<div>
							<h5>Harrison Stein</h5>
							<p>110 Lamar Station Apt. 730 - Hagerstown, OK / 49808</p>
							<p>692-767-2903</p>
						</div>
						<button
							onclick="location.href='${pageContext.request.contextPath}/product-checkout-complete'">Deliver
							to this address</button>
					</div>
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
					<span>Subtotal:</span> <strong>Rs 48,000</strong>
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
			<form>
				<div class="input-row">
					<input type="text" placeholder="Full name" required /> <input
						type="text" placeholder="Phone number" required />
				</div>
				<input type="text" placeholder="Address" required /> <input
					type="text" placeholder="Town/city" required />
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



