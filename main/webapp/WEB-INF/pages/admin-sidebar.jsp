<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-style.css">
<aside class="admin-sidebar">
    <div class="logo">
        <img src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/HeadLogo.png" alt="Logo">
    </div>
    <nav>
        <ul>
            <li class="menu-title">OVERVIEW</li>
            <li>
                <a href="#"><img src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/Dashboard.png" class="icon"> Dashboard</a>
            </li>
            <li class="menu-title">MANAGEMENT</li>
            <li>
                <a href="#"><img src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/User.png" class="icon"> User</a>
            </li>
            <li>
                <a href="#"><img src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/Product.png" class="icon"> Product</a>
                <ul class="submenu">
                    <li><a href="product-list.jsp">List</a></li>
                    <li><a href="product-details.jsp">Details</a></li>
                    <li><a href="add-product.jsp">Create</a></li>
                    <li><a href="#">Edit</a></li>
                </ul>
            </li>
            <li>
                <a href="#"><img src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/Cart.png" class="icon"> Order</a>
            </li>
        </ul>
    </nav>
</aside>
</html>