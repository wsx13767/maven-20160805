<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>爽爽訂-發起團購</title>
	<%@ include file="includeFile/top.inc" %>
	<%@ include file="includeFile/nowUser.inc"%>
	<%@ include file="includeFile/testLog.inc"%>
</head>
<body> 
<div id="background_dialog">
	<%if(nowUser!=null){%>
		<%@ include file="includeFile/header.inc"%>
	<%
	} else {
	%>
		<%@ include file="includeFile/headerNotLog.inc"%>
	<%}%>
<!--container-->

	<!-- 建立訂單頁面 -->
	<div class="container">
<%@ include file="includeFile/selectdan.inc" %>
		<div class="main"> 
         	<div class="reservation_top">          
            	<div class=" contact_right">
          			<h3>發起團購</h3>
            		<div>
            			<a href="#" class="accChooseQQ" id="buildBtn_toggle">新增</a>
            			<a href="#" class="accChooseQQ" id="search_Btn">搜尋</a>
					</div>
            	</div>
           </div>
        </div>
    	<!--左列選項 -->
		
		<div class="clearfix"> </div>
	</div>
<!--initiate accordion-->


	<!--下半部copyright-->
	<%@ include file="includeFile/footer.inc" %>
</div> 


	<!-- 查詢飲料食物表單 -->
	<form id="search_form" method="post" action="./SearchOrder.jsp"></form>
	<form id="search_drink_form" method="post" action="./searchOrderDrink.jsp"></form>
	<div id="search_food_drink" style="position: fixed;top:0%;left:0%;width: 100%;height: 100%;display: none;">
		<img src="images/backgroundimg.png" class="black_mask">
		<div class="container" style="position:fixed;top: 20%;left:25%;width: 50%;height: 60%;border-style: solid;background-image: url('images/123.jpg');background-position: -5%;overflow: auto;">
			<p style="position: absolute; top: 30%; left: 26%;">
				<input type="text" class="search_food" placeholder="食品" style="width: 120px;height: 40px;"name="searchFoodQQOO" id="searchFoodID_json" form="search_form"/>
				<input type="submit" value="搜尋" style="width: 120px;height: 40px;" form="search_form"/>
			</p>
			<p style="position: absolute; top: 50%; left: 26%;">
				<input type="text" class="search_drink" placeholder="飲品" style="width: 120px;height: 40px;"name="searchDrinkQQOO" form="search_drink_form"/>
				<input type="submit" value="搜尋" style="width: 120px;height: 40px;" form="search_drink_form"/>
			</p>
			<p style="position: absolute; top: 10px; left: 2%;">
				<input type="button" class="turnoff" value="返回" style="width: 120px;height: 40px;"/>
			</p>
		</div>
	</div>

	<!-- 新增隱藏食物飲料選項 -->
	<div  class="showHide_list" id="build_food_drink_button" style="position: fixed;top:0%;left:0%;width: 100%;height: 100%;display: none;">
		<img src="images/backgroundimg.png" class="black_mask">
		<div class="container" style="position:fixed;top: 22.5%;left:20%;width: 55%;height: 50%;border-style: solid;background: white;background-position: -5%;overflow: auto;">
			<p style="position: absolute; top: 15%; left: 14%;">
				<a href="#" class="accChooseQQ"  id="drink">飲料</a>
				<a href="#" class="accChooseQQ"  id="food">食物</a>
			</p>
			<p style="position: absolute; top: 10px; left: 2%;">
				<input type="button" class="turnoff" value="返回" style="width: 120px;height: 40px;"/>
			</p>
		</div>
	</div>
	
</body>
</html>