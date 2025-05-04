<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:include page="../head.jsp" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-add-product.css">
    <title>Edit Product</title>
</head>
<body>
    <div class="admin-layout">
        <jsp:include page="../admin-header.jsp" />
        <jsp:include page="../admin-sidebar.jsp" />
        <main class="admin-product-container">
            <div class="breadcrumb">
                <h4>Edit Product</h4>
                <div class="path">
                    <span>Dashboard</span> •
                    <span>Product</span> •
                    <span class="current">Edit Product</span>
                </div>
            </div>
            <c:if test="${not empty error}">
                <p style="color:red;">${error}</p>
            </c:if>
            <form action="${pageContext.request.contextPath}/admin/products/edit" method="post" enctype="multipart/form-data" class="product-form">
                <input type="hidden" name="productId" value="${product.productId}">
                <input type="hidden" name="existingImage" value="${product.image}">
                <div class="form-section">
                    <h5>Details</h5>
                    <p>Title, short description, image...</p>
                    <input type="text" name="productName" placeholder="Product Name" value="${product.name}" required />
                    <textarea name="subDescription" placeholder="Sub Description">${product.subDescription}</textarea>
                    <label for="fullDescription"><h5>Full Description</h5></label>
                    <textarea name="fullDescription" placeholder="Write something awesome about the product...">${product.fullDescription}</textarea>
                </div>
                <div class="form-section">
                    <h5>Images</h5>
                    <div class="image-upload">
                        <label for="images">
                            <img src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/AddImage.png" alt="Upload" />
                            <p>Select files<br><span>Click to browse through your machine.</span></p>
                        </label>
                        <input type="file" name="images" id="images" accept="image/*" />
                        <c:if test="${not empty product.image}">
                            <p>Current Image: ${product.image}</p>
                        </c:if>
                    </div>
                    <div class="image-actions">
                        <button type="button" class="remove-btn">Remove all</button>
                        <button type="submit" class="upload-btn">
                            <img src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/upload-icon.png" alt="Upload Icon"> Upload
                        </button>
                    </div>
                </div>
                <div class="form-section">
                    <h5>Properties</h5>
                    <div class="properties-grid">
                        <input type="number" name="quantity" placeholder="Quantity" value="${product.quantity}" min="0" required />
                        <div class="form-group">
                            <select id="category" name="category" required>
                                <option value="">Select Category</option>
                                <c:forEach var="category" items="${categories}">
                                    <option value="${category.name}" ${category.name == product.category ? 'selected' : ''}>${category.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <select id="colors" name="colors">
                                <option value="">Select Color</option>
                                <option value="Natural" ${product.color == 'Natural' ? 'selected' : ''}>Natural</option>
                                <option value="Orange" ${product.color == 'Orange' ? 'selected' : ''}>Orange</option>
                                <option value="White" ${product.color == 'White' ? 'selected' : ''}>White</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <select id="sizes" name="sizes">
                                <option value="">Select Size</option>
                                <option value="8" ${product.size == '8' ? 'selected' : ''}>8</option>
                                <option value="9" ${product.size == '9' ? 'selected' : ''}>9</option>
                                <option value="10" ${product.size == '10' ? 'selected' : ''}>10</option>
                                <option value="11" ${product.size == '11' ? 'selected' : ''}>11</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <input type="number" id="price" name="price" placeholder="Enter Price" step="0.01" min="0" value="${product.price}" required />
                        </div>
                        <div class="form-group discounted-price-group">
                            <div class="discount-toggle">
                                <input type="checkbox" id="enableDiscount" onchange="toggleDiscountInput()" ${product.discountedPrice > 0 ? 'checked' : ''} />
                                <input type="number" id="discountedPrice" name="discountedPrice" placeholder="Enter discounted price" step="0.01" min="0" value="${product.discountedPrice > 0 ? product.discountedPrice : ''}" ${product.discountedPrice > 0 ? '' : 'disabled'} />
                            </div>
                        </div>
                    </div>
                </div>
                <label>
                    <input type="checkbox" name="publish" ${product.published ? 'checked' : ''} />
                    Publish
                </label>
                <button type="submit" class="btn-create">Update Product</button>
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