<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="../head.jsp" />
<link rel="stylesheet"
	href="${contextPath}/css/admin-style.css">
<link rel="stylesheet"
	href="${contextPath}/css/admin-add-product.css">
<title>Create New Product</title>
</head>
<body>
	<div class="admin-layout">
		<jsp:include page="../admin-header.jsp" />
		<jsp:include page="../admin-sidebar.jsp" />

		<main class="admin-product-container">
			<!-- Breadcrumb -->
			<div class="breadcrumb">
				<h4>Create a new product</h4>
				<div class="path">
					<span>Dashboard</span> • <span>Product</span> • <span
						class="current">New Product</span>
				</div>
			</div>

			<!-- Error Message -->
			<c:if test="${not empty error}">
				<p style="color: red;">${error}</p>
			</c:if>

			<!-- Form Container -->
			<form action="${contextPath}/admin/products/add"
				method="post" enctype="multipart/form-data" class="product-form">
				<!-- Product Details -->
				<div class="form-section">
					<h5>Details</h5>
					<p>Title, short description, image...</p>
					<input type="text" name="productName" placeholder="Product Name"
						required />
					<textarea name="subDescription" placeholder="Short Description"></textarea>
					<label for="fullDescription"><h5>Full Description</h5></label>
					<textarea name="fullDescription"
						placeholder="Write something awesome about the product..."></textarea>
				</div>

				<!-- Image Upload -->
				<div class="form-section">
					<h5>Images</h5>
					<div class="image-upload">
						<label for="images"> <img
							src="${contextPath}/resources/system/images/ProductPageLogo/AddImage.png"
							alt="Upload" />
							<p>
								Select files<br>
								<span>Click to browse through your machine.</span>
							</p>
						</label> <input type="file" name="images" id="images" accept="image/*" />
					</div>
					<div class="image-actions">
						<button type="button" class="remove-btn">Remove</button>
						<button type="submit" class="upload-btn">
							<img
								src="${contextPath}/resources/system/images/ProductPageLogo/upload-icon.png"
								alt="Upload Icon"> Upload
						</button>
					</div>
				</div>

				<!-- Product Properties -->
				<div class="form-section">
					<h5>Properties</h5>
					<div class="properties-grid">
						<input type="number" name="quantity" placeholder="Stock Quantity"
							required />
						<!-- Category -->
						<div class="form-group">
							<select id="category" name="category">
								<option value="">Category</option>
								<option value="1">Sofas</option>
								<option value="2">Chairs</option>
								<option value="3">Tables</option>
							</select>
						</div>

						<!-- Colors -->
						<div class="form-group">
							<select id="colors" name="colors">
								<option value="">Select Color</option>
								<option value="Natural">Natural</option>
								<option value="Orange">Orange</option>
								<option value="White">White</option>
							</select>
						</div>

						<!-- Sizes -->
						<div class="form-group">
							<select id="sizes" name="sizes">
								<option value="">Select Size</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
							</select>
						</div>

						<!-- Price -->
						<div class="form-group">
							<input type="number" step="0.01" name="price"
								placeholder="Enter Price" required />
						</div>

						<!-- Discounted Price with toggle -->
						<div class="form-group discounted-price-group">
							<div class="discount-toggle">
								<input type="checkbox" id="enableDiscount"
									onchange="toggleDiscountInput()" /> <input type="number"
									step="0.01" id="discountedPrice" name="discountedPrice"
									placeholder="Enter discounted price" disabled />
							</div>
						</div>
					</div>
				</div>

				<label> <input type="checkbox" name="publish" /> Publish
				</label>
				<button type="submit" class="btn-create">Create Product</button>
			</form>
		</main>
	</div>
	<script>
        function toggleDiscountInput() {
            const toggle = document.getElementById('enableDiscount');
            const input = document.getElementById('discountedPrice');
            input.disabled = !toggle.checked;
            if (!toggle.checked) {
                input.value = '';
            }
        }
    </script>
</body>
</html>