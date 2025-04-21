<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>registration</title>
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
	href="${pageContext.request.contextPath}/css/auth-reg.css" />
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
				<h3 style="text-align: center; margin-bottom: 16px">Welcome</h3>
				<p class="body1" style="text-align: center">Get ready to explore
					sustainable bamboo & cane furniture.</p>
				<img
					src="${pageContext.request.contextPath}/resources/system/images/auth-illustration.png"
					alt="Auth Illustration" class="auth-illustration" />
			</div>
		</div>

		<!-- Form -->
		<div class="auth-form-container full-page-height">
			<form class="auth-form" method="post">
				<h5 style="margin-bottom: 12px">Get started absolutely free</h5>
				<p class="body2" style="margin-bottom: 40px">
					Already have an account? <a
						href="${pageContext.request.contextPath}/login" class="subtitle2">Sign
						in</a>
				</p>

				<div class="auth-inputs">
					<div class="name-fields">
						<div>
							<p class="input-label">First Name</p>
							<input type="text" class="input-text"
								placeholder="Your First Name" name="first-name" id="first-name">

						</div>
						<div>
							<p class="input-label">Last Name</p>
							<input type="text" class="input-text"
								placeholder=" Your last Name" name="last-name" id="last-name">

						</div>
					</div>

					<div>
						<p class="input-label">Email Address</p>
						<input class="input-text" placeholder="yourname@gmail.com"
							name="email" id="email">
					</div>

					<div>
						<p class="input-label">Phone Number</p>
						<input class="input-text" placeholder="Phone Number" name="phone"
							id="phone">
					</div>

					<div>
						<p class="input-label">Gender</p>
						<select id="gender-id" name="gender-id" class="input-text"
							required>
							<option value="" disabled>Select your gender</option>
							<c:forEach var="gender" items="${genders}">
								<option value="${gender.genderId}">${gender.name}</option>
							</c:forEach>
						</select>
					</div>
				

					<div>
						<p class="input-label">Password</p>
						<input type="password" class="input-text"
							placeholder="8+ characters" name="password" id="password">
					</div>

					<div>
						<p class="input-label">Confirm Password</p>
						<input type="password" class="input-text"
							placeholder="Confirm Your Password" name="password-confirm"
							id="password-confirm">
					</div>

					<%
					String errorMessage = (String) request.getAttribute("error");
					if (errorMessage != null) {
					%>
					<p class="error-message"><%=errorMessage%></p>
					<%
					}
					%>
					

					<button class="btn btn-lg">Create account</button>
				</div>

				<p class="body2" style="margin-top: 30px">
					By signing up, I agree to the <a href="terms.html">terms of use</a>
					and <a href="privacy.html">privacy policy</a>.
				</p>
			</form>
		</div>
	</div>
</body>
</html>