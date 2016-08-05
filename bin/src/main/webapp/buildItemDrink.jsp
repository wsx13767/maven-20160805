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
#textarea{
width:780px;
height:250px;
resize: none;
}
.num{
width:80px;
}
.date{
width:240px;
}
</style>
<script>
$(function(){
	var drinkcount = 2;
	$('#drink_add').click(function(){
		 if(drinkcount >4){
				alert("只能4組");
		       	 return false;
		 }
		 $('#itemrow').append('<tr>'+
					'<td><h4><input type="text"  placeholder="飲品名稱" name="drink_itemName_name'+drinkcount+'" form="drink_form" minlength="2" maxlength="20" required="required"/></h4></td>'+
					'<td><h4><input type="number"  placeholder="價錢" min="10" max="9999" name="drink_bigMoney_name'+drinkcount+'" form="drink_form" required="required"/></h4></td>'+
					'<td><h4><input type="number"  placeholder="價錢" min="10" max="9999" name="drink_midMoney_name'+drinkcount+'" form="drink_form" required="required"/></h4></td>'+
					'<td><h4><input type="number"  placeholder="價錢" min="10" max="9999" name="drink_smallMoney_name'+drinkcount+'" form="drink_form" required="required"/></h4></td>'+
					'<td><h4>'+
						'<p><input type="checkbox" name="sugar'+drinkcount+'" value="0" form="drink_form" />無糖</p>'+
						'<p><input type="checkbox" name="sugar'+drinkcount+'" value="3" form="drink_form"/>3分</p>'+
						'<p><input type="checkbox" name="sugar'+drinkcount+'" value="5" form="drink_form"/>5分</p>'+
						'<p><input type="checkbox" name="sugar'+drinkcount+'" value="7" form="drink_form"/>7分</p>'+
						'<p><input type="checkbox" name="sugar'+drinkcount+'" value="10" form="drink_form" checked="checked" required="required"/>正常</p>'+
					'</h4></td>'+
					'<td><h4>'+
						'<p><input type="checkbox" name="ice'+drinkcount+'" value="0" form="drink_form"/>熱</p>'+
						'<p><input type="checkbox" name="ice'+drinkcount+'" value="3" form="drink_form" />去冰</p>'+
						'<p><input type="checkbox" name="ice'+drinkcount+'" value="5" form="drink_form" />微冰</p>'+
						'<p><input type="checkbox" name="ice'+drinkcount+'" value="7" form="drink_form"/>少冰</p>'+
						'<p><input type="checkbox" name="ice'+drinkcount+'" value="10" form="drink_form" checked="checked" required="required"/>正常</p>'+
					'</h4></td>'+
					'<td><h4>'+
						'<p><input type="checkbox" name="size'+drinkcount+'" value="b" form="drink_form" checked="checked" required="required"/>大</p>'+
						'<p><input type="checkbox" name="size'+drinkcount+'" value="m" form="drink_form"/>中</p>'+
						'<p><input type="checkbox" name="size'+drinkcount+'" value="s" form="drink_form"/>小</p>'+
					'</h4></td>'+
				'</tr>');
		 		drinkcount++;
	});
});
</script>	
	

	
	<div class="container">
