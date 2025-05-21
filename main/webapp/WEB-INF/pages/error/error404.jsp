<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Not Found</title>
<!-- Public Sans Font -->
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:wght@300;400;600;700&display=swap"
	rel="stylesheet" />

<link rel="stylesheet"
	href="${contextPath}/css/error.css" />
<link rel="stylesheet"
	href="${contextPath}/css/components.css" />


</head>
<body>
	<div class="error-layout">
		<!-- Header -->
		<div class="error-header">
			<a href="${contextPath}/"> <img
				src="${contextPath}/resources/system/images/logo.png"
				alt="Logo" class="error-header-logo" /></a> <a
				href="${contextPath}/contact-us"
				class="subtitle2" style="color: var(--text-primary)">Need help?</a>
		</div>

		<!-- Main Content -->
		<div class="container">
			<div class="content">
				<h1 class="title">Sorry, page not found!</h1>
				<p class="body1">Sorry, we couldn’t find the page you’re looking
					for. Perhaps you’ve mistyped the URL? Be sure to check your
					spelling.</p>
				<div class="error-img">
					<img
						src="${contextPath}/resources/system/images/illustration-404.png"
						alt="error404" />
				</div>

				<a href="${contextPath}/home"><button class="btn btn-lg">Go
						to Home</button></a>

			</div>
		</div>
	</div>
</body>
</html>