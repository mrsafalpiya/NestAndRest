<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, com.nestandrest.model.Product" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Listing - Nest and Rest</title>
    <jsp:include page="../head.jsp" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product-listing.css">
</head>

<body>
<jsp:include page="../header.jsp" />
<main class="product-page container">
    <div class="product-header">
    <div class="product-header-top">
        <h4>Shop</h4>
        <div class="search-sort-container">
        
        		<input type="text" placeholder="Search..." class="search-input"/>
	    	
	    		<div class filter-controls>
			        <select class="category-select" onchange="filterCategory(this.value)">
			            <option value="all" <%= (request.getAttribute("selectedCategory") == null || "all".equals(request.getAttribute("selectedCategory"))) ? "selected" : "" %>>All Categories</option>
			            <option value="sofa" <%= "sofa".equals(request.getAttribute("selectedCategory")) ? "selected" : "" %>>Sofas</option>
			            <option value="chair" <%= "chair".equals(request.getAttribute("selectedCategory")) ? "selected" : "" %>>Chairs</option>
			            <option value="stool" <%= "stool".equals(request.getAttribute("selectedCategory")) ? "selected" : "" %>>Stools</option>
			            <option value="rack" <%= "rack".equals(request.getAttribute("selectedCategory")) ? "selected" : "" %>>Racks</option>
			            <option value="hanger" <%= "hanger".equals(request.getAttribute("selectedCategory")) ? "selected" : "" %>>Hangers</option>
			            <option value="table" <%= "table".equals(request.getAttribute("selectedCategory")) ? "selected" : "" %>>Tables</option>
			        </select>
			
			        <select class="sort-select">
			            <option>Sort by: Featured</option>
			            <option>Price: Low to High</option>
			            <option>Price: High to Low</option>
			        </select>
		        </div>
					<a href="cart.jsp" class="cart-icon"> <img
						src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/AddToCartIcon.png"
						alt="Cart" /> <span class="cart-count"></span>
					</a>
			</div>
</div>
</div>

		<div class="product-grid">
			<%
			List<Product> products = (List<Product>) request.getAttribute("products");
			if (products != null && !products.isEmpty()) {
				for (Product p : products) {
			%>
			<a href="product-details?id=<%=products.indexOf(p)%>"
				style="text-decoration: none; color: inherit;">
				<div class="product-card">
					<img
						src="<%=request.getContextPath()%>/resources/system/images/<%=p.getImage()%>"
						alt="<%=p.getName()%>">
					<h5><%=p.getName()%></h5>
					<p>Rs.<%= String.format("%.2f", p.getPrice()) %></p>

				</div>
			</a>
			<%
			}
			} else {
			%>
			<p style="color: red;">No products found for this category.</p>
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
                    <span class="active"><%= i %></span>
    <%
                } else {
    %>
                    <a href="products?<%= selectedCategory != null ? "category=" + selectedCategory + "&" : "" %>page=<%= i %>"><%= i %></a>
    <%
                }
            }
        }
    %>
</div>

</main>

<jsp:include page="../footer.jsp" />
<script>
    function filterCategory(category) {
        if (category === 'all') {
            window.location.href = 'products';
        } else {
            window.location.href = 'products?category=' + category;
        }
    }
</script>
</body>
</html>