<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.nestandrest.model.ProductModel"%>
<%@ page import="com.nestandrest.model.ProductVariantModel"%>
<%@ page import="com.nestandrest.model.ProductVariantValueModel"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Product Details - Nest and Rest</title>
<jsp:include page="../head.jsp" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/product-details.css">
</head>
<body>
	<jsp:include page="../header.jsp" />
	<main class="product-details container">
		<%
		// Retrieve the product object from the request
		ProductModel product = (ProductModel) request.getAttribute("product");
		if (product != null) {
		%>
		
		<!-- Breadcrumb navigation for user orientation -->
		<nav class="breadcrumb">
			<a href="${pageContext.request.contextPath}/home">Home</a> &gt; <a
				href="${pageContext.request.contextPath}/products">Shop</a> &gt; <span><%=product.getName()%></span>
		</nav>

		<div class="product-top">
		
			<!-- Image carousel for the product -->
			<div class="carousel-container">
				<button class="arrow left" onclick="prevSlide()">&#10094;</button>
				
				<!-- Main product image -->
				<img id="mainImage" class="main-image"
					src="<%=request.getContextPath()%>/resources/product-images/<%=product.getProductId()%>.png"
					alt="<%=product.getName()%>">

				<button class="arrow right" onclick="nextSlide()">&#10095;</button>

				<!-- Thumbnails for additional images -->
				<div class="thumbnail-container">
					<%
					for (int i = 0; i < 5; i++) {
					%>
					<!-- repeat 5 thumbnails for now -->
					<img class="thumbnail"
						src="<%=request.getContextPath()%>/resources/product-images/<%=product.getProductId() + (i == 0 ? "" : "-" + (i + 1))%>.png"
						onclick="changeSlide(<%=i%>)">
					<%
					}
					%>
				</div>
			</div>


			<!-- Product info and purchase options -->
			<div class="product-info">
			
				<!-- SALE badge if discounted -->
				<c:if test="${product.getDiscountedPrice() != 0.0}">
					<span class="badge">SALE</span>
				</c:if>
				
				<!-- Product name -->
				<h1><%=product.getName()%></h1>
				
				<!-- Price: show both original and discounted if applicable -->
				<p class="price">
					<c:if test="${product.getDiscountedPrice() != 0.0}">
						<span class="original">Rs <%=product.getPrice()%></span>
						<span class="discounted">Rs <%=product.getDiscountedPrice()%></span>
					</c:if>
					<c:if test="${product.getDiscountedPrice() == 0.0}">
						<span class="discounted">Rs <%=product.getPrice()%></span>
					</c:if>

				</p>
				
				<!-- Short product description -->
				<p class="short-description"><%=product.getShortDescription()%></p>

				<!-- Out-of-stock message or Add to Cart form -->
				<c:if test="${product.getStockQty() == 0}">
					<p
						style="color: red; text-align: center; font-size: 24px; margin-top: 10px;">Out
						of stock!</p>
				</c:if>

				<c:if test="${product.getStockQty() != 0}">
					<!-- Product selection and quantity form -->
					<form class="product-options" action="product-details"
						method="post" style="margin-top: 20px">
						<!-- Passing the product ID as hidden input -->
						<input type="hidden" name="id" value="<%=product.getProductId()%>">
						
						<!-- Loop through product variants (e.g., Size, Color) -->
						<c:forEach var="variant" items="${productVariants}">
							<div class="option-row">
								<label for="variant-${variant.getProductVariantId()}">${variant.getVariantName()}</label>
								<select name="variant-${variant.getProductVariantId()}"
									id="variant-${variant.getProductVariantId()}">
									<c:forEach var="variantValue"
										items="${variant.getVariantValues()}">
										<option value="${variantValue.getProductVariantValueId()}">${variantValue.getVariantValue()}</option>
									</c:forEach>
								</select>
							</div>
						</c:forEach>


						<!-- Quantity input with buttons -->
						<div class="option-row">
							<label for="quantity">Quantity</label>
							<div class="quantity-control">
								<button type="button" onclick="decreaseQuantity()">-</button>
								<input type="number" name="quantity" value="1" min="1"
									max="${product.getStockQty()}">
								<button type="button" onclick="increaseQuantity()">+</button>
							</div>
						</div>
						
						<input type="hidden" name="product_id" value="${product.getProductId()}" />

						<!-- Add to Cart button -->
						<button type="submit" class="add-to-cart">
							<span class="cart-icon"></span> Add to cart
						</button>
					</form>
				</c:if>

			</div>
		</div>
		
		<!-- Product service highlights section -->
		<div class="highlights">
			<div class="highlight">
				<img
					src="<%=request.getContextPath()%>/resources/system/images/ProductPageLogo/Authentic.png"
					alt="">
				<p>
					100% Original<br> <small>Authentic Nepalese
						craftsmanship</small>
				</p>
			</div>
			<div class="highlight">
				<img
					src="<%=request.getContextPath()%>/resources/system/images/ProductPageLogo/Replacement.png"
					alt="">
				<p>
					10 Day Replacement<br> <small>Full replacement within
						10 days</small>
				</p>
			</div>
			<div class="highlight">
				<img
					src="<%=request.getContextPath()%>/resources/system/images/ProductPageLogo/Warranty.png"
					alt="">
				<p>
					1 Year Warranty<br> <small>365-day quality guarantee</small>
				</p>
			</div>
		</div>

		<!-- Description Tab Section -->
		<div class="tabs">
			<button class="active">Description</button>
		</div>

		<div class="tab-content">
			<h6>Product details</h6>
			<p>
				<%=product.getLongDescription()%>
			</p>
		</div>

		<!-- JavaScript for carousel and quantity control -->
		<script>
		    const images = [
		        "<%=request.getContextPath()%>/resources/product-images/<%=product.getProductId()%>.png",
		        "<%=request.getContextPath()%>/resources/product-images/<%=product.getProductId()%>-2.png",
		        "<%=request.getContextPath()%>/resources/product-images/<%=product.getProductId()%>-3.png",
		        "<%=request.getContextPath()%>/resources/product-images/<%=product.getProductId()%>-4.png",
		        "<%=request.getContextPath()%>/resources/product-images/<%=product.getProductId()%>-5.png"
		    ];

		    let currentIndex = 0;
		
		    function updateImage() {
		        const mainImage = document.getElementById('mainImage');
		        mainImage.src = images[currentIndex];
		
		        document.querySelectorAll('.thumbnail').forEach((thumb, index) => {
		            thumb.classList.toggle('selected', index === currentIndex);
		        });
		    }
		
		    function changeSlide(index) {
		        currentIndex = index;
		        updateImage();
		    }
		
		    function prevSlide() {
		        currentIndex = (currentIndex - 1 + images.length) % images.length;
		        updateImage();
		    }
		
		    function nextSlide() {
		        currentIndex = (currentIndex + 1) % images.length;
		        updateImage();
		    }
		
		    // Initialize
		    window.onload = updateImage;
		    
		    function increaseQuantity() {
		        const qtyInput = document.querySelector('input[name="quantity"]');
		        qtyInput.value = Math.min(parseInt(qtyInput.value) + 1, ${product.getStockQty()});
		    }
		
		    function decreaseQuantity() {
		        const qtyInput = document.querySelector('input[name="quantity"]');
		        if (qtyInput.value > 1) {
		            qtyInput.value = parseInt(qtyInput.value) - 1;
		        }
		    }
		</script>


		<%
		} else {
		%>
		<!-- Fallback for null product -->
		<p style="color: red;">Product not found.</p>
		<%
		}
		%>
		<jsp:include page="../footer.jsp" />
	</main>
</body>
</html>
