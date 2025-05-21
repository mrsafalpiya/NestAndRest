<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Admin - Dashboard</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="head.jsp" />
<link rel="stylesheet" href="${contextPath}/css/admin-style.css">
<link rel="stylesheet" href="${contextPath}/css/admin-dashboard.css">
<script
	src="https://cdn.jsdelivr.net/npm/chart.js@4.4.9/dist/chart.umd.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>


</script>
</head>
<body>
	<div class="admin-layout">
		<%-- Header (top right admin icon) --%>
		<jsp:include page="admin-header.jsp" />
		<%-- Sidebar --%>
		<jsp:include page="admin-sidebar.jsp" />

		<%-- Main Dashboard Content --%>
		<main class="container content-container">
			<!-- Product sold and Total balance charts -->
			<div class="product-sold-total-balance-contrainer">
				<div class="card products-sold">
					<div class="products-sold-content-div">
						<p class="subtitle2" style="color: var(--text-primary);">Total
							Products</p>
						<h3 style="margin: 12px 0">${total_products}</h3>
					</div>
				</div>
				<div class="card products-sold">
					<div class="products-sold-content-div">
						<p class="subtitle2" style="color: var(--text-primary);">Products
							Sold</p>
						<h3 style="margin: 12px 0">${products_sold}</h3>
					</div>
				</div>
				<div class="card products-sold">
					<div class="products-sold-content-div">
						<p class="subtitle2" style="color: var(--text-primary);">Total
							Sales</p>
						<h3 style="margin: 12px 0">Rs ${total_sales}</h3>
					</div>
				</div>
			</div>

			<div class="sales-gender-yearly-container">
				<div class="sales-by-gender card">
					<div class="header">
						<h6>Sale by Gender</h6>
						<div id="saleByGender"></div>
					</div>
				</div>
				<div class="sales-by-gender yearly-sales card">
					<div class="header">
						<h6>Yearly Sales</h6>
					</div>
					<canvas id="yearlySales"></canvas>
				</div>
			</div>

			<!-- Best Products -->
			<div class="best-products card">
				<table class="best-products-table">
					<tr>
						<th class="max">Product</th>
						<th>Price</th>
						<th>Sales</th>
						<th>Rank</th>
					</tr>
					<c:forEach var="product" items="${top_5_products}"
						varStatus="status">
						<tr
							onclick="window.location='${contextPath}/product-details?id=${product.productId}';"
							style="cursor: pointer;">
							<td>
								<div class="product-info">
									<img
										src="${contextPath}/resources/product-images/${product.productId}.png"
										width="40" height="40" />
									<div>
										<p>${product.name}</p>
									</div>
								</div>
							</td>
							<td>
								<div>
									<p>Rs ${product.salePrice}</p>
								</div>
							</td>
							<td>${product.cartQty}</td>
							<td><span class="chip top${status.index + 1}">Top
									${status.index + 1}</span></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</main>
	</div>

	<script>
	
		// Sale by gender graph
		var options = {
			series : [
				<fmt:formatNumber value="${sales_by_gender.get('Male') / products_sold * 100}" type="number" maxFractionDigits="2" minFractionDigits="2"/>,
				<fmt:formatNumber value="${sales_by_gender.get('Female') / products_sold * 100}" type="number" maxFractionDigits="2" minFractionDigits="2"/>,
			],
			colors : [ '#CDAD82', '#FCCF63' ],
			legend : {
				show : true,
				position : 'bottom',
			},
			chart : {
				height : 350,
				type : 'radialBar',
			},
			plotOptions : {
				radialBar : {
					dataLabels : {
						name : {
							fontSize : '14px',
							fontWeight : 600,
							color : '#637381',
						},
						value : {
							fontSize : '20px',
							fontWeight : 700,
						},
						total : {
							show : true,
							label : 'Total',
							formatter : function(w) {
								return ${products_sold};
							}
						}
					}
				}
			},
			labels : [ 'Mens', 'Womens' ],
		};

		var chart = new ApexCharts(document.querySelector("#saleByGender"),
				options);
		chart.render();

		// Yearly sales graph
		const yearlySalesXValues = [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
				'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ];
		new Chart("yearlySales", {
			type : "line",
			data : {
				labels : yearlySalesXValues,
				datasets : [ {
					label : 'Total sale',
					data : [ ${yearly_sales[0]}, ${yearly_sales[1]}, ${yearly_sales[2]}, ${yearly_sales[3]}, ${yearly_sales[4]}, ${yearly_sales[5]}, ${yearly_sales[6]}, ${yearly_sales[7]}, ${yearly_sales[8]}, ${yearly_sales[9]}, ${yearly_sales[10]}, ${yearly_sales[11]} ],
					borderColor : "#FEAA31",
					fill : true,
					pointStyle : false,
					cubicInterpolationMode : 'monotone',
					tension : 0.4
				} ],
			}
		});
	</script>
</body>
</html>
