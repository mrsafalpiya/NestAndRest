<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.nestandrest.model.Product" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product Details - Nest and Rest</title>
    <jsp:include page="../head.jsp" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product-details.css">
</head>
<body>
<jsp:include page="../header.jsp" />
<main class="product-details container">
<%
    Product product = (Product) request.getAttribute("product");
    if (product != null) {
%>
		<nav class="breadcrumb">
			<a href="${pageContext.request.contextPath}/index.jsp">Home</a> &gt;
			<a href="${pageContext.request.contextPath}/products">Shop</a> &gt; <span>${product.name}</span>
		</nav>

		<div class="product-top">
			<div class="carousel-container">
				<button class="arrow left" onclick="prevSlide()">&#10094;</button>

				<img id="mainImage" class="main-image"
					src="<%=request.getContextPath()%>/resources/system/images/<%=product.getImage()%>"
					alt="<%=product.getName()%>">

				<button class="arrow right" onclick="nextSlide()">&#10095;</button>

				<div class="thumbnail-container">
					<%
					for (int i = 0; i < 5; i++) {
					%>
					<!-- repeat 5 thumbnails for now -->
					<img class="thumbnail"
						src="<%=request.getContextPath()%>/resources/system/images/<%=product.getImage()%>"
						onclick="changeSlide(<%=i%>)">
					<%
					}
					%>
				</div>
			</div>



			<div class="product-info">
            <span class="badge">SALE</span>
            <h1><%= product.getName() %></h1>
            <p class="rating">
                ★★★★★ <span>(20 reviews)</span>
            </p>
            <p class="price">
                <span class="original">Rs. <%= product.getPrice() + 1500 %></span>
                <span class="discounted">Rs. <%= product.getPrice() %></span>
            </p>
            <p class="short-description">
                The simple style sofa is a great choice to relax and enjoy a nice day, take a nap or chat with your family or friends. The armchair, with an elegant design, fits perfectly into any living room decor.
            </p>

            <form class="product-options">
                <label>Size</label>
                <select name="size">
                    <option>10.5</option>
                    <option>11</option>
                    <option>12</option>
                </select>

                <label>Quantity</label>
                <div class="quantity-control">
                    <button type="button">-</button>
                    <input type="number" name="quantity" value="1" min="1">
                    <button type="button">+</button>
                </div>

                <button class="add-to-cart">Add to cart</button>
            </form>
        </div>
    </div>

    <div class="highlights">
        <div class="highlight">
            <img src="<%= request.getContextPath() %>/resources/system/icons/original.svg" alt="">
            <p>100% Original<br><small>Authentic Nepalese craftsmanship</small></p>
        </div>
        <div class="highlight">
            <img src="<%= request.getContextPath() %>/resources/system/icons/replacement.svg" alt="">
            <p>10 Day Replacement<br><small>Full replacement within 10 days</small></p>
        </div>
        <div class="highlight">
            <img src="<%= request.getContextPath() %>/resources/system/icons/warranty.svg" alt="">
            <p>1 Year Warranty<br><small>365-day quality guarantee</small></p>
        </div>
    </div>

    <div class="tabs">
        <button class="active">Description</button>
        <button>Reviews (20)</button>
    </div>

    <div class="tab-content">
        <h6>Product details</h6>
        <p>
            Experience the perfect blend of traditional craftsmanship and modern comfort with our exquisite <%= product.getName() %>. Handcrafted by skilled Nepalese artisans using sustainable bamboo and premium cane weaving techniques perfected over generations, this elegantly designed piece transforms any living space into a sanctuary of relaxation and style.
            <br><br>
            The sturdy bamboo frame provides exceptional durability while maintaining a lightweight aesthetic that brings a touch of nature indoors. Its intricate cane weaving showcases the dedication and talent of our artisans, resulting in furniture that is not only beautiful but also environmentally conscious.
            <br><br>
            Perfect for living rooms, patios, or reading nooks, this item is a testament to timeless design. Add the Gecheer Sofa with Cushion to your home today and enjoy comfort, durability, and the warm elegance of Nepalese heritage.
        </p>
    </div>

		<script>
    const images = [
        "<%= request.getContextPath() %>/resources/system/images/<%= product.getImage() %>",
        "<%= request.getContextPath() %>/resources/system/images/<%= product.getImage() %>",
        "<%= request.getContextPath() %>/resources/system/images/<%= product.getImage() %>",
        "<%= request.getContextPath() %>/resources/system/images/<%= product.getImage() %>",
        "<%= request.getContextPath() %>/resources/system/images/<%= product.getImage() %>"
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
</script>


<%
    } else {
%>
    <p style="color:red;">Product not found.</p>
<%
    }
%>
<jsp:include page="../footer.jsp" />
</main>
</body>
</html>
