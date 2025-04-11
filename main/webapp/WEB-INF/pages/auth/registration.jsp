<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				alt="Logo" class="auth-header-logo" /></a> 
            <a href="${pageContext.request.contextPath}/contact-us"
			 class="subtitle2" style="color: var(--text-primary)">Need help?</a>
        </div>

        <!-- Banner -->
        <div class="auth-banner full-page-height">
            <div class="auth-banner-content">
                <h3 style="text-align: center; margin-bottom: 16px">Welcome</h3>
                <p class="body1" style="text-align: center">
                    Get ready to explore sustainable bamboo & cane furniture.
                </p>
               <img
					src="${pageContext.request.contextPath}/resources/system/images/auth-illustration.png"
					alt="Auth Illustration" class="auth-illustration" />
            </div>
        </div>

        <!-- Form -->
        <div class="auth-form-container full-page-height">
            <form class="auth-form">
                <h5 style="margin-bottom: 12px">Get started absolutely free</h5>
                <p class="body2" style="margin-bottom: 40px">
                    Already have an account? <a href="index.html" class="subtitle2">Sign in</a>
                </p>

                <div class="auth-inputs">
                    <div class="name-fields">
                        <div>
                            <p class="input-label">First Name</p>
                            <input type="text" class="input-text" placeholder="Your First Name ">
                        </div>
                        <div>
                            <p class="input-label">Last Name</p>
                            <input type="text" class="input-text" placeholder=" Your last Name">
                           
                        </div>
                    </div>

                    <div>
                        <p class="input-label">Email Address</p>
                        <input type="email" class="input-text" placeholder="yourname@gmail.com ">
                    </div>

                    <div>
                        <p class="input-label">Password</p>
                        <input type="password" class="input-text" placeholder="6+ characters">
                    </div>

                    <button class="btn btn-lg">Create account</button>
                </div>

                <p class="body2" style="margin-top: 30px">
                    By signing up, I agree to the <a href="terms.html">terms of use</a> and <a href="privacy.html">privacy policy</a>.
                </p>
            </form>
        </div>
    </div>
</body>
</html>