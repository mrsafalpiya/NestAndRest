<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/components.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/auth.css" />
<title>Login - Nest and Rest Furniture</title>
</head>
<body>
	<div class="auth-layout full-page-height">
		<!-- Header -->
		<div class="auth-header">
			<a href="${pageContext.request.contextPath}/"><img
				src="${pageContext.request.contextPath}/resources/system/images/logo.png"
				alt="Logo" class="auth-header-logo" /></a> <a
				href="${pageContext.request.contextPath}/contact-us"
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
					src="${pageContext.request.contextPath}/resources/system/images/auth-illustration.png"
					alt="Auth Illustration" class="auth-illustration" />
			</div>
		</div>

		<!-- Form -->
		<div class="auth-form-container full-page-height">
			<form class="auth-form" method="post">
				<%
				String successMessage = (String) request.getAttribute("success");
				if (successMessage != null) {
				%>
				<p class="success-message" style="margin-bottom: 12px;"><%=successMessage%></p>
				<%
				}
				%>
				<h5 style="margin-bottom: 12px">Sign in to your account</h5>
				<p class="body2" style="margin-bottom: 40px">
					Don't have an account? <a
						href="${pageContext.request.contextPath}/registration"
						class="subtitle2">Get started</a>
				</p>
				<div class="auth-inputs">
					<div>
						<p class="input-label">Email address</p>
						<input class="input-text" placeholder="test@mail.com" name="email"
							id="email" />
					</div>
					<div>
						<p class="input-label">Password</p>
						<input type="password" class="input-text"
							placeholder="8+ characters" name="password" id="password" />
					</div>

					<%
					String errorMessage = (String) request.getAttribute("error");
					if (errorMessage != null) {
					%>
					<p class="error-message"><%=errorMessage%></p>
					<%
					}
					%>
					<button class="btn btn-lg">Sign in</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
