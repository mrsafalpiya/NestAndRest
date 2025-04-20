<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
 <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <jsp:include page="../head.jsp" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-add-product.css">
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
                    <span>Dashboard</span> &bull;
                    <span>Product</span> &bull;
                    <span class="current">New Product</span>
                </div>
            </div>

            <!-- Form Container -->
            <form action="AddProductServlet" method="post" enctype="multipart/form-data" class="product-form">

                <!-- Product Details -->
                <div class="form-section">
                    <h5>Details</h5>
                    <p>Title, short description, image...</p>
                    <input type="text" name="productName" placeholder="Product Name" required />
                    <textarea name="subDescription" placeholder="Sub Description"></textarea>
                    <label for="fullDescription"><h5> Full Description </h5></label>
                    <textarea name="fullDescription" placeholder="Write something awesome about the product..."></textarea>
                </div>

                <!-- Image Upload -->
                <div class="form-section">
                    <h5>Images</h5>
                    <div class="image-upload">
                        <label for="images">
                            <img src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/AddImage.png" alt="Upload" />
                            <p>Select files<br><span>Click to browse through your machine.</span></p>
                        </label>
                        <input type="file" name="images" id="images" multiple />
					</div>
					<div class="image-actions">
							<button type="button" class="remove-btn">Remove all</button>
							<button type="submit" class="upload-btn">
								<img
									src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/upload-icon.png"
									alt="Upload Icon"> Upload
							</button>
						</div>
                </div>

                <!-- Product Properties -->
                <div class="form-section">
                    <h5>Properties</h5>
                    <div class="properties-grid">
	                    <input type="number" name="quantity" placeholder="Quantity" />
	                    <!-- Category -->
						<div class="form-group">
							<select id="category" name="category">
								<option value="">Category</option>
								<option value="sofa">Sofa</option>
								<option value="chair">Chair</option>
								<option value="table">Table</option>
							</select>
						</div>

						<!-- Colors -->
						<div class="form-group">
							<select id="colors" name="colors">
								<option value="">Colors</option>
								<option value="red">Natural</option>
								<option value="blue">Orange</option>
								<option value="green">White</option>
							</select>
						</div>

						<!-- Sizes -->
						<div class="form-group">
							<select id="sizes" name="sizes">
								<option value="">Size</option>
								<option>8</option>
								<option>9</option>
								<option>10</option>
								<option>11</option>
							</select>
						</div>

						<!-- Price -->
						<div class="form-group">
							<input type="text" id="price" placeholder="Enter Price" />
						</div>

						<!-- Discounted Price with toggle -->
						<div class="form-group discounted-price-group">
							<div class="discount-toggle">
								<input type="checkbox" id="enableDiscount"
									onchange="toggleDiscountInput()" /> <input type="text"
									id="discountedPrice" name="discountedPrice"
									placeholder="Enter discounted price" disabled />
							</div>
						</div>

					</div>
                </div>

					<label>
                        <input type="checkbox" name="publish" />
                        Publish
                    </label> <button type="submit" class="btn-create">Create Product</button>
            </form>
            </main>
        </div>
	<script>
		function toggleDiscountInput() {
			const toggle = document.getElementById('enableDiscount');
			const input = document.getElementById('discountedPrice');
			input.disabled = !toggle.checked;
		}
	</script>

</body>
</html>