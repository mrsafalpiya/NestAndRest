<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header class="user-header">
	<div class="header-menu-content">
		<a href="#" style="width: 100%"><img src="${pageContext.request.contextPath}/resources/system/images/logo.png"
			alt="Nest and Rest" width="55" height="55" /></a>
		<div class="header-menu-links-container">
			<a href="#" class="header-menu-link">Home</a> <a href="#"
				class="header-menu-link">Shop</a> <a href="#"
				class="header-menu-link">About Us</a> <a href="#"
				class="header-menu-link">Contact Us</a>
		</div>
	</div>
	<div class="header-menu-open-overlay" onclick="closeHeaderMenu()"></div>
	<div class="header-content container">
		<div class="header-left">
			<button class="header-menu" onclick="openHeaderMenu()">
				<svg width="40" height="40" viewBox="0 0 40 40" fill="none"
					xmlns="http://www.w3.org/2000/svg">
              <path opacity="0.32"
						d="M23.7798 12.5H13.2202C12.2717 12.5 11.5 13.0606 11.5 13.7504C11.5 14.4394 12.2717 15 13.2202 15H23.7798C24.7283 15 25.5 14.4394 25.5 13.7504C25.5 13.0605 24.7283 12.5 23.7798 12.5Z"
						fill="#637381" />
              <path
						d="M26.7798 18.75H16.2202C15.2717 18.75 14.5 19.3106 14.5 20.0004C14.5 20.6894 15.2717 21.25 16.2202 21.25H26.7798C27.7283 21.25 28.5 20.6894 28.5 20.0004C28.5 19.3105 27.7283 18.75 26.7798 18.75Z"
						fill="#637381" />
              <path
						d="M23.7798 25H13.2202C12.2717 25 11.5 25.5606 11.5 26.2504C11.5 26.9394 12.2717 27.5 13.2202 27.5H23.7798C24.7283 27.5 25.5 26.9394 25.5 26.2504C25.5 25.5606 24.7283 25 23.7798 25Z"
						fill="#637381" />
            </svg>
			</button>

			<a href="#"><img src="${pageContext.request.contextPath}/resources/system/images/logo.png" alt="Nest and Rest"
				width="40" height="40" /></a>
		</div>

		<div class="header-right">
			<div class="header-links">
				<a href="#">Home</a>
				<div class="categories-menu">
					<div class="categories-menu-container">
						<div class="categories-menu-content">
							<a href="#" class="categories-entry">Category 1</a> <a href="#"
								class="categories-entry">Category 2</a>
						</div>
					</div>
					<a href="#" class="categories-menu-link"> <svg
							xmlns="http://www.w3.org/2000/svg" width="6" height="6"
							viewBox="0 0 6 6" fill="none">
                  <circle opacity="0.48" cx="3" cy="3" r="3"
								fill="#1C252E" />
                </svg> Shop <svg xmlns="http://www.w3.org/2000/svg" width="16"
							height="16" viewBox="0 0 16 16" fill="none">
                  <path
								d="M8.00022 10.3342C7.84445 10.3345 7.69349 10.2802 7.57355 10.1809L3.57355 6.84752C3.29005 6.61188 3.25124 6.19103 3.48689 5.90752C3.72253 5.62402 4.14338 5.58521 4.42689 5.82086L8.00022 8.80752L11.5736 5.92752C11.7113 5.81567 11.8879 5.76334 12.0643 5.78211C12.2408 5.80088 12.4024 5.8892 12.5136 6.02752C12.637 6.16609 12.6971 6.34984 12.6793 6.53456C12.6616 6.71928 12.5677 6.88826 12.4202 7.00086L8.42022 10.2209C8.29683 10.3045 8.14895 10.3444 8.00022 10.3342Z"
								fill="#1C252E" />
                </svg>
					</a>
				</div>
				<a href="#">About Us</a> <a href="#">Contact Us</a>
			</div>
			<div class="header-auth">
				<button class="btn btn-outlined">Sign In</button>
				<button class="btn">Register</button>
			</div>
		</div>
	</div>
</header>