<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.nestandrest.model.UserModel"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
				</div>

				<div class="menu-item">
					<img
						src="${pageContext.request.contextPath}/resources/system/images/product-icon.png"
						alt="Product Icon" /> <span>Product</span> 
				</div>

				<div class="menu-item">
					<img
						src="${pageContext.request.contextPath}/resources/system/images/order-icon.png"
						alt="Order Icon" /> <span>Order</span>
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
			<form method="get" action="usermanagement-list">
				<div class="search-bar">
					<input type="text" name="searchTerm" placeholder="Search..."
						value="${param.searchTerm}" />
					<button type="submit">Search</button>
					<c:if test="${not empty param.searchTerm}">
						<div class="search-tags">
							<span>Search: <strong>${param.searchTerm}</strong></span> <a
								href="usermanagement-list" class="clear-btn">🗑 Clear</a>
						</div>
					</c:if>
				</div>
			</form>

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
					<tbody>
						<%
						List<UserModel> userList = (List<UserModel>) request.getAttribute("userList");
						if (userList != null) {
							for (UserModel user : userList) {
						%>
						<tr>
							<td><input type="checkbox" /></td>
							<td><img
								src="${pageContext.request.contextPath}/resources/system/images/user-images/user1.png" />
								<%=user.getName()%></td>
							<td><%=user.getPhone()%></td>
							<td><%=user.getEmail()%></td>
							<td><%=user.getRoleId() == 1 ? "Admin" : "User"%></td>
							<td><a
								href="${pageContext.request.contextPath}/edit-user-profile?userId=<%=user.getUserId()%>">
									<span class="edit-icon">✏️</span>
							</a></td>

						</tr>
						<%
						}
						} else {
						%>
						<tr>
							<td colspan="6">No users found.</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>

			<!-- Pagination -->
			<div class="pagination">
				<c:forEach begin="1" end="${totalPages}" var="i">
					<c:choose>
						<c:when test="${i == currentPage}">
							<span class="current">${i}</span>
						</c:when>
						<c:otherwise>
							<a
								href="usermanagement-list?page=${i}&searchTerm=${param.searchTerm}">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
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
		function toggleSubmenu(el) {
            const submenu = el.nextElementSibling;
            submenu.style.display = submenu.style.display === "none" ? "block" : "none";
            const arrow = el.querySelector('.arrow');
            arrow.style.transform = submenu.style.display === "none" ? "rotate(-90deg)" : "rotate(0deg)";
        }
	</script>

</body>
</html>