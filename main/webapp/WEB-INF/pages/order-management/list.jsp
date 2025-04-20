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
	href="${pageContext.request.contextPath}/css/admin-orders-management-list.css">
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
					<a href="#">Dashboard</a>
					<svg width="4" height="4" viewBox="0 0 4 4" fill="none"
						xmlns="http://www.w3.org/2000/svg">
						<circle cx="2" cy="2" r="2" fill="#919EAB" />
					</svg>
					<a href="#">Orders List</a>
				</div>
			</div>

			<div class="list-table-container">
				<!-- Tabs -->
				<div class="table-tabs">
					<button class="table-tab-item">
						<span>All</span>
						<div class="tab-item-count">132</div>
					</button>
					<button class="table-tab-item selected">
						<span>Pending</span>
						<div class="tab-item-count pending">32</div>
					</button>
					<button class="table-tab-item">
						<span>Completed</span>
						<div class="tab-item-count completed">23</div>
					</button>
				</div>

				<!-- Filters -->
				<div class="table-filters">
					<div style="min-width: 180px">
						<p class="input-label">Start date</p>
						<input type="date" class="input-text" placeholder="Start date" />
					</div>
					<div style="min-width: 180px">
						<p class="input-label">End date</p>
						<input type="date" class="input-text" placeholder="End date" />
					</div>
					<div style="width: 100%">
						<p class="input-label">Search customer</p>
						<input type="text" class="input-text" />
					</div>
				</div>

				<!-- Results Count -->
				<div class="results-count">
					<p class="body2">
						<span class="subtitle2" style="color: #1c252e">8</span> results
						found
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
						<tr>
							<td>#6078</td>
							<td>
								<div class="customer-info">
									<img
										src="https://img.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg"
										width="40" height="40" />
									<div class="customer-details">
										<p class="body2 name">Jayvion Simon</p>
										<p class="body2 phone">9876543210</p>
									</div>
								</div>
							</td>
							<td>
								<div>
									<p class="body2" style="margin-bottom: 4px">12 Jan 2022</p>
									<p class="caption">10:00 PM</p>
								</div>
							</td>
							<td>52</td>
							<td>Rs 10,000</td>
							<td>
								<div style="display: flex; gap: 32px">
									<div class="tab-item-count completed">Completed</div>
								</div>
							</td>
						</tr>
						<tr>
							<td>#6019</td>
							<td>
								<div class="customer-info">
									<img
										src="https://img.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg"
										width="40" height="40" />
									<div class="customer-details">
										<p class="body2 name">Lucian Obrien</p>
										<p class="body2 phone">9876543210</p>
									</div>
								</div>
							</td>
							<td>
								<div>
									<p class="body2" style="margin-bottom: 4px">11 Feb 2022</p>
									<p class="caption">09:00 PM</p>
								</div>
							</td>
							<td>43</td>
							<td>Rs 15,000</td>
							<td>
								<div style="display: flex; gap: 32px">
									<div class="tab-item-count pending">Pending</div>
								</div>
							</td>
						</tr>
						<tr>
							<td>#6009</td>
							<td>
								<div class="customer-info">
									<img
										src="https://img.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg"
										width="40" height="40" />
									<div class="customer-details">
										<p class="body2 name">Reece Chung</p>
										<p class="body2 phone">9876543210</p>
									</div>
								</div>
							</td>
							<td>
								<div>
									<p class="body2" style="margin-bottom: 4px">08 Apr 2022</p>
									<p class="caption">06:00 PM</p>
								</div>
							</td>
							<td>22</td>
							<td>Rs 8,000</td>
							<td>
								<div style="display: flex; gap: 32px">
									<div class="tab-item-count completed">Completed</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>

		</main>
	</div>
</body>
</html>
