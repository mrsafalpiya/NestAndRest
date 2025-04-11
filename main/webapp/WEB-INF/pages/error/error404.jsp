<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error404</title>
<!-- Public Sans Font -->
  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link href="https://fonts.googleapis.com/css2?family=Public+Sans:wght@300;400;600;700&display=swap" rel="stylesheet" />

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/error.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components.css" />
 
  
</head>
<body>
	 <div class="error-layout">
	    <!-- Header -->
	    <div class="error-header">
	    <a href="${pageContext.request.contextPath}/">
	      <img src="${pageContext.request.contextPath}/resources/system/images/logo.png"
		  alt="Logo" class="error-header-logo" /></a>
	      <a href="${pageContext.request.contextPath}/contact-us"
		   class="subtitle2" style="color: var(--text-primary)">Need help?</a>
	    </div>
	
	    <!-- Main Content -->
	    <div class="container">
	      <div class="content">
	        <h1 class="title">Sorry, page not found!</h1>
	        <p class="body1">Sorry, we couldn’t find the page you’re looking for. Perhaps you’ve mistyped the URL? Be sure to check your spelling.</p>
	        <div class="error-img">
	          <img src="${pageContext.request.contextPath}/resources/system/images/illustration-404.png"
		 		 alt="error404" />
	        </div>
	        <button class="btn btn-lg">Go to Home</button>
	        
	      </div>
	    </div>
  </div>
</body>
</html>