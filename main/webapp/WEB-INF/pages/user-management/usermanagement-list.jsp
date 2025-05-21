<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.nestandrest.model.UserModel"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Admin - User List</title>
<jsp:include page="../head.jsp" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin-style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/usermanagement-list.css">
</head>
<body>
	<div class="admin-layout">
		<jsp:include page="../admin-header.jsp" />
		<jsp:include page="../admin-sidebar.jsp" />

		<div class="main">
			<div class="header">List</div>
			<div class="breadcrumb">Dashboard &nbsp; • &nbsp; User &nbsp; •
				&nbsp; List</div>

			<form method="get" action="usermanagement-list">
				<div class="search-bar">
					<input type="text" name="searchTerm"
						placeholder="Search by name, email..." value="${param.searchTerm}" />
					<button type="submit">Search</button>
				</div>

				<c:if test="${not empty param.searchTerm}">
					<div class="search-keyword">
						<span>Search: <strong>${param.searchTerm}</strong></span> <a
							href="usermanagement-list" class="clear-btn">🗑 Clear</a>
					</div>
				</c:if>
			</form>

			<div class="table-responsive">
				<table class="user-table">
					<thead>
						<tr>
							<th><input type="checkbox" /></th>
							<th>Name</th>
							<th>Phone</th>
							<th>Email</th>
							<th>Role</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty userList}">
								<c:forEach var="user" items="${userList}">
									<tr>
										<td><input type="checkbox" /></td>
										<td><img
											src="${pageContext.request.contextPath}/resources/user-images/${user.userId}.png"
											onerror="this.onerror=null;this.src='${pageContext.request.contextPath}/resources/system/images/user-avatar.png';"
											alt="Profile Image" width="150" /> ${user.name}</td>
										<td>${user.phone}</td>
										<td>${user.email}</td>
										<td><c:choose>
												<c:when test="${user.roleId == 2}">Admin</c:when>
												<c:otherwise>User</c:otherwise>
											</c:choose></td>
										<td><a
											href="${pageContext.request.contextPath}/edit-user-profile?userId=${user.userId}"
											title="Edit">✏️</a></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="6" class="no-data">No users found.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>

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
</body>
</html>
