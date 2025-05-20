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
<title>Admin - Queries List</title>
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
				<h4>Queries List</h4>
				<div class="links">
					<a href="${contextPath}/admin">Dashboard</a>
					<svg width="4" height="4" viewBox="0 0 4 4" fill="none"
						xmlns="http://www.w3.org/2000/svg">
						<circle cx="2" cy="2" r="2" fill="#919EAB" />
					</svg>
					<a href="#">Queries List</a>
				</div>
			</div>

			<div class="list-table-container">

				<!-- Results Count -->
				<div class="results-count">
					<p class="body2">
						<span class="subtitle2" style="color: #1c252e">${queries.size()}</span>
						queries found
					</p>
				</div>

				<!-- Results Table -->
				<div class="results-table-container">
					<table class="results-table">
						<tr>
							<th>Query</th>
							<th>User</th>
							<th>Created At</th>
							<th class="max">Message</th>
						</tr>
						<c:forEach var="query" items="${queries}">
							<tr>
								<td>#${query.queryId}</td>
								<td>
									<div class="customer-info">
										<div class="customer-details">
											<p class="body2 name">${query.userName}</p>
											<p class="body2 phone">${query.userEmail}</p>
										</div>
									</div>
								</td>
								<td>
									<div>
										<p class="body2" style="margin-bottom: 4px">
											<fmt:formatDate value="${query.createdAt}"
												pattern="dd MMM yyyy" var="formattedDate" />${formattedDate}
										</p>
										<p class="caption">
											<fmt:formatDate value="${query.createdAt}" pattern="hh:mm a"
												var="formattedTime" />${formattedTime}
										</p>
									</div>
								</td>

								<td>${query.message}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>

		</main>
	</div>
</body>
</html>
