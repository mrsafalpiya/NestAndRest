<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin - Product List</title>
<jsp:include page="../head.jsp" />
<link rel="stylesheet" href="${contextPath}/css/admin-style.css">
<link rel="stylesheet" href="${contextPath}/css/admin-product-list.css">
<style>
.stock-bar.low {
	background-color: #ff4d4d;
}

.stock-bar.medium {
	background-color: #ffeb3b;
}

.stock-bar.green {
	background-color: #4caf50;
}

.actions a {
	margin-right: 10px;
	color: #007bff;
	text-decoration: none;
}

.actions a:hover {
	text-decoration: underline;
}

.actions form {
	display: inline;
}

.actions button {
	background: none;
	border: none;
	color: #dc3545;
	cursor: pointer;
	margin-right: 10px;
	text-decoration: none;
}

.actions button:hover {
	text-decoration: underline;
}

.modal {
	display: none;
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgba(0, 0, 0, 0.4);
}

.modal-content {
	background-color: #fefefe;
	margin: 15% auto;
	padding: 20px;
	border: 1px solid #888;
	width: 80%;
	max-width: 500px;
	border-radius: 5px;
}

.modal-content h3 {
	margin-top: 0;
}

.modal-content form {
	display: flex;
	flex-direction: column;
	gap: 10px;
}

.modal-content input, .modal-content select {
	padding: 8px;
	font-size: 14px;
}

.modal-content button {
	padding: 10px;
	cursor: pointer;
}

.modal-content .btn-save {
	background-color: #007bff;
	color: white;
	border: none;
}

.modal-content .btn-cancel {
	background-color: #6c757d;
	color: white;
	border: none;
}

.error {
	color: red;
}
</style>
</head>
<body>
	<div class="admin-layout">
		<jsp:include page="../admin-header.jsp" />
		<jsp:include page="../admin-sidebar.jsp" />

		<main class="admin-product-container">
			<div class="breadcrumb">
				<h2>List</h2>
				<div class="path">
					<span>Dashboard</span> • <span>Product</span> • <span
						class="current">List</span>
				</div>
			</div>

			<div class="stock-bar">
				<div class="filters">
					<form class="admin-product-controls">
						<input type="text" class="search-box" name="search"
							value="${searchQuery}" placeholder="Search products...">
					</form>
				</div>

				<a href="${contextPath}/admin/products/add" class="btn-new-product">+
					New product</a>
			</div>

			<c:if test="${not empty error}">
				<p class="error">${error}</p>
			</c:if>

			<div class="product-table">
				<table>
					<thead>
						<tr>
							<th>Product</th>
							<th>Stock</th>
							<th>Price</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty products}">
								<c:forEach var="product" items="${products}">
									<tr>
										<td>
											<div class="product-info">
												<img
													src="${contextPath}/resources/product-images/${product.getProductId()}.png"
													alt="${product.getName()}"
													style="width: 50px; height: 50px;" />

												<div class="product-text">
													<strong>${product.getName()}</strong><br> <small>${product.getCategory()}</small>
												</div>
											</div>
										</td>
										<td>
											<div
												class="stock-bar ${product.getStockQty() < 5 ? 'low' : product.getStockQty() < 10 ? 'medium' : 'green'}">
												${product.getStockQty()} in stock</div>
										</td>
										<td>Rs. <span
											style="${product.getDiscountedPrice() != 0.0 ? 'text-decoration: line-through' : ''}">${product.price}</span>
											${product.getDiscountedPrice() != 0.0 ? product.getDiscountedPrice() : ''}
										</td>
										<td class="actions"><a
											href="${contextPath}/admin/products/edit?id=${product.productId}">Edit</a><a
											href="#"
											onclick="openEditModal(${product.getProductId()}, '${product.getName()}', ${product.getStockQty()}, ${product.getPrice()}, ${product.getDiscountedPrice()}, '${product.getCategoryId()}')">Quick
												Edit</a>
											<form action="${contextPath}/admin/products/list"
												method="post">
												<input type="hidden" name="action" value="delete" /> <input
													type="hidden" name="productId" value="${product.productId}" />
												<button type="submit"
													onclick="return confirm('Are you sure you want to delete this product?')">Delete</button>
											</form></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="5" style="color: red; text-align: center;">No
										products found.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				<div class="admin-pagination">
					<c:if test="${totalPages > 1}">
						<c:if test="${currentPage > 1}">
							<a href="?page=${currentPage - 1}" class="arrow-btn">←</a>
						</c:if>
						<c:forEach begin="1" end="${totalPages}" var="i">
							<a href="?page=${i}"
								class="page-number ${i == currentPage ? 'active' : ''}">${i}</a>
						</c:forEach>
						<c:if test="${currentPage < totalPages}">
							<a href="?page=${currentPage + 1}" class="arrow-btn">→</a>
						</c:if>
					</c:if>
				</div>
			</div>

			<!-- Edit Modal -->
			<div id="editModal" class="modal">
				<div class="modal-content">
					<h3 style="margin-bottom: 10px;">Edit Product</h3>
					<form id="editForm" action="${contextPath}/admin/products/list"
						method="post">
						<input type="hidden" name="action" value="update" /> <input
							type="hidden" name="productId" id="editProductId" /> <label
							for="editName">Name:</label> <input type="text"
							class="input-text" id="editName" name="name" required /> <label
							for="editStock">Stock:</label> <input type="number"
							id="editStock" class="input-text" name="stock" min="0" required />
						<label for="editPrice">Price:</label> <input type="number"
							id="editPrice" class="input-text" name="price" step="1" min="0"
							required /><label for="editDiscountedPrice">Discounted
							Price:</label> <input type="number" id="editDiscountedPrice"
							class="input-text" name="discount-price" step="1" min="0"
							required /> <label for="editCategory">Category:</label> <select
							id="editCategory" name="category" class="input-text"
							style="background: white" required>
							<c:forEach var="category" items="${allCategories}">
								<option value="${category.getCategoryId()}">${category.getName()}</option>
							</c:forEach>
						</select>
						<div style="margin-top: 10px;">
							<button type="submit" class="btn-save">Save</button>
							<button type="button" class="btn-cancel"
								onclick="closeEditModal()">Cancel</button>
						</div>
					</form>
				</div>
			</div>
		</main>
	</div>
	<script>
        function toggleSelectAll() {
            const selectAll = document.getElementById('selectAll');
            const checkboxes = document.getElementsByName('productIds');
            checkboxes.forEach(checkbox => checkbox.checked = selectAll.checked);
        }

        function openEditModal(productId, name, stock, price, discountedPrice, category) {
            document.getElementById('editProductId').value = productId;
            document.getElementById('editName').value = name;
            document.getElementById('editStock').value = stock;
            document.getElementById('editPrice').value = price;
            document.getElementById('editDiscountedPrice').value = discountedPrice;
            document.getElementById('editCategory').value = category;
            document.getElementById('editModal').style.display = 'block';
        }

        function closeEditModal() {
            document.getElementById('editModal').style.display = 'none';
        }

        window.onclick = function(event) {
            const modal = document.getElementById('editModal');
            if (event.target == modal) {
                closeEditModal();
            }
        }
    </script>
</body>
</html>
