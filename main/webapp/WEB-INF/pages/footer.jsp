<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<footer>
	<div class="container">
		<div class="footer-left">
			<a href="#"><img
				src="${contextPath}/resources/system/images/logo.png" alt="Logo"
				width="40" height="40" /></a>
			<p class="body2 info">Bringing Nepal's sustainable craftsmanship
				to your home.</p>
		</div>
		<div class="footer-right">
			<div class="footer-links-container">
				<p class="overline">Nest and Rest</p>
				<div class="links">
					<a href="${contextPath}/about-us">About us</a> <a
						href="${contextPath}/contact-us">Contact us</a>
				</div>
			</div>
			<div class="footer-links-container">
				<p class="overline">Legal</p>
				<div class="links">
					<a href="${contextPath}/terms">Terms and condition</a> <a
						href="${contextPath}/privacy-policy">Privacy policy</a>
				</div>
			</div>
			<div class="footer-links-container">
				<p class="overline">Contact</p>
				<div class="links">
					<a href="mailto:contact@nestandrest.com.np">contact@nestandrest.com.np</a>
				</div>
			</div>
		</div>
		<p style="margin-top: 40px;">© All rights reserved.</p>
	</div>
</footer>

<script>
	function openHeaderMenu() {
		document.getElementsByTagName("body")[0].classList.add("menu-open");
	}

	function closeHeaderMenu() {
		document.getElementsByTagName("body")[0].classList.remove("menu-open");
	}
</script>