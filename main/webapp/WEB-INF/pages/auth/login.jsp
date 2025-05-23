<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- Public Sans Font -->
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,100..900;1,100..900&display=swap"
	rel="stylesheet" />

<link rel="stylesheet" href="${contextPath}/css/styles.css" />
<link rel="stylesheet" href="${contextPath}/css/components.css" />
<link rel="stylesheet" href="${contextPath}/css/auth.css" />
<title>Login</title>
</head>
<body>



	<div class="auth-layout full-page-height">
		<!-- Header -->

		<div class="auth-header">
			<a href="${contextPath}/home"><img
				src="${contextPath}/resources/system/images/logo.png" alt="Logo"
				class="auth-header-logo" /></a> <a href="${contextPath}/contact-us"
				class="subtitle2" style="color: var(--text-primary)">Need help?</a>
		</div>

		<!-- Banner -->
		<div class="auth-banner full-page-height">
			<div class="auth-banner-content">
				<h3 style="text-align: center; margin-bottom: 16px">Hi, Welcome
					back</h3>
				<p class="body1" style="text-align: center">Sign in to continue
					your sustainable furniture journey.</p>
				<img
					src="${contextPath}/resources/system/images/auth-illustration.png"
					alt="Auth Illustration" class="auth-illustration" />
			</div>
		</div>

		<!-- Form -->
		<div class="auth-form-container full-page-height">
			<form class="auth-form" method="post" action="${contextPath}/login">
				<c:if test="${not empty success}">
				<p class="success-message" style="margin-bottom: 12px;">${success}</p>
				</c:if>
				<h5 style="margin-bottom: 12px">Sign in to your account</h5>
				<p class="body2" style="margin-bottom: 40px">
					Don't have an account? <a href="${contextPath}/registration"
						class="subtitle2">Get started</a>
				</p>
				<div class="auth-inputs">
					<div>
						<p class="input-label">Email address</p>
						<input type="email" name="email" class="input-text"
							placeholder="yourname@gmail.com"
							value="${rememberedEmail != null ? rememberedEmail : ''}">

					</div>
					<div>
						<p class="input-label">Password</p>
						<input type="password" class="input-text"
							placeholder="8+ characters" name="password" id="password" />
					</div>


					<c:if test="${not empty error}">
					<p class="error-message">${error}</p>
					</c:if>
					<div class="checkbox-group" style="margin-bottom: 20px;">
						<input type="checkbox" name="rememberMe" id="rememberMe">
						<label for="rememberMe" style="margin-left: 8px;">Remember
							Me</label>
					</div>
					<button class="btn btn-lg">Sign in</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>

