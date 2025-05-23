<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>

<html lang="en">

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Contact Us</title>

<link rel="preconnect" href="https://fonts.googleapis.com" />

<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />

<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,100..900;1,100..900&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="${contextPath}/css/contact-us.css" />

<link rel="stylesheet"
	href="${contextPath}/css/components.css" />

<link rel="stylesheet"
	href="${contextPath}/css/styles.css" />

</head>

<body>

	<jsp:include page="../header.jsp" />

	<!-- Background image section with Hero.png -->
	<section class="location-section"
		style="background-image: url('${contextPath}/resources/system/images/bg.png')">
		<h2>
			<span>Where</span><br>to find us?
		</h2>
		<div class="locations">

			<div>
				<h3>Bouddhanath</h3>
				<p>75 Bouddha Circle, Phulbari Lane - Kathmandu, Nepal / 44621</p>
			</div>
			<div>
				<h3>Thamel</h3>
				<p>238 Narsingh Chowk, Jyatha Road - Kathmandu, Nepal / 44600</p>
			</div>
			<div>
				<h3>Patan Durbar Square</h3>
				<p>412 Mangal Bazaar, Heritage Avenue - Lalitpur, Nepal / 44700</p>
			</div>
			<div>
				<h3>Swayambhunath</h3>
				<p>189 Monkey Temple Road, Hilltop Plaza - Kathmandu, Nepal /
					44620</p>
			</div>
		</div>

	</section>

	<!-- Contact form section -->
	<section class="contact-section">
		<div class="main-container">
			<div class="form-area">
				<c:if test="${success != null}">
					<p class="success-message">${success}</p>
				</c:if>

				<h2>
					Feel free to contact us.<br>We’ll be glad to hear from you.
				</h2>

				<form method="POST">
					<input type="text" placeholder="Name" required name="name">
					<input type="email" placeholder="Email" required name="email">
					<input type="text" placeholder="Subject" required name="subject">
					<textarea placeholder="Enter your message here" required
						name="message"></textarea>

					<c:if test="${error != null}">
						<p class="error-message">${error}</p>
					</c:if>
					<button type="submit">Submit Now</button>
				</form>
			</div>
			<div class="map-area">
				<iframe src="https://www.openstreetmap.org/export/embed.html"
					loading="lazy"></iframe>
			</div>
		</div>
	</section>

	<jsp:include page="../footer.jsp" />

</body>

</html>