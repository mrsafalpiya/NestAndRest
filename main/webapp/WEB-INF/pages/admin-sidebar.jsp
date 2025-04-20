<aside id="adminSidebar" class="admin-sidebar">
	<!-- Mobile Menu Button -->
	<button class="mobile-menu-btn" onclick="toggleSidebar()">
	    <img src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/menu.png" alt="Menu">
	</button>
		
    <div class="logo">
        <img src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/HeadLogo.png" alt="Logo">
    </div>
    <button id="sidebarToggle" class="sidebar-toggle-btn">
    	<img src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/toggle-button.png" alt="Toggle" />
	</button>
    <nav class="sidebar-nav">
        <ul>
            <li class="menu-title">OVERVIEW</li>
            <li>
                <a href="#"><img src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/Dashboard.png" class="icon"> Dashboard</a>
            </li>
            <li class="menu-title">MANAGEMENT</li>
            <li>
            </li>
            <!-- USER MENU -->
<li>
    <div class="menu-item collapsible" onclick="toggleSubmenu(this)">
        <img src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/User.png" class="icon">
        <span>User</span>
        <span class="sidebar-arrow">&#9662;</span>
    </div>
</li>

<!-- PRODUCT MENU -->
<li>
    <div class="menu-item collapsible" onclick="toggleSubmenu(this)">
        <img src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/Product.png" class="icon">
        <span>Product</span>
        <span class="sidebar-arrow">&#9662;</span>
    </div>
    <ul class="submenu">
        <li><a href="product-list.jsp">List</a></li>
        <li><a href="product-details.jsp">Details</a></li>
        <li><a href="add-product.jsp">Create</a></li>
        <li><a href="#">Edit</a></li>
    </ul>
</li>
			<li>
                <a href="#"><img src="${pageContext.request.contextPath}/resources/system/images/ProductPageLogo/Cart.png" class="icon"> Order</a>
            </li>
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

function toggleSubmenu(item) {
    item.classList.toggle("open");
    const submenu = item.nextElementSibling;
    if (submenu && submenu.classList.contains("submenu")) {
        submenu.classList.toggle("show");
    }
}
</script>

<script>
    function toggleSidebar() {
        const sidebar = document.querySelector('.admin-sidebar');
        sidebar.classList.toggle('active');
    }
</script>



</aside>