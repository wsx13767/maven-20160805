<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" import="dao.*,model.*,java.util.*"%>

<%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<title>爽爽訂-發起團購</title>
	<%@ include file="includeFile/top.inc" %>
	<%@ include file="includeFile/nowUser.inc"%>
	<%@ include file="includeFile/testLog.inc"%>
</head>
<body onload="loadImageFile();"> 
	<%if(nowUser!=null){%>
		<%@ include file="includeFile/header.inc"%>
	<%
	} else {
	%>
		<%@ include file="includeFile/headerNotLog.inc"%>
	<%}%>
	
<style>
.num{
width:80px;
}
.date{
width:240px;
}
#textarea{
width:780px;
height:250px;
resize: none;
}
</style>	
<script>
$(function(){
	var foodcount = 2;
	$('#food_add').click(function(){
		 if(foodcount >4){
				alert("只能4組");
		       	 return false;
		 }
		 $('#itemrow').append('<tr>'+
					'<td><h4><input type="text" placeholder="食品名稱" name="food_itemName_name'+foodcount+'" form="food_form" minlength="2" maxlength="20" required="required"/></h4></td>'+
					'<td><h4><input type="number"  placeholder="價錢" min="10" max="9999" name="food_money_name'+foodcount+'" form="food_form" required="required" /></h4></td>'+
					'</tr>');
		 		foodcount++;
	});
});
</script>		
	
	<!-- 建立訂單頁面 -->
	<div class="container">
<%@ include file="includeFile/selectdan.inc" %>
		<div class="main"> 
         	<div class="reservation_top">          
            	<div class=" contact_right">
            	
	            		<div class="row">
							<div class="col-md-9"><h1>
								標題：<input form="food_form" name="food_title_name" type="text" class="food_title" />
							</h1></div>
							<div class="col-md-3"><h1>
							</h1></div>
							<table>
							<tr>
								<td width="350px"; rowspan="3";>
									<img width="300px"; src="images/cloudupicon.png" class="uploadPreview" alt="Image preview"/>
									<input id="uploadImage" type="file" name="food_picture_name" onchange="loadImageFile();" form="food_form" required="required"/>
								</td>
								<td style="width:600px; border-bottom:1px solid rgba(85, 85, 85, 0.15);"><h2>
									團購最大目標：<input id="number_max" form="food_form" name="food_itemNeedCounter_name" class="num"   type="number" min="50" max="999" required="required"/>
								</h2></td>
							</tr>
							<tr>
								<td style="width:600px; border-bottom:1px solid rgba(85, 85, 85, 0.15);"><h2>
									團購最低需求：<input name="food_counter_name" form="food_form" id="number_min" class="num"  type="number" min="10"  required="required"/>
								</h2></td>
							</tr>
							<tr>
								<td style="width:600px; border-bottom:1px solid rgba(85, 85, 85, 0.15);">
									<h2>截止時間：</h2>
									<h2><input id="date" class="date" type="date" name="food_deadline_name" form="food_form"  required="required"/></h2>
									<h2><input id="time" type="time" name="food_deadline_name2" form="food_form" required="required"/>
									</h2>
								</td>
							</tr>
							</table>
						</div>
						<h1>商品介紹</h1>
						<h4><textarea id="textarea"  placeholder="商品敘述" name="food_itemInformation_name" form="food_form" minlength="10"  required="required"></textarea></h4>
						<h1>商品項目<button type="button" class="btn btn-success" id="food_add">增加商品</button></h1>
						<table id="itemrow" class="table table-hover">
						<tr >
							<td style="width:200px;"><h4>飲品名稱</h4></td>
							<td style="width:100px;"><h4>價錢</h4></td>
						</tr>
						<tr>
							<td><h4><input type="text"  placeholder="食品名稱" name="food_itemName_name1" form="food_form" minlength="2" maxlength="20" required="required" /></h4></td>
							<td><h4><input type="number"  placeholder="價錢" min="10" max="9999" name="food_money_name1" form="food_form" required="required" /></h4></td>
						</tr>
					</table>
					<button  type="submit" class="btn btn-warning" form="food_form">完成發佈團購</button>
            	
            	
            	
            	
            	
            	</div>
            </div>
        </div>
     	<%@ include file="includeFile/footer.inc"%>
		<form id="food_form" action="./buildOrderServlet" method="post" "></form>
		<input type="text" name="userID" value="<%=nowUser.getUserId()%>" class="hidden" form="food_form"/>   
        
        
</body>
</html>