<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.nestandrest.model.Product" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin - Product List</title>
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <jsp:include page="../head.jsp" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-product-list.css">
</head>
<body>
    <div class="admin-layout">
   		 <%-- Header (top right admin icon) --%>
        <jsp:include page="../admin-header.jsp" />
        <%-- Sidebar --%>
        <jsp:include page="../admin-sidebar.jsp" />

        <%-- Main Product Container --%>
        <main class="admin-product-container">

            <div class="breadcrumb">
                <h2>List</h2>
                <div class="path">
                    <span>Dashboard</span> &bull;
                    <span>Product</span> &bull;
                    <span class="current">List</span>
                </div>
            </div>

            <div class="stock-bar">
                <div class="filters">
                    <select class="filter-select">
                        <option>Stock</option>
                    </select>
                    <div class="admin-product-controls">
   						 <input type="text" class="search-box" placeholder="Search products...">
					</div>
                </div>
                <div class="filter-controls">
   					 <div class="applied-filters">
        				<span class="filter-tag">Stock : Low <img src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/X.png" class="close-icon"></span>
        				<span class="filter-tag">Search : Keyword <img src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/X.png" class="close-icon"></span>
        				<span class="clear-all">
            			<img src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/Delete.png" class="delete-icon"> Clear</span>
    				</div>
				</div>
              
                <a href="add-product.jsp" class="btn-new-product">+ New product</a>
            </div>

			<div class="product-table">
				<table>
					<thead>
						<tr>
							<th><input type="checkbox" /></th>
							<th>Product</th>
							<th>Created at</th>
							<th>Stock</th>
							<th>Price</th>
						</tr>
					</thead>
					<tbody>
						<%
                            List<Product> products = (List<Product>) request.getAttribute("products");
                            if (products != null && !products.isEmpty()) {
                                for (Product product : products) {
                        %>
						<tr>
							<td><input type="checkbox" name="productIds"
								value="placeholder" /></td>
							<td>
								<div class="product-info">
									<img
										src="${pageContext.request.contextPath}/resources/system/images/<%= product.getImage() %>"
										alt="<%= product.getName() %>">
									<div class="product-text">
										<strong><%= product.getName() %></strong><br> <small><%= product.getCategory() %></small>
									</div>
								</div>
							</td>
							<td>06 May 2022</td>
							<td>
								<div class="stock-bar green">72 in stock</div>
							</td>
							<td>Rs. <%= product.getPrice() %></td>
						</tr>
						<%
                                }
                            } else {
                        %>
						<tr>
							<td colspan="4" style="color: red; text-align: center;">No
								products found.</td>
						</tr>
						<%
                            }
                        %>

					</tbody>
				</table>
				<div class="admin-pagination">
    <%
        Integer totalPages = (Integer) request.getAttribute("totalPages");
        Integer currentPage = (Integer) request.getAttribute("currentPage");

        if (totalPages != null && currentPage != null) {
            if (currentPage > 1) {
    %>
        <a href="?page=<%=currentPage - 1%>" class="arrow-btn">&#8592;</a>
    <%
            }
            for (int i = 1; i <= totalPages; i++) {
    %>
        <a href="?page=<%=i%>" class="page-number <%= (i == currentPage) ? "active" : "" %>"><%=i%></a>
    <%
            }
            if (currentPage < totalPages) {
    %>
        <a href="?page=<%=currentPage + 1%>" class="arrow-btn">&#8594;</a>
    <%
            }
        }
    %>
</div>

			</div>
		</main>
    </div>
</body>
</html>
