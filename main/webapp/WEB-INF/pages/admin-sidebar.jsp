<aside id="adminSidebar" class="admin-sidebar">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<!-- Mobile Menu Button -->
	<button class="mobile-menu-btn" onclick="toggleSidebar()">
		<img
			src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/menu.png"
			alt="Menu">
	</button>

	<div class="logo">
		<a href="${contextPath}/"> <img
			src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/HeadLogo.png"
			alt="Logo"></a>
	</div>
	<button id="sidebarToggle" class="sidebar-toggle-btn">
		<img
			src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/toggle-button.png"
			alt="Toggle" />
	</button>
	<nav class="sidebar-nav">
		<ul>
			<li class="menu-title">OVERVIEW</li>
			<li><a href="${contextPath}/admin"><img
					src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/Dashboard.png"
					class="icon"> Dashboard</a></li>
			<li class="menu-title">MANAGEMENT</li>
			<li></li>
			<!-- USER MENU -->
			<li><a href="${contextPath}/usermanagement-list"><img
					src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/User.png"
					class="icon"> User</a></li>

			<!-- PRODUCT MENU -->
			<li><a href="${contextPath}/admin/products/list"><img
					src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/Product.png"
					class="icon"> Product</a></li>
			<li><a href="${contextPath}/admin-orders"><img
					src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/Cart.png"
					class="icon"> Order</a></li>
			<li><a href="${contextPath}/user-query"><img
					src="${pageContext.request.contextPath}/resources/system/images/user-query.png"
					class="icon"> User Query</a></li>
		</ul>
	</nav>

	<script>
		const toggleBtn = document.getElementById("sidebarToggle");
		const sidebar = document.getElementById("adminSidebar");

		toggleBtn.addEventListener("click", () => {
    		sidebar.classList.toggle("collapsed");
    		const container = document.querySelector(".admin-product-container");
    			if (container) {
        			container.style.marginLeft = sidebar.classList.contains("collapsed") ? "60px" : "193px";
   		 }
		});
</script>

	<script>
    function toggleSidebar() {
        const sidebar = document.querySelector('.admin-sidebar');
        sidebar.classList.toggle('active');
    }
</script>



</aside>