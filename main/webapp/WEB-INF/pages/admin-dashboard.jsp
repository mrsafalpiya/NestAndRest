<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Admin - Dashboard</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="head.jsp" />
<link rel="stylesheet"
	href="${contextPath}/css/admin-style.css">
<link rel="stylesheet"
	href="${contextPath}/css/admin-dashboard.css">
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
						<p class="subtitle2" style="color: var(--text-primary);">Product
							Sold</p>
						<h3 style="margin: 12px 0">${total_sales}</h3>
						<div class="last-week-comp-stats">
							<svg width="24" height="24" viewBox="0 0 24 24" fill="none"
								xmlns="http://www.w3.org/2000/svg">
								<path
									d="M0 12C0 5.37258 5.37258 0 12 0C18.6274 0 24 5.37258 24 12C24 18.6274 18.6274 24 12 24C5.37258 24 0 18.6274 0 12Z"
									fill="#22C55E" fill-opacity="0.16" />
								<path
									d="M18 8.66667C18.0063 8.62021 18.0063 8.57312 18 8.52667C17.9942 8.48753 17.983 8.44938 17.9667 8.41333C17.949 8.38075 17.929 8.34955 17.9067 8.32C17.8813 8.27784 17.8498 8.23965 17.8133 8.20667L17.7333 8.16C17.6948 8.1313 17.6521 8.10881 17.6067 8.09333H17.4733C17.4327 8.054 17.3853 8.02237 17.3333 8H14C13.6318 8 13.3333 8.29848 13.3333 8.66667C13.3333 9.03486 13.6318 9.33333 14 9.33333H15.8867L13.22 12.4733L10.34 10.76C10.0582 10.5924 9.69633 10.6546 9.48665 10.9067L6.15332 14.9067C6.03995 15.0427 5.98537 15.2183 6.00163 15.3946C6.01789 15.571 6.10365 15.7336 6.23999 15.8467C6.35993 15.9461 6.51088 16.0003 6.66665 16C6.86494 16.0003 7.05308 15.9124 7.17999 15.76L10.1467 12.2L12.9933 13.9067C13.2721 14.072 13.6293 14.0129 13.84 13.7667L16.6667 10.4667V12C16.6667 12.3682 16.9651 12.6667 17.3333 12.6667C17.7015 12.6667 18 12.3682 18 12V8.66667Z"
									fill="#118D57" />
							</svg>
							<p class="subtitle2" style="color: var(--text-primary)">8.2%</p>
							<p class="body2">last week</p>
						</div>
					</div>
					<canvas id="productSoldGraph"
						style="width: 100%; max-width: 140px; height: 100%; max-height: 70px;"></canvas>
				</div>
				<div class="card products-sold">
					<div class="products-sold-content-div">
						<p class="subtitle2" style="color: var(--text-primary);">Total
							Balance</p>
						<h3 style="margin: 12px 0">10,989</h3>
						<div class="last-week-comp-stats">
							<svg width="16" height="16" viewBox="0 0 16 16" fill="none"
								xmlns="http://www.w3.org/2000/svg">
								<path
									d="M14.0002 7.99999C14.0002 7.6318 13.7017 7.33333 13.3335 7.33333C12.9653 7.33333 12.6668 7.6318 12.6668 7.99999V9.53333L9.84017 6.19999C9.62949 5.95374 9.27224 5.89467 8.9935 6.05999L6.14684 7.79999L3.18017 4.23999C3.02774 4.0566 2.78904 3.96845 2.554 4.00877C2.31896 4.04908 2.12328 4.21172 2.04067 4.43543C1.95806 4.65914 2.00107 4.90993 2.1535 5.09333L5.48684 9.09333C5.69651 9.34541 6.05836 9.4076 6.34017 9.23999L9.1935 7.52666L11.8602 10.6667H10.0002C9.63198 10.6667 9.3335 10.9651 9.3335 11.3333C9.3335 11.7015 9.63198 12 10.0002 12H13.3335C13.4156 11.998 13.4967 11.9822 13.5735 11.9533L13.6668 11.9C13.7016 11.8825 13.735 11.8624 13.7668 11.84C13.8034 11.807 13.8348 11.7688 13.8602 11.7267C13.8825 11.6971 13.9026 11.6659 13.9202 11.6333C13.9365 11.5973 13.9477 11.5591 13.9535 11.52C13.9805 11.4612 13.9964 11.3979 14.0002 11.3333V7.99999Z"
									fill="#B71D18" />
							</svg>
							<p class="subtitle2" style="color: var(--text-primary)">86.6%</p>
							<p class="body2">last week</p>
						</div>
					</div>
					<canvas id="totalBalanceChart"
						style="width: 100%; max-width: 140px; height: 100%; max-height: 70px;"></canvas>
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
					<tr>
						<td>
							<div class="product-info">
								<img
									src="https://img.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg"
									width="40" height="40" />
								<div>
									<p>Product Name</p>
								</div>
							</div>
						</td>
						<td>
							<div>
								<p>Rs 15,000</p>
							</div>
						</td>
						<td>100</td>
						<td><span class="chip top1">Top 1</span></td>
					</tr>
					<tr>
						<td>
							<div class="product-info">
								<img
									src="https://img.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg"
									width="40" height="40" />
								<div>
									<p>Product Name</p>
								</div>
							</div>
						</td>
						<td>
							<div>
								<p>Rs 15,000</p>
							</div>
						</td>
						<td>100</td>
						<td><span class="chip top2">Top 2</span></td>
					</tr>
					<tr>
						<td>
							<div class="product-info">
								<img
									src="https://img.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg"
									width="40" height="40" />
								<div>
									<p>Product Name</p>
								</div>
							</div>
						</td>
						<td>
							<div>
								<p>Rs 15,000</p>
							</div>
						</td>
						<td>100</td>
						<td><span class="chip top3">Top 3</span></td>
					</tr>
					<tr>
						<td>
							<div class="product-info">
								<img
									src="https://img.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg"
									width="40" height="40" />
								<div>
									<p>Product Name</p>
								</div>
							</div>
						</td>
						<td>
							<div>
								<p>Rs 15,000</p>
							</div>
						</td>
						<td>100</td>
						<td><span class="chip top4">Top 4</span></td>
					</tr>
					<tr>
						<td>
							<div class="product-info">
								<img
									src="https://img.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg"
									width="40" height="40" />
								<div>
									<p>Product Name</p>
								</div>
							</div>
						</td>
						<td>
							<div>
								<p>Rs 15,000</p>
							</div>
						</td>
						<td>100</td>
						<td><span class="chip top5">Top 5</span></td>
					</tr>
				</table>
			</div>
		</main>
	</div>

	<script>
		// Products sold line graph
		const productsSoldXValues = [ "2024-04-20", "2024-04-21", "2024-04-22" ];
		new Chart("productSoldGraph", {
			type : "line",
			data : {
				labels : productsSoldXValues,
				datasets : [ {
					data : [ 16, 16, 16, 16, 16, 16 ],
					borderColor : "#C9A172",
					fill : false,
					pointStyle : false,
					cubicInterpolationMode : 'monotone',
					tension : 0.4
				} ]
			},
			options : {
				legend : {
					display : false
				},
				scales : {
					x : {
						display : false,
					},
					y : {
						display : false,
					}
				},
				plugins : {
					legend : {
						display : false
					},
				}
			}
		});

		// Total balance line graph
		const totalBalanceXValues = [ 100, 200, 300, 400, 500, 600, 700, 800,
				900, 1000 ];
		new Chart("totalBalanceChart", {
			type : "line",
			data : {
				labels : totalBalanceXValues,
				datasets : [ {
					data : [ 10, 13, 15, 40, 34, 50, 37 ],
					borderColor : "#FED565",
					fill : false,
					pointStyle : false,
					cubicInterpolationMode : 'monotone',
					tension : 0.4
				} ]
			},
			options : {
				legend : {
					display : false
				},
				scales : {
					x : {
						display : false,
					},
					y : {
						display : false,
					}
				},
				plugins : {
					legend : {
						display : false
					},
				}
			}
		});

		// Sale by gender graph
		var options = {
			series : [ 35, 50 ],
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
								return '9,990';
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
					data : [ 10, 13, 15, 40, 34, 50, 37 ],
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
