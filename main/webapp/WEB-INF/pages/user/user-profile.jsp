<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Profile</title>

<jsp:include page="../head.jsp" />

<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,100..900;1,100..900&display=swap"
	rel="stylesheet" />

<link rel="stylesheet"
	href="${contextPath}/css/user-profile.css" />
<link rel="stylesheet"
	href="${contextPath}/css/components.css" />

</head>
<body>

	<jsp:include page="../header.jsp" />

	<div class="main-wrapper">

		<div class="header">
			<h2>Edit User Profile</h2>
		</div>

		<div class="cards-container">


			<!-- Photo Card -->
			<div class="photo-card">
				<div class="profile-photo">
					<label for="photo-upload" class="upload-overlay"> <img
						src="${contextPath}/resources/user-images/${user.getUserId()}.png"
						onerror="this.src='${contextPath}/resources/system/images/Avatar.png'"
						alt="Profile Photo" />

						<div class="hover-layer"></div>
					</label>
					<form method="POST" enctype="multipart/form-data">
						<input type="file" id="photo-upload" name="image" accept="image/*"
							style="display: none;" onchange="form.submit()"> <input
							type="hidden" name="update" value="picture" />
					</form>
				</div>
				<p>
					Allowed images<br>Max size of 10 MB
				</p>
			</div>

			<!-- Details Card -->
			<div class="details-card">
				<c:if test="${error != null}">
					<p class="error-message" style="margin-bottom: 20px;">${error}</p>
				</c:if>
				<c:if test="${success != null}">
					<p class="success-message" style="margin-bottom: 20px;">${success}</p>
				</c:if>

				<form class="details-form" method="POST">
					<div class="form-group">
						<label>First Name</label> <input type="text" name="first_name"
							value="${user.getFirstName()}">
					</div>

					<div class="form-group">
						<label>Last Name</label> <input type="text" name="last_name"
							value="${user.getLastName()}">
					</div>

					<div class="form-group" style="grid-column: 1/-1;">
						<label>Email</label> <input type="email" name="email"
							value="${user.getEmail()}">
					</div>

					<div class="form-group" style="grid-column: 1/-1;">
						<label>Phone number</label> <input type="text" name="phone"
							value="${user.getPhone()}">
					</div>

					<div class="form-group" style="grid-column: 1/-1;">
						<label>Gender</label> <select name="gender" class="input-text"
							required>
							<option value="" disabled>Select your gender</option>
							<c:forEach var="gender" items="${genders}">
								<option value="${gender.genderId}"
									${user.getGenderId() == gender.genderId ? 'selected' : ''}>${gender.name}</option>
							</c:forEach>
						</select>
					</div>

					<input type="hidden" name="update" value="profile" />

					<div class="update-button-wrapper" style="grid-column: 1/-1;">
						<button class="update-button">Update Profile</button>
					</div>
				</form>

				<!-- Update Password -->
				<h4 style="margin-top: 10px;">Update Password</h4>
				<form class="details-form" style="margin-top: 20px;" method="POST">
					<div class="form-group" style="grid-column: 1/-1;">
						<label>New Password</label> <input type="password" name="password">
					</div>

					<div class="form-group" style="grid-column: 1/-1;">
						<label>Confirm New Password</label> <input type="password"
							name="password-confirm">
					</div>

					<input type="hidden" name="update" value="password" />

					<div class="update-button-wrapper" style="grid-column: 1/-1;">
						<button class="update-button">Update Password</button>
					</div>
				</form>
			</div>
		</div>

	</div>

	<jsp:include page="../footer.jsp" />
</body>
</html>