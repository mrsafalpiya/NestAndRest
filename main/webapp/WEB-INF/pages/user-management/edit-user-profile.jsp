<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.nestandrest.model.UserModel"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit User Profile</title>
<jsp:include page="../head.jsp" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/edit-user-profile.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/components.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin-style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/usermanagement-list.css">
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,100..900;1,100..900&display=swap"
	rel="stylesheet" />
</head>
<body>

	<div class="admin-layout">
		<%-- Sidebar and Header --%>
		<jsp:include page="../admin-header.jsp" />
		<jsp:include page="../admin-sidebar.jsp" />

		<div class="main">
			<h4>Edit</h4>

			<div class="breadcrumb">Dashboard &nbsp; • &nbsp; User &nbsp; •
				&nbsp; Edit</div>

			<div class="cards-container">
				<!-- Photo Card -->
				<div class="photo-card">
					<div class="profile-photo">
						<label for="photo-upload" class="upload-overlay"> <%-- Profile Image with fallback --%>
							<%-- Profile Image with fallback --%> <c:choose>
								<c:when test="${not empty user.profileImage}">
									<img
										src="${pageContext.request.contextPath}/resources/user-images/${user.profileImage}"
										alt="Profile Image" width="150"
										onerror="this.onerror=null;this.src='${pageContext.request.contextPath}/resources/system/images/user-avatar.png';" />
								</c:when>
								<c:otherwise>
									<img
										src="${pageContext.request.contextPath}/resources/system/images/user-avatar.png"
										alt="Default Profile Image" width="150" />
								</c:otherwise>
							</c:choose>

						</label>
					</div>

					<!-- Delete User Form -->
					<form action="${pageContext.request.contextPath}/edit-user-profile"
						method="POST" onsubmit="return confirmDelete()">
						<input type="hidden" name="userId" value="${user.userId}">
						<input type="hidden" name="action" value="delete">
						<button type="submit" class="delete-button">Delete User</button>
					</form>
				</div>

				<!-- Details Card -->
				<div class="details-card">
					<form class="details-form" method="post"
						action="${pageContext.request.contextPath}/edit-user-profile"
						onsubmit="return confirmUpdate()">
						<input type="hidden" name="userId" value="${user.userId}" />

						<div class="form-group">
							<label for="name">Name</label> <input type="text" name="name"
								id="name" value="${user.name}" required>
						</div>

						<div class="form-group">
							<label for="email">Email</label> <input type="email" name="email"
								id="email" value="${user.email}" required>
						</div>

						<div class="form-group">
							<label for="phone">Phone number</label> <input type="text"
								name="phone" id="phone" value="${user.phone}">
						</div>

						<div class="form-group">
							<label for="genderId">Gender:</label> <select id="genderId"
								name="genderId" required class="form-control">
								<option value="1" ${user.genderId == 1 ? 'selected' : ''}>Male</option>
								<option value="2" ${user.genderId == 2 ? 'selected' : ''}>Female</option>
								<option value="3" ${user.genderId == 3 ? 'selected' : ''}>Others</option>
							</select>
						</div>

						<div class="form-group">
							<label for="roleId">Role</label> <select name="roleId"
								id="roleId">
								<option value="1" ${user.roleId == 1? 'selected' : ''}>Customer</option>
								<option value="2" ${user.roleId == 2? 'selected' : ''}>Admin</option>
							</select>
						</div>

						<div class="update-button-wrapper">
							<button type="submit" class="update-button">Update
								Profile</button>
						</div>
						<!-- Show error message if exists -->
						<c:if test="${not empty error}">
							<p style="color: red;">${error}</p>
						</c:if>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
