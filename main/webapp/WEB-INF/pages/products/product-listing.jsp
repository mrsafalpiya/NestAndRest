<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Product Listing - Nest and Rest</title>
<jsp:include page="../head.jsp" />
<link rel="stylesheet"
	href="${contextPath}/css/product-listing.css">
</head>

<body>
	<jsp:include page="../header.jsp" />

	<main class="product-page container">
		<div class="product-header">
			<div class="product-header-top">
				<h4>Shop</h4>
				<!-- Search and Filter Form -->
				<form class="search-sort-container">
					<input type="text" placeholder="Search..." class="search-input"
						name="search" value="${searchQuery}" />

					<div class="filter-controls">
						<!-- Category Dropdown -->
						<select class="category-select" name="category"
							onchange="form.submit()">
							<option value="0"
								<c:if test="${selectedCategory == null || selectedCategory == '0'}">selected</c:if>>All
								Categories</option>
							<c:forEach var="category" items="${allCategories}">
								<option value="${category.categoryId}"
									<c:if test="${category.categoryId == selectedCategory}">selected</c:if>>${category.name}</option>
							</c:forEach>
						</select>

						<!-- Sort Dropdown -->
						<select class="sort-select" name="order" onchange="form.submit()">
							<option value="new"
								<c:if test="${orderByQuery == 'new'}">selected</c:if>>Sort
								by: Newest First</option>
							<option value="cheap"
								<c:if test="${orderByQuery == 'cheap'}">selected</c:if>>Price:
								Low to High</option>
							<option value="expensive"
								<c:if test="${orderByQuery == 'expensive'}">selected</c:if>>Price:
								High to Low</option>
						</select>
					</div>

				</form>
			</div>
		</div>

		<!-- Product Grid -->
		<div class="product-grid">
			<c:choose>
				<c:when test="${not empty products}">
					<c:forEach var="p" items="${products}">
						<a href="product-details?id=${p.productId}"
							style="text-decoration: none; color: inherit;">
							<div class="product-card">
								<img
									src="${contextPath}/resources/product-images/${p.productId}.png"
									alt="${p.name}" />
								<h5>${p.name}</h5>
								<p>
									Rs <span
										class="<c:if test='${p.discountedPrice != 0.0}'>strike</c:if>">${p.price}</span>
									<c:if test="${p.discountedPrice != 0.0}">
										<span>${p.discountedPrice}</span>
									</c:if>
								</p>
							</div>
						</a>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p style="color: red;">No products found!</p>
				</c:otherwise>
			</c:choose>
		</div>

		<!-- Pagination -->
		<div class="pagination">
			<c:if test="${not empty totalPages && not empty currentPage}">
				<c:forEach var="i" begin="1" end="${totalPages}">
					<c:choose>
						<c:when test="${i == currentPage}">
							<span class="active">${i}</span>
						</c:when>
						<c:otherwise>
							<a
								href="products?<c:if test='${selectedCategory != null}'>category=${selectedCategory}&</c:if>page=${i}">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:if>
		</div>
	</main>

	<jsp:include page="../footer.jsp" />
</body>
</html>
