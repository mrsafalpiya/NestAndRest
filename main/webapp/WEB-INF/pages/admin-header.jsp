<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-style.css">
<body>
<div class="admin-header">
    <div class="breadcrumb">
        <h4>List</h4>
        <div class="path">
            <span>Dashboard</span> &bull;
            <span>Product</span> &bull;
            <span class="current">List</span>
        </div>
    </div>
    <div class="header-right">
        <img src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/AdminLogo.png" alt="Admin Profile" class="profile-icon">
        <a href="add-product.jsp" class="btn-new-product">+ New product</a>
    </div>
</div>
</body>
</html>