<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit User Profile</title>

<jsp:include page="../head.jsp" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,100..900;1,100..900&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/edit-user-profile.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/components.css" />
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
					<div class="submenu-item">List</div>
					<div class="submenu-item active">Edit</div>
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
		<div class="overlay" id="overlay" onclick="hideSidebar()"></div>
		



		<div class="main-wrapper">
			<div class="main">
				<h4>Edit</h4>
				<div class="breadcrumb">Dashboard &nbsp; • &nbsp; User &nbsp;
					• &nbsp; List</div>

				<div class="cards-container">
					<!-- Photo Card -->
					<div class="photo-card">
						<div class="profile-photo">
							<label for="photo-upload" class="upload-overlay"> <img
								id="profileImage"
								src="${pageContext.request.contextPath}/resources/system/images/user-avatar.png"
								alt="Profile Photo" />
								<div class="hover-layer">
									<img
										src="${pageContext.request.contextPath}/resources/system/images/profileicon.png"
										alt="Upload Icon" />
								</div>
							</label> <input type="file" id="photo-upload" accept="image/*"
								style="display: none;">
						</div>
						<p>
							Allowed *.jpeg, *.jpg, *.png, *.gif<br>Max size of 3.1 MB
						</p>
						<button class="delete-button">Delete User</button>
					</div>
					<!-- Details Card -->
					<div class="details-card">
						<form class="details-form">
							<div class="form-group">
								<label>Name</label> <input type="text" value="Foo Bar">
							</div>
							<div class="form-group">
								<label>Email</label> <input type="email" value="foo@bar.com">
							</div>
							<div class="form-group">
								<label>Phone number</label> <input type="text"
									value="9876543210">
							</div>
							<div class="form-group">
								<label>Address</label> <input type="text" value="Kupondole">
							</div>
							<div class="form-group">
								<label>Country</label> <select>
									<option>Nepal</option>
									<option>Nepal</option>
									<option>Nepal</option>
								</select>
							</div>
							<div class="form-group">
								<label>State/Region</label> <input type="text" value="Bagmati">
							</div>
							<div class="form-group">
								<label>City</label> <input type="text" value="Kathmandu">
							</div>
							<div class="form-group">
								<label>Zip/Code</label> <input type="text" value="44600">
							</div>
							<div class="form-group full-width">
								<label>About</label>
								<textarea>Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae...</textarea>
							</div>
						</form>
						<div class="update-button-wrapper">
							<button class="update-button">Update Profile</button>
						</div>
					</div>
				</div>
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

	<script>
  		function toggleSubmenu(el) {
    	const submenu = el.nextElementSibling;
    	submenu.style.display = submenu.style.display === "none" ? "block" : "none";
    	const arrow = el.querySelector('.arrow');
    	arrow.style.transform = submenu.style.display === "none" ? "rotate(-90deg)" : "rotate(0deg)";
 	 }

  		// Optional: default collapse
  		document.querySelectorAll(".submenu").forEach(sm => sm.style.display = "block");
	</script>

	<script>
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
