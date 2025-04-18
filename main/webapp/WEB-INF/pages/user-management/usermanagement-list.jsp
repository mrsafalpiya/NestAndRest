<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User List</title>

<jsp:include page="../head.jsp" />

<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/usermanagement-list.css" />
</head>
<body>
	<div class="admin-header">
		<div class="header-right">
			<img
				src="${pageContext.request.contextPath}/resources/system/images/admin-logo.png"
				alt="Admin Profile" class="profile-icon">
		</div>
	</div>

	<div class="container">
		<div class="sidebar" id="sidebar">
			<!-- Sidebar Placeholder -->
			<div class="sidebar">
				<div class="logo">
					<img
						src="${pageContext.request.contextPath}/resources/system/images/logo.png"
						alt="Logo" />
				</div>

				<div class="section-title">OVERVIEW</div>
				<div class="menu-item">
					<img
						src="${pageContext.request.contextPath}/resources/system/images/dashboard.png"
						alt="Dashboard Icon" /> <span>Dashboard</span>
				</div>

				<div class="section-title--second">MANAGEMENT</div>

				<div class="menu-item collapsible" onclick="toggleSubmenu(this)">
					<img
						src="${pageContext.request.contextPath}/resources/system/images/icon.png"
						alt="User Icon" /> <span>User</span> <span class="arrow">&#9662;</span>
				</div>

				<div class="submenu">
					<div class="submenu-branch"></div>
					<div class="submenu-item active">List</div>
					<div class="submenu-item">Edit</div>
				</div>

				<div class="menu-item">
					<img
						src="${pageContext.request.contextPath}/resources/system/images/product-icon.png"
						alt="Product Icon" /> <span>Product</span> <span class="arrow">&#x203A;</span>
				</div>

				<div class="menu-item">
					<img
						src="${pageContext.request.contextPath}/resources/system/images/order-icon.png"
						alt="Order Icon" /> <span>Order</span> <span class="arrow">&#x203A;</span>
				</div>
			</div>
		</div>
		<div class="toggle-sidebar" onclick="toggleSidebar()" id="toggleArrow">&#10094;</div>

		<!-- Main Content -->
		<div class="main">
			<div class="header">List</div>
			<div class="breadcrumb">Dashboard &nbsp; • &nbsp; User &nbsp; •
				&nbsp; List</div>

			<!-- Search bar -->
			<div class="search-bar">
				<input type="text" placeholder="Search..." />
				<div class="search-tags">
					<span>Search: <strong>Keyword</strong></span>
					<button class="clear-btn">🗑 Clear</button>
				</div>
			</div>


			<!-- User table -->
			<div class="table-responsive">
				<table class="user-table">
					<thead>
						<tr>
							<th><input type="checkbox" /></th>
							<th>Name</th>
							<th>Phone number</th>
							<th>Email</th>
							<th>Role</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="checkbox" /></td>
							<td><img
								src="${pageContext.request.contextPath}/resources/system/images/user-images/user4.png" />
								Jayvion Simon</td>
							<td>365-374-4961</td>
							<td>foo@bar.com</td>
							<td>User</td>
							<td><span class="edit-icon">✏️</span> <span
								class="more-options">&#8942;</span> <!-- Vertical ellipsis --></td>

						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td><img
								src="${pageContext.request.contextPath}/resources/system/images/user-images/user1.png" />
								Lucian Obrien</td>
							<td>904-966-2836</td>
							<td>bar@buzz.com</td>
							<td>User</td>
							<td><span class="edit-icon">✏️</span> <span
								class="more-options">&#8942;</span> <!-- Vertical ellipsis --></td>

						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td><img
								src="${pageContext.request.contextPath}/resources/system/images/user-images/user2.png" />
								Deja Brady</td>
							<td>399-757-9909</td>
							<td>test@admin.com</td>
							<td>Admin</td>
							<td><span class="edit-icon">✏️</span> <span
								class="more-options">&#8942;</span> <!-- Vertical ellipsis --></td>

						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td><img
								src="${pageContext.request.contextPath}/resources/system/images/user-images/user3.png" />Harrison
								Stein</td>
							<td>692-767-2903</td>
							<td>buzz@boo.com</td>
							<td>User</td>
							<td><span class="edit-icon">✏️</span> <span
								class="more-options">&#8942;</span> <!-- Vertical ellipsis --></td>

						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td><img
								src="${pageContext.request.contextPath}/resources/system/images/user-images/user5.png" />
								Reece Chung</td>
							<td>990-588-5716</td>
							<td>test@email.com</td>
							<td>Admin</td>
							<td><span class="edit-icon">✏️</span> <span
								class="more-options">&#8942;</span> <!-- Vertical ellipsis --></td>

						</tr>
					</tbody>
				</table>
			</div>

			<!-- Pagination -->
			<div class="pagination">
				<span class="pagination-info">6-10 of 11</span> <span
					class="pagination-nav">
					<button class="page-btn">&#x276E;</button>
					<button class="page-btn">&#x276F;</button>
				</span>
			</div>
		</div>
	</div>
	<script>
		function toggleSidebar() {
			const sidebar = document.querySelector('.sidebar');
			const toggleArrow = document.getElementById("toggleArrow");

			// Toggle the collapsed class on sidebar to hide or show instantly
			sidebar.classList.toggle('collapsed');
		}
	</script>

</body>
</html>