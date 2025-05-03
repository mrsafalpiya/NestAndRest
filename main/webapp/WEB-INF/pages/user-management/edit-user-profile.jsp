<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit User Profile</title>

<jsp:include page="../head.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/edit-user-profile.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components.css" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet" />
</head>
<body>
	<div class="admin-header">
		<div class="header-right">
			<img src="${pageContext.request.contextPath}/resources/system/images/admin-logo.png" alt="Admin Profile" class="profile-icon">
		</div>
	</div>

	<div class="container">
		<div class="sidebar" id="sidebar">
			<div class="logo">
				<img src="${pageContext.request.contextPath}/resources/system/images/logo.png" alt="Logo" />
			</div>

			<div class="section-title">OVERVIEW</div>
			<div class="menu-item">
				<img src="${pageContext.request.contextPath}/resources/system/images/dashboard.png" alt="Dashboard Icon" /> 
				<span>Dashboard</span>
			</div>

			<div class="section-title--second">MANAGEMENT</div>

			<div class="menu-item collapsible" onclick="toggleSubmenu(this)">
				<img src="${pageContext.request.contextPath}/resources/system/images/icon.png" alt="User Icon" /> 
				<span>User</span> <span class="arrow">&#9662;</span>
			</div>

			<div class="submenu">
				<div class="submenu-branch"></div>
				<a href="${pageContext.request.contextPath}/usermanagement-list" class="submenu-item">List</a>
			</div>

			<div class="menu-item">
				<img src="${pageContext.request.contextPath}/resources/system/images/product-icon.png" alt="Product Icon" /> 
				<span>Product</span>
			</div>

			<div class="menu-item">
				<img src="${pageContext.request.contextPath}/resources/system/images/order-icon.png" alt="Order Icon" /> 
				<span>Order</span>
			</div>
		</div>

		<div class="toggle-sidebar" onclick="toggleSidebar()" id="toggleArrow">&#10094;</div>
		<div class="overlay" id="overlay" onclick="hideSidebar()"></div>

		<div class="main-wrapper">
			<div class="main">
				<h4>Edit</h4>
				<div class="breadcrumb">Dashboard &nbsp; • &nbsp; User &nbsp; • &nbsp; Edit</div>

				<div class="cards-container">
					<!-- Photo Card -->
					<div class="photo-card">
						<div class="profile-photo">
							<label for="photo-upload" class="upload-overlay"> 
								<img id="profileImage" src="${pageContext.request.contextPath}/resources/system/images/user-avatar.png" alt="Profile Photo" />
								<div class="hover-layer">
									<img src="${pageContext.request.contextPath}/resources/system/images/profileicon.png" alt="Upload Icon" />
								</div>
							</label> 
							<input type="file" id="photo-upload" accept="image/*" style="display: none;">
						</div>
						<p>Allowed *.jpeg, *.jpg, *.png, *.gif<br>Max size of 3.1 MB</p>

						<!-- Delete User Form -->
						<form action="${pageContext.request.contextPath}/edit-user-profile" method="POST" onsubmit="return confirmDelete()">
							<input type="hidden" name="userId" value="${user.userId}">
							<input type="hidden" name="action" value="delete">
							<button type="submit" class="delete-button">Delete User</button>
						</form>
					</div>

					<!-- Details Card -->
					<div class="details-card">
						<form class="details-form" method="post" action="${pageContext.request.contextPath}/edit-user-profile" onsubmit="return confirmUpdate()">
							<input type="hidden" name="userId" value="${user.userId}" />

							<div class="form-group">
								<label for="name">Name</label> 
								<input type="text" name="name" id="name" value="${user.name}" required>
							</div>

							<div class="form-group">
								<label for="email">Email</label> 
								<input type="email" name="email" id="email" value="${user.email}" required>
							</div>

							<div class="form-group">
								<label for="phone">Phone number</label> 
								<input type="text" name="phone" id="phone" value="${user.phone}">
							</div>

							<div class="form-group">
								<label for="address">Address:</label> 
								<input type="text" name="address" id="address" value="${address != null ? address.address : ''}" />
							</div>

							<div class="form-group">
								<label for="genderId">Gender</label> 
								<select name="genderId" id="genderId">
									<option value="1" ${user.genderId == 1 ? 'selected' : ''}>Male</option>
									<option value="2" ${user.genderId == 2 ? 'selected' : ''}>Female</option>
									<option value="3" ${user.genderId == 3 ? 'selected' : ''}>Others</option>
								</select>
							</div>

							<div class="form-group">
								<label for="roleId">Role</label> 
								<select name="roleId" id="roleId">
									<option value="1" ${user.roleId == 1 ? 'selected' : ''}>Admin</option>
									<option value="2" ${user.roleId == 2 ? 'selected' : ''}>Customer</option>
								</select>
							</div>

							<div class="update-button-wrapper">
								<button type="submit" class="update-button">Update Profile</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		function toggleSidebar() {
			const sidebar = document.querySelector('.sidebar');
			const toggleArrow = document.getElementById("toggleArrow");
			sidebar.classList.toggle('collapsed');
		}

		function confirmUpdate() {
			return confirm("Are you sure you want to update your profile?");
		}

		function confirmDelete() {
			return confirm("Are you sure you want to delete this user?");
		}

		const photoInput = document.getElementById('photo-upload');
		const profileImage = document.getElementById('profileImage');
		photoInput.addEventListener('change', function() {
			const file = this.files[0];
			if (file && file.type.startsWith('image/')) {
				const reader = new FileReader();
				reader.onload = function(e) {
					profileImage.src = e.target.result;
				};
				reader.readAsDataURL(file);
			} else {
				alert("Please select a valid image file.");
			}
		});
	</script>
</body>
</html>
