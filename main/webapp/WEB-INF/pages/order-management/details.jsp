<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<!-- Header -->
			<div class="details-header">
				<button style="background: transparent;">
					<svg width="36" height="36" viewBox="0 0 36 36" fill="none"
						xmlns="http://www.w3.org/2000/svg">
						<path
							d="M19.5247 23.8334C19.2726 23.8342 19.0336 23.7209 18.8747 23.525L14.8497 18.525C14.597 18.2175 14.597 17.7742 14.8497 17.4667L19.0164 12.4667C19.3109 12.1123 19.837 12.0638 20.1914 12.3584C20.5458 12.6529 20.5943 13.179 20.2997 13.5334L16.5747 18L20.1747 22.4667C20.3828 22.7165 20.4266 23.0645 20.287 23.358C20.1474 23.6516 19.8498 23.8372 19.5247 23.8334Z"
							fill="#637381" />
				</svg>
				</button>
				<div class="order-title-container">
					<div class="order-title-top-line">
						<h4>Order #6079</h4>
						<span class="status completed">Completed</span>
					</div>
					<p class="body2">12 Aug 2022 10:00 PM</p>
				</div>
				<select class="order-status">
					<option>Completed</option>
					<option>Pending</option>
				</select>
			</div>

			<!-- Details and Customer Info -->
			<div class="details-customer-info-container">

				<!-- Details Container -->
				<div class="details-container">
					<div class="header">
						<h6>Details</h6>
					</div>
					<div class="items-list-container">
						<div class="item-entry">
							<div class="item-image-title">
								<img
									src="https://cdn.prod.website-files.com/5f2b10811da7064399ed3a1c/67640b5b33d1a8708c0843b2_1.webp"
									width="48" height="48" />
								<p class="body2" style="color: var(--text-primary)">Two
									Sitter Sofa</p>
							</div>
							<p class="subtitle2" style="color: var(--text-primary)">Rs
								10,000</p>
						</div>
						<div class="divider"></div>
						<div class="item-entry">
							<div class="item-image-title">
								<img
									src="https://cdn.prod.website-files.com/5f2b10811da7064399ed3a1c/67640b5b33d1a8708c0843b2_1.webp"
									width="48" height="48" />
								<p class="body2" style="color: var(--text-primary)">Two
									Sitter Sofa</p>
							</div>
							<p class="subtitle2" style="color: var(--text-primary)">Rs
								10,000</p>
						</div>
						<div class="divider"></div>
						<div class="item-entry">
							<div class="item-image-title">
								<img
									src="https://cdn.prod.website-files.com/5f2b10811da7064399ed3a1c/67640b5b33d1a8708c0843b2_1.webp"
									width="48" height="48" />
								<p class="body2" style="color: var(--text-primary)">Two
									Sitter Sofa</p>
							</div>
							<p class="subtitle2" style="color: var(--text-primary)">Rs
								10,000</p>
						</div>
						<div class="divider"></div>
					</div>
					<div class="order-details-total-container">
						<div class="order-details-text-container">
							<p class="body2">Subtotal</p>
							<p class="body2">Discount</p>
							<p class="subtitle1">Total</p>
						</div>
						<div class="order-details-text-container prices">
							<p class="subtitle2" style="color: var(--text-primary)">Rs
								45,000</p>
							<p class="body2" style="color: #FF5630">- Rs 10,000</p>
							<p class="subtitle1">Rs 35,000</p>
						</div>
					</div>
				</div>

				<!-- Customer Info -->
				<div class="customer-info-div">
					<div class="header">
						<h6 style="width: 100%;">Customer Info</h6>
						<button style="background: transparent">
							<svg width="36" height="36" viewBox="0 0 36 36" fill="none"
								xmlns="http://www.w3.org/2000/svg">
								<path
									d="M17.5002 23.126L23.6635 16.9626C22.6266 16.5297 21.6848 15.8971 20.8918 15.101C20.0953 14.3078 19.4625 13.3658 19.0293 12.3285L12.866 18.4918C12.3852 18.9726 12.1443 19.2135 11.9377 19.4785C11.6936 19.7912 11.4843 20.1296 11.3135 20.4876C11.1693 20.791 11.0618 21.1143 10.8468 21.7593L9.71185 25.1618C9.65961 25.3176 9.65186 25.4849 9.68947 25.6448C9.72707 25.8048 9.80855 25.951 9.92474 26.0672C10.0409 26.1834 10.1872 26.2649 10.3472 26.3025C10.5071 26.3401 10.6744 26.3324 10.8302 26.2801L14.2327 25.1451C14.8785 24.9301 15.201 24.8226 15.5043 24.6785C15.8627 24.5076 16.201 24.2985 16.5135 24.0543C16.7785 23.8476 17.0193 23.6068 17.5002 23.126ZM25.3735 15.2526C25.988 14.6381 26.3333 13.8046 26.3333 12.9355C26.3333 12.0665 25.988 11.233 25.3735 10.6185C24.759 10.0039 23.9255 9.65869 23.0564 9.65869C22.1874 9.65869 21.3539 10.0039 20.7393 10.6185L20.0002 11.3576L20.0318 11.4501C20.3961 12.4924 20.9922 13.4384 21.7752 14.2168C22.5769 15.0232 23.556 15.631 24.6343 15.9918L25.3735 15.2526Z"
									fill="#637381" />
							</svg>
						</button>
					</div>
					<div class="info-container">
						<div class="user-info-container">
							<img
								src="https://img.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg"
								width="48" height="48" style="border-radius: 50%;" />
							<div>
								<p class="subtitle2" style="color: var(--primary-text)">Jayvion
									Simon</p>
								<p class="body2">nannie.abernathy70@yahoo.com</p>
								<p class="body2">9876543210</p>
							</div>
						</div>
						<div class="divider"></div>
						<div class="shipping-header">
							<h6>Shipping</h6>
						</div>
						<div class="shipping-info-container">
							<p class="body2">Address</p>
							<div>
								<p>19034 Verna Unions Apt.</p>
								<p>164 - Honolulu, RI / 87535</p>
							</div>
							<p class="body2">Phone number</p>
							<p>9876543210</p>
						</div>
					</div>
				</div>
			</div>
		</main>
	</div>
</body>
</html>
