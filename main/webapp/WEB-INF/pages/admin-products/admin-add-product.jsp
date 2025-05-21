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
					<input type="file" name="images" id="images" accept="image/*" multiple />
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
								<option disabled selected>Select a category</option>
								<c:forEach var="category" items="${categories}">
									<option value="${category.categoryId}">${category.name}</option>
								</c:forEach>
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

				<!-- Product Variants -->
				<div class="form-section">
					<h5>Variants</h5>
					<div class="variants-container">
						<p>Define product variants (e.g., Color, Size)</p>
						<div class="variant-row">
							<input type="text" name="variantNames[]" placeholder="Variant name (e.g. Color)" class="variant-name"/>
							<textarea name="variantValues[]" placeholder="Enter values, one per line&#10;e.g.&#10;Red&#10;Blue&#10;Green" class="variant-values"></textarea>
							<button type="button" class="remove-variant" onclick="removeVariantRow(this)">×</button>
						</div>
						<button type="button" class="add-variant" onclick="addVariantRow()">+ Add Variant</button>
					</div>

					<script>
						function addVariantRow() {
							const container = document.querySelector('.variants-container');
							const newRow = document.createElement('div');
							newRow.className = 'variant-row';
							newRow.innerHTML = `
								<input type="text" name="variantNames[]" placeholder="Variant name (e.g. Color)" class="variant-name"/>
								<textarea name="variantValues[]" placeholder="Enter values, one per line&#10;e.g.&#10;Red&#10;Blue&#10;Green" class="variant-values"></textarea>
								<button type="button" class="remove-variant" onclick="removeVariantRow(this)">×</button>
							`;
							container.insertBefore(newRow, container.querySelector('.add-variant'));
						}

						function removeVariantRow(button) {
							const row = button.parentElement;
							if (document.querySelectorAll('.variant-row').length > 1) {
								row.remove();
							}
						}
					</script>
				</div>

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