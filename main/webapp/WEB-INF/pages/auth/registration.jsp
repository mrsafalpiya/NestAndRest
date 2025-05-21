<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
<!-- Public Sans Font -->
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,100..900;1,100..900&display=swap"
	rel="stylesheet" />

<link rel="stylesheet" href="${contextPath}/css/styles.css" />
<link rel="stylesheet" href="${contextPath}/css/components.css" />
<link rel="stylesheet" href="${contextPath}/css/auth-reg.css" />
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
				<h3 style="text-align: center; margin-bottom: 16px">Welcome</h3>
				<p class="body1" style="text-align: center">Get ready to explore
					sustainable bamboo & cane furniture.</p>
				<img
					src="${contextPath}/resources/system/images/auth-illustration.png"
					alt="Auth Illustration" class="auth-illustration" />
			</div>
		</div>

		<!-- Form -->
		<div class="auth-form-container full-page-height">
			<form class="auth-form" method="post">
				<h5 style="margin-bottom: 12px">Get started absolutely free</h5>
				<p class="body2" style="margin-bottom: 40px">
					Already have an account? <a href="${contextPath}/login"
						class="subtitle2">Sign in</a>
				</p>

				<div class="auth-inputs">
					<div class="name-fields">
						<div>
							<p class="input-label">First Name</p>
							<input type="text" class="input-text"
								placeholder="Your First Name" name="first-name" id="first-name"
								value="${restoreFirstName}">

						</div>
						<div>
							<p class="input-label">Last Name</p>
							<input type="text" class="input-text"
								placeholder=" Your last Name" name="last-name" id="last-name"
								value="${restoreLastName}">

						</div>
					</div>

					<div>
						<p class="input-label">Email Address</p>
						<input class="input-text" placeholder="yourname@gmail.com"
							name="email" id="email" value="${restoreEmail}">
					</div>

					<div>
						<p class="input-label">Phone Number</p>
						<input class="input-text" placeholder="Phone Number" name="phone"
							id="phone" value="${restorePhone}">
					</div>

					<div>
						<p class="input-label">Gender</p>
						<select id="gender-id" name="gender-id" class="input-text"
							required>
							<option value="" disabled>Select your gender</option>
							<c:forEach var="gender" items="${genders}">
								<option value="${gender.genderId}"
									<c:if test="${gender.genderId == restoreGenderId}">selected</c:if>>${gender.name}</option>
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

					<c:if test="${not empty error}">
					<p class="error-message">${error}</p>
					</c:if>


					<button class="btn btn-lg">Create account</button>
				</div>

				<p class="body2" style="margin-top: 30px">
					By signing up, I agree to the <a href="${contextPath}/terms">terms
						of use</a> and <a href="${contextPath}/privacy-policy">privacy
						policy</a>.
				</p>
			</form>
		</div>
	</div>
</body>
</html>