<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nest and Rest</title>
<jsp:include page="head.jsp" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/home.css" />
</head>
<body>
	<jsp:include page="header.jsp" />



	<!-- Interactive Hero Section -->
	<section class="interactive-room-container">
		<div class="image-map-wrapper">
			<img
				src="${pageContext.request.contextPath}/resources/system/images/interactive-room.png"
				alt="Interactive Room" class="base-image">

			<!-- Mouse tracking tooltip showing the name of the furniture -->
			<div id="furniture-info-div"></div>

			<!-- Text content -->
			<div class="interactive-room-container-text">
				<h3 style="font-weight: 600" class="title">Like what you see?</h3>
				<h4 style="font-weight: 500; margin-bottom: 10px;">Click on a
					furniture to explore more</h4>
				<svg width="26" height="35" viewBox="0 0 26 35" fill="#88734D"
					xmlns="http://www.w3.org/2000/svg">
					<path
						d="M0 13.125H11.9167V0H10.8333C4.84792 0 0 4.89453 0 10.9375V13.125ZM0 15.3125V24.0625C0 30.1055 4.84792 35 10.8333 35H15.1667C21.1521 35 26 30.1055 26 24.0625V15.3125H13H0ZM26 13.125V10.9375C26 4.89453 21.1521 0 15.1667 0H14.0833V13.125H26Z"
						fill="currentColor" />
				</svg>
				<a style="margin-top: 24px;"
					href="${pageContext.request.contextPath}/products"><button
						class="btn">View Shop</button></a>

			</div>

			<svg class="image-map-svg" viewBox="0 0 2038 1009"
				preserveAspectRatio="xMidYMid meet">
			      <!-- Stools -->
			      <a
					href="${pageContext.request.contextPath}/products?category=<c:forEach var="category" items="${allCategories}"><c:if test="${category.name == 'Stools'}"><c:out value="${category.categoryId}"/></c:if></c:forEach>"
					title="Stools" data-tooltip="Stools">
        			<path
						d="M95,993 L118,1006 L149,1009 L178,1006 L209,1002 L239,991 L259,984 L276,973 L281,948 L279,794 L247,785 L241,770 L187,765 L111,781 L106,796 L89,810 Z"
						class="clickable-area" />
      				</a>

      			<!-- Tables -->
      			<a
					href="${pageContext.request.contextPath}/products?category=<c:forEach var="category" items="${allCategories}"><c:if test="${category.name == 'Tables'}"><c:out value="${category.categoryId}"/></c:if></c:forEach>"
					title="Tables" data-tooltip="Tables">
        			<path
						d="M793,879 L793,771 L770,763 L768,742 L777,731 L824,725 L846,718 L844,680 L867,673 L866,700 L895,698 L907,718 L938,720 L969,720 L988,729 L1010,740 L1009,760 L987,769 L987,857 L970,855 L967,772 L945,776 L943,875 L925,886 L927,792 L909,778 L860,778 L858,846 L842,846 L844,779 L835,776 L813,787 L811,877 Z"
						class="clickable-area" data-category="tables" />
      			</a>

      			<!-- Chairs -->
      			<a
					href="${pageContext.request.contextPath}/products?category=<c:forEach var="category" items="${allCategories}"><c:if test="${category.name == 'Chairs'}"><c:out value="${category.categoryId}"/></c:if></c:forEach>"
					title="Chairs" data-tooltip="Chairs">
        			<path
						d="M1314,801 L1312,658 L1327,651 L1434,649 L1455,593 L1468,588 L1472,566 L1488,570 L1596,575 L1607,575 L1598,658 L1611,664 L1605,680 L1614,821 L1595,817 L1595,779 L1472,781 L1468,837 L1450,839 L1450,787 L1329,758 L1327,799 Z"
						class="clickable-area" data-category="chairs" />
     			 </a>

      			 <!-- Hangers -->
     			 <a
					href="${pageContext.request.contextPath}/products?category=<c:forEach var="category" items="${allCategories}"><c:if test="${category.name == 'Hangers'}"><c:out value="${category.categoryId}"/></c:if></c:forEach>"
					title="Hangers" data-tooltip="Hangers">
        			<path
						d="M1714,273 L2020,181 L2038,181 L2032,195 L2018,203 L1926,228 L1929,253 L1958,282 L1971,308 L1984,360 L1982,405 L1969,443 L1947,543 L1942,555 L1913,550 L1893,543 L1891,622 L1832,622 L1841,526 L1828,539 L1794,525 L1803,478 L1788,470 L1785,405 L1728,405 L1719,352 L1737,341 L1743,285 L1716,290 Z"
						class="clickable-area" data-category="hangers" />
     				 </a>

      			<!-- Sofas (first area) -->
      			<a
					href="${pageContext.request.contextPath}/products?category=<c:forEach var="category" items="${allCategories}"><c:if test="${category.name == 'Sofas'}"><c:out value="${category.categoryId}"/></c:if></c:forEach>"
					title="Sofas" data-tooltip="Sofas">
       				 <path
						d="M120,781 L118,662 L136,653 L167,649 L162,613 L196,590 L211,591 L259,590 L296,590 L321,582 L353,573 L375,571 L406,568 L428,571 L442,571 L466,566 L487,562 L498,573 L511,591 L522,586 L534,586 L569,642 L601,640 L630,638 L686,642 L686,685 L692,707 L685,725 L688,747 L686,781 L686,798 L670,798 L672,758 L609,796 L603,848 L603,859 L587,861 L585,819 L491,874 L484,942 L464,951 L457,884 L285,881 L279,796 L249,787 L247,770 L185,765 Z"
						class="clickable-area" data-category="sofas" />
      			</a>

      			 <!-- Sofas (second area) -->
     			 <a
					href="${pageContext.request.contextPath}/products?category=<c:forEach var="category" items="${allCategories}"><c:if test="${category.name == 'Sofas'}"><c:out value="${category.categoryId}"/></c:if></c:forEach>"
					title="Sofas" data-tooltip="Sofas">
       				 <path
						d="M707,787 L709,644 L740,628 L742,603 L754,595 L758,557 L782,557 L829,557 L877,556 L928,556 L973,554 L993,561 L1001,556 L1022,554 L1055,556 L1098,557 L1142,552 L1180,554 L1210,554 L1228,554 L1232,595 L1243,595 L1247,615 L1256,639 L1259,664 L1259,789 L1241,793 L1241,746 L1015,747 L976,723 L909,717 L898,696 L871,697 L867,670 L838,678 L836,719 L784,726 L768,732 L761,746 L724,743 L726,788 Z"
						class="clickable-area" data-category="sofas" />
     			 </a>
   			 </svg>

			<!-- Tooltip container (positioned absolutely) -->
			<div class="tooltip-container"></div>
		</div>
	</section>

	<!-- Mobile Hero -->
	<section class="mobile-hero">
		<img
			src="${pageContext.request.contextPath}/resources/system/images/interactive-room.png"
			alt="Interactive Room" class="base-image">
		<div class="mobile-hero-contents">
			<h3 style="color: white">Nest and Rest</h3>
			<h5 style="color: white">Bringing Nepal's sustainable
				craftsmanship to your home.</h5>
			<a style="margin-top: 16px; text-align: center"
				href="${pageContext.request.contextPath}/products"><button
					class="btn btn-lg">View Shop</button></a>
		</div>
	</section>

	<div class="container contents-container">
		<!-- Top Categories -->
		<section class="list-container">
			<h4>Shop Our Top Categories</h4>
			<div class="list-items">
				<c:forEach var="category" items="${allCategories}">
					<a
						href="${pageContext.request.contextPath}/products?category=${category.categoryId}">
						<div class="item-card">
							<div class="item-img">
								<img
									src="${pageContext.request.contextPath}/resources/category-images/${category.categoryId}.png" />
							</div>
							<div class="item-info">
								<p class="subtitle1">${category.name}</p>
							</div>
						</div>
					</a>
				</c:forEach>
			</div>
		</section>

		<!-- Products on Sale! -->
		<section class="list-container">
			<h4>Products on Sale!</h4>
			<div class="list-items">
				<c:forEach var="product" items="${productsWithDiscounts}">
					<jsp:include page="./product-card.jsp">
						<jsp:param name="id" value="${product.productId}" />
						<jsp:param name="name" value="${product.name}" />
						<jsp:param name="discountedPrice"
							value="${'' + product.discountedPrice}" />
						<jsp:param name="price" value="${'' + product.price}" />
					</jsp:include>
				</c:forEach>
			</div>
		</section>

		<!-- Products by Category -->
		<section class="list-container">
			<h4>Products By Category</h4>

			<c:forEach var="category" items="${allCategories}">
				<div class="category-listing">
					<h5>${category.name}</h5>
					<div class="list-items">
						<c:forEach var="product"
							items="${categorizedProducts[category.categoryId]}">
							<jsp:include page="./product-card.jsp">
								<jsp:param name="id" value="${product.productId}" />
								<jsp:param name="name" value="${product.name}" />
								<jsp:param name="discountedPrice"
									value="${'' + product.discountedPrice}" />
								<jsp:param name="price" value="${'' + product.price}" />
							</jsp:include>
						</c:forEach>
					</div>
				</div>
			</c:forEach>
		</section>
	</div>

	<!-- About -->
	<section class="about">
		<div class="container">
			<div class="images">
				<div>
					<img
						src="${pageContext.request.contextPath}/resources/system/images/about-img1.jpg"
						alt="" />
				</div>
				<div>
					<img
						src="${pageContext.request.contextPath}/resources/system/images/about-img2.jpg"
						alt="" />
				</div>
			</div>
			<div class="info">
				<h2>What is Rest and Nest?</h2>
				<p class="body1" style="margin-top: 24px; margin-bottom: 40px">
					Our company brings the timeless tradition of Nepalese bamboo and
					cane craftsmanship to your home. We work directly with skilled
					local artisans who have perfected their craft through generations,
					creating sustainable, eco-friendly furniture pieces that blend
					traditional techniques with contemporary design. Each piece tells a
					story of Nepal's rich cultural heritage while providing functional
					elegance for modern living spaces.</p>
				<div class="our-work-btn">
					<a href="${pageContext.request.contextPath}/products"><button
							class="btn btn-outlined" style="width: fit-content">
							Our work
							<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
								viewBox="0 0 24 24" fill="none">
                <path
									d="M9.99999 18.9999C9.76634 19.0004 9.53991 18.919 9.35999 18.7699C9.15549 18.6004 9.02685 18.3564 9.00246 18.0919C8.97807 17.8274 9.05993 17.564 9.22999 17.3599L13.71 11.9999L9.38999 6.62994C9.22222 6.42335 9.14372 6.1584 9.17188 5.89376C9.20003 5.62912 9.33251 5.38662 9.53999 5.21994C9.74916 5.03591 10.0256 4.94747 10.3028 4.97594C10.5799 5.00442 10.8326 5.14722 11 5.36994L15.83 11.3699C16.1333 11.7389 16.1333 12.2709 15.83 12.6399L10.83 18.6399C10.6265 18.8854 10.3182 19.0191 9.99999 18.9999Z"
									fill="#1C252E" />
              </svg>
						</button></a>
				</div>
			</div>
		</div>
	</section>

	<jsp:include page="footer.jsp" />

	<script>
        // Get all anchor tags
        const links = document.querySelectorAll('.interactive-room-container .image-map-svg a');
        const infoDiv = document.getElementById('furniture-info-div');
        
        // Add event listeners to each anchor
        links.forEach(link => {
            // When mouse enters the anchor
            link.addEventListener('mouseenter', function() {
                // Get the title attribute
                const titleText = this.getAttribute('title');
                
                // Set the text of the info div
                infoDiv.textContent = titleText;
                
                // Show the info div
                infoDiv.style.display = 'block';
                
                // Position the info div near the link (optional)
                const rect = this.getBoundingClientRect();
                infoDiv.style.left = rect.left + 'px';
                infoDiv.style.top = rect.top + 'px';
            });
            
            // When mouse leaves the anchor
            link.addEventListener('mouseleave', function() {
                // Hide the info div
                infoDiv.style.display = 'none';
            });
        });
    </script>
</body>
</html>