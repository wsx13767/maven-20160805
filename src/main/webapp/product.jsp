<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<META charset="utf-8">
<title>爽爽訂，訂餐真方便</title>
	<%@ include file="includeFile/top.inc"%>
	<%@ include file="includeFile/nowUser.inc"%>
</head>

<body>
	<%--
		ItemInformationDAO iInD=new ItemInformationDAO();
		List<ItemInformation> itemList = iInD.findAll();
		BuildOrderDAO buDAO=new BuildOrderDAO();
		List<BuildOrder> bulist = buDAO.findAll();
		JsonDAO jsd=new JsonDAO();
		Object itemJson=jsd.getItemInformationJSON(itemList);
		String buJson=jsd.getBUILDORDERJSON (bulist);
	--%>
	<script src="js/product.js"></script>

	<%if(nowUser!=null){%>
		<%@ include file="includeFile/header.inc"%>
	<%
	} else {
	%>
		<%@ include file="includeFile/headerNotLog.inc"%>
	<%}%>
	<div class="container">

		<%@ include file="includeFile/selectdan.inc"%>


		<div class="all_item_grid">
			<div class="all_item_list">
				<div class="sort-mode">
					<div class="j_sort_nav">
						<ul class="sort-mode-list">
							<li><span class="sort-mode-title">排序：</span></li>
							<li class="sort-mode-content"><a class="buyMoney " href="/G/product.jsp?sortBy=MONEY&odM=DESC"
								title="直購價">購買價</a></li>
							<li class="sort-mode-content"><a class="buyCount " href="/G/product.jsp?sortBy=COUNT&odC=DESC"
								title="已賣數量">已賣數量</a></li>
							<li class="sort-mode-content"><a class="buyDate " href="/G/product.jsp?sortBy=DATE&odD=DESC"
								title="最新刊登">最新刊登</a></li>
							<li class="sort-mode-content" style="margin-left: 15px;">
							類別:
								<select name="forma" style="position: relative;top:0px;">
									<option value="ALL">不限</option>
									 <option value="DRINK">飲料</option>
									 <option value="FOOD">食物</option>
								</select>
							</li>
							<li style="position: relative;right:15%;" class="sort-mode-select">
								每頁訂單筆數:
								 <select name="limit" id="">
									<option value="10">10</option>
									<option value="20">20</option>
									<option value="30">30</option>
								</select>		
							</li>
						</ul>
					</div>
					<ul class="items-mix-title">
						<li class="item_photo">團購商品照片</li>
						<li class="item_price">價格區間</li>
						<li class="item_DM">詳細資訊</li>
						<li class="bid_total">已賣數量</li>
						<li class="date_time">發起時間</li>
					</ul>
					<div class="addPTab"></div>
				</div>
				<div id="foot_pagination" class="rt-pagination">
					<ul class="page-link-list">
						<li class="item_prev_page current"><a href="#"
							rt-go-page="prev"><上一頁</a></li>
						<!-- <li class="page-to active"><a href="#" rt-go-page="to-0">1</a></li>-->
						<li style="margin-left:5px;" class="item_next_page"><a href="#" rt-go-page="next">下一頁></a></li>
					</ul>
					<div class="page-info" style="margin: 0px auto;">
						<span class="page-info-num"></span> <span
							class="page-info-slash"></span> <span class="page-info-pagenum"></span>
						<span class="page-info-total"></span>
					</div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>

		<div class="clearfix"></div>
	</div>
	<!---->
	<%@ include file="includeFile/footer.inc"%>
</body>

</html>