<%@ include file="includeFile/selectdan.inc" %>
		<div class="main"> 
         	<div class="reservation_top">          
            	<div class=" contact_right">
            	<h1>飲料訂單</h1>
            		<div class="row">
						<div class="col-md-9">
						<h1>
							標題：<input form="drink_form" name="drink_title_name" type="text" class="drink_title" required="required"/>
						</h1></div>
						<div class="col-md-3"><h1>
						</h1></div>
						<table>
							<tr>
								<td width="350px"; rowspan="3";>
									<img width="300px"; src="images/cloudupicon.png" class="uploadPreview" alt="Image preview"/>
									<input id="uploadImage" type="file" name="myPhoto" onchange="loadImageFile();" form="drink_form" required="required"/>
								</td>
								<td style="width:600px; border-bottom:1px solid rgba(85, 85, 85, 0.15);"><h2>
									團購最大目標：<input id="number_max" form="drink_form" name="drink_itemNeedcounter_name" class="num"   type="number" min="50" max="999" required="required"/>
								</h2></td>
							</tr>
							<tr>
								<td style="width:600px; border-bottom:1px solid rgba(85, 85, 85, 0.15);"><h2>
									團購最低需求：<input name="drink_counter_name" form="drink_form" id="number_min" class="num"  type="number" min="10" required="required"/>
								</h2></td>
							</tr>
							<tr>
								<td style="width:600px; border-bottom:1px solid rgba(85, 85, 85, 0.15);">
									<h2>截止時間：</h2>
									<h2><input id="date" class="date" type="date" name="drink_deadline_name" form="drink_form"  required="required"/></h2>
									<h2><input id="time" type="time" name="drink_deadline_name2" form="drink_form"  required="required"/>
									</h2>
								</td>
							</tr>
						</table>
					</div>
					<h1>商品介紹</h1>
					<h4><textarea id="textarea" placeholder="商品敘述" name="drink_itemInformation_name" form="drink_form" minlength="10" required="required"></textarea></h4>
					<h1>商品項目<button type="button" class="btn btn-success" id="drink_add">增加商品</button></h1>
					<table id="itemrow" class="table table-hover">
					<tr >
						<td style="width:200px;"><h4>飲品名稱</h4></td>
						<td style="width:100px;"><h4>大杯價錢</h4></td>
						<td style="width:100px;"><h4>中杯價錢</h4></td>
						<td style="width:100px;"><h4>小杯價錢</h4></td>
						<td><h4>甜度</h4></td>
						<td><h4>冰塊</h4></td>
						<td><h4>大小</h4></td>
					</tr>
					<tr>
						<td><h4><input type="text"  placeholder="飲品名稱" name="drink_itemName_name1" form="drink_form" minlength="2" maxlength="20" required="required"/></h4></td>
						<td><h4><input type="number"  placeholder="價錢" min="10" max="9999" name="drink_bigMoney_name1" form="drink_form" required="required"/></h4></td>
						<td><h4><input type="number"  placeholder="價錢" min="10" max="9999" name="drink_midMoney_name1" form="drink_form" required="required"/></h4></td>
						<td><h4><input type="number"  placeholder="價錢" min="10" max="9999" name="drink_smallMoney_name1" form="drink_form" required="required"/></h4></td>
						<td><h4>
							<p><input type="checkbox" name="sugar1" value="0" form="drink_form" />無糖</p>
							<p><input type="checkbox" name="sugar1" value="3" form="drink_form"/>3分</p>
							<p><input type="checkbox" name="sugar1" value="5" form="drink_form"/>5分</p>
							<p><input type="checkbox" name="sugar1" value="7" form="drink_form"/>7分</p>
							<p><input type="checkbox" name="sugar1" value="10" form="drink_form" checked="checked" required="required"/>正常</p>
						</h4></td>
						<td><h4>
							<p><input type="checkbox" name="ice1" value="0" form="drink_form"/>熱</p>
							<p><input type="checkbox" name="ice1" value="3" form="drink_form" />去冰</p>
							<p><input type="checkbox" name="ice1" value="5" form="drink_form" />微冰</p>
							<p><input type="checkbox" name="ice1" value="7" form="drink_form"/>少冰</p>
							<p><input type="checkbox" name="ice1" value="10" form="drink_form" checked="checked" required="required"/>正常</p>
						</h4></td>
						<td><h4>
							<p><input type="checkbox" name="size1" value="b" form="drink_form" checked="checked" required="required"/>大</p>
							<p><input type="checkbox" name="size1" value="m" form="drink_form"/>中</p>
							<p><input type="checkbox" name="size1" value="s" form="drink_form"/>小</p>
						</h4></td>
					</tr>

				</table>
				<button  type="submit" class="btn btn-warning" form="drink_form">完成發佈團購</button>
			
					<form id="drink_form" action="./buildOrderDrinkServlet" method="post" "></form>
					<input type="text" name="userID" value="<%=nowUser.getUserId()%>" class="hidden" form="drink_form"/>

            	</div>
            </div>
         </div>
     </div>
	
	
<!--container-->

<%@ include file="includeFile/footer.inc"%>
</body>
</html>