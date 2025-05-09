<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, com.nestandrest.model.ProductModel"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Product Listing - Nest and Rest</title>
<jsp:include page="../head.jsp" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/product-listing.css">
</head>

<body>
	<jsp:include page="../header.jsp" />
	<main class="product-page container">
		<div class="product-header">
			<div class="product-header-top">
				<h4>Shop</h4>
				<form class="search-sort-container">

					<input type="text" placeholder="Search..." class="search-input"
						name="search" value="${searchQuery}" />

					<div class filter-controls>
						<select class="category-select" name="category"
							onchange="form.submit()">
							<option value="0"
								<%=(request.getAttribute("selectedCategory") == null || "0".equals(request.getAttribute("selectedCategory")))
		? "selected"
		: ""%>>All
								Categories</option>

							<c:forEach var="category" items="${allCategories}">
								<option value="${category.getCategoryId()}"
									${category.getCategoryId() == selectedCategory ? 'selected' : ''}>${category.getName()}</option>
							</c:forEach>
						</select> <select class="sort-select" name="order" onchange="form.submit()">
							<option value="new" ${orderByQuery == 'new' ? 'selected' : ''}>Sort
								by: Newest First</option>
							<option value="cheap"
								${orderByQuery == 'cheap' ? 'selected' : ''}>Price: Low
								to High</option>
							<option value="expensive"
								${orderByQuery == 'expensive' ? 'selected' : ''}>Price:
								High to Low</option>
						</select>
					</div>
					<a href="cart.jsp" class="cart-icon"> <img
						src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/AddToCartIcon.png"
						alt="Cart" /> <span class="cart-count"></span>
					</a>
				</form>
			</div>
		</div>

		<div class="product-grid">
			<%
			List<ProductModel> products = (List<ProductModel>) request.getAttribute("products");
			if (products != null && !products.isEmpty()) {
				for (ProductModel p : products) {
			%>
			<a href="product-details?id=<%=p.getProductId()%>"
				style="text-decoration: none; color: inherit;">
				<div class="product-card">
					<img
						src="<%=request.getContextPath()%>/resources/product-images/<%=p.getProductId()%>.png"
						alt="<%=p.getName()%>">
					<h5><%=p.getName()%></h5>
					<p>
						Rs <span
							class="<%=p.getDiscountedPrice() != 0.0 ? "strike" : ""%>"><%=String.format("%.2f", p.getPrice())%></span>
						<span><%=p.getDiscountedPrice() != 0.0 ? String.format("%.2f", p.getDiscountedPrice()) : ""%></span>
					</p>

				</div>
			</a>
			<%
			}
			} else {
			%>
			<p style="color: red;">No products found!</p>
			<%
			}
			%>
		</div>



		<div class="pagination">
			<%
			Integer totalPages = (Integer) request.getAttribute("totalPages");
			Integer currentPage = (Integer) request.getAttribute("currentPage");
			String selectedCategory = (String) request.getAttribute("selectedCategory");

			if (totalPages != null && currentPage != null) {
				for (int i = 1; i <= totalPages; i++) {
					if (i == currentPage) {
			%>
			<span class="active"><%=i%></span>
			<%
			} else {
			%>
			<a
				href="products?<%=selectedCategory != null ? "category=" + selectedCategory + "&" : ""%>page=<%=i%>"><%=i%></a>
			<%
			}
			}
			}
			%>
		</div>

	</main>

	<jsp:include page="../footer.jsp" />
</body>
</html>