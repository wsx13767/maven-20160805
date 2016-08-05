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
<!--container-->
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
	//變更標題
	$('#modify').click(function(){
		$('.drink_title').removeAttr("readonly");
		$('.drink_title').removeAttr("style");
		$('#modify').hide();
		$('#success').fadeToggle();
	});
	$('#success').click(function(){
		$('.drink_title').attr("readonly","readonly");
		$('.drink_title').attr("style","border-style:none;background-color:#eeede8;");
		$('#modify').fadeToggle();
		$('#success').hide();
	});
	//變更最大需求
	$('#modify_2').click(function(){
		$('#number_max').removeAttr("readonly");
		$('#number_max').removeAttr("style");
		$('#modify_2').hide();
		$('#success_2').fadeToggle();
	});
	$('#success_2').click(function(){
		$('#number_max').attr("readonly","readonly");
		$('#number_max').attr("style","border-style:none;background-color:#eeede8;");
		$('#modify_2').fadeToggle();
		$('#success_2').hide();
	});
	//變更最小需求
	$('#modify_3').click(function(){
		$('#number_min').removeAttr("readonly");
		$('#number_min').removeAttr("style");
		$('#modify_3').hide();
		$('#success_3').fadeToggle();
	});
	$('#success_3').click(function(){
		$('#number_min').attr("readonly","readonly");
		$('#number_min').attr("style","border-style:none;background-color:#eeede8;");
		$('#modify_3').fadeToggle();
		$('#success_3').hide();
	});
	//變更時間
	$('#modify_4').click(function(){
		$('#date,#time').removeAttr("readonly");
		$('#date,#time').removeAttr("style");
		$('#modify_4').hide();
		$('#success_4').fadeToggle();
	});
	$('#success_4').click(function(){
		$('#date,#time').attr("readonly","readonly");
		$('#date,#time').attr("style","border-style:none;background-color:#eeede8;");
		$('#modify_4').fadeToggle();
		$('#success_4').hide();
	});
	//變更商品資訊
	$('#modify_5').click(function(){
		$('#textarea').removeAttr("readonly");
		$('#textarea').removeAttr("style");
		$('#modify_5').hide();
		$('#success_5').fadeToggle();
	});
	$('#success_5').click(function(){
		$('#textarea').attr("readonly","readonly");
		$('#textarea').attr("style","border-style:none;background-color:#eeede8;");
		$('#modify_5').fadeToggle();
		$('#success_5').hide();
	});
	$('#drink_add').click(function(){
		 $('#itemrow').append('<tr>'+
					'<td><h4><input type="text" style="border-style:none; background-color:#eeede8;" placeholder="飲品名稱" name="drink_itemName_name" form="drink_form" minlength="2" maxlength="20" required="required"/></h4></td>'+
					'<td><h4><input type="number" style="border-style:none; background-color:#eeede8;" placeholder="價錢" min="10" max="9999" name="drink_bigMoney_name" form="drink_form" required="required"/></h4></td>'+
					'<td><h4><input type="number" style="border-style:none; background-color:#eeede8;" placeholder="價錢" min="10" max="9999" name="drink_midMoney_name" form="drink_form" required="required"/></h4></td>'+
					'<td><h4><input type="number" style="border-style:none; background-color:#eeede8;" placeholder="價錢" min="10" max="9999" name="drink_smallMoney_name" form="drink_form" required="required"/></h4></td>'+
					'<td><h4>'+
						'<p><input type="checkbox" name="sugar" value="0" form="drink_form" />無糖</p>'+
						'<p><input type="checkbox" name="sugar" value="3" form="drink_form"/>3分</p>'+
						'<p><input type="checkbox" name="sugar" value="5" form="drink_form"/>5分</p>'+
						'<p><input type="checkbox" name="sugar" value="7" form="drink_form"/>7分</p>'+
						'<p><input type="checkbox" name="sugar" value="10" form="drink_form" checked="checked" required="required"/>正常</p>'+
					'</h4></td>'+
					'<td><h4>'+
						'<p><input type="checkbox" name="ice" value="0" form="drink_form"/>熱</p>'+
						'<p><input type="checkbox" name="ice" value="3" form="drink_form" />去冰</p>'+
						'<p><input type="checkbox" name="ice" value="5" form="drink_form" />微冰</p>'+
						'<p><input type="checkbox" name="ice" value="7" form="drink_form"/>少冰</p>'+
						'<p><input type="checkbox" name="ice" value="10" form="drink_form" checked="checked" required="required"/>正常</p>'+
					'</h4></td>'+
					'<td><h4>'+
						'<p><input type="checkbox" name="size" value="b" form="drink_form" checked="checked" required="required"/>大</p>'+
						'<p><input type="checkbox" name="size" value="m" form="drink_form"/>中</p>'+
						'<p><input type="checkbox" name="size" value="s" form="drink_form"/>小</p>'+
					'</h4></td>'+
					'<td><button  type="button" class="btn btn-danger" onclick="deleteRow(this)">刪除</button></td>'+
				'</tr>');
	});
	
	
});
//刪除列
function deleteRow(r)
	{
		var i=r.parentNode.parentNode.rowIndex;
		document.getElementById('itemrow').deleteRow(i);
	}
</script>






	<!-- 建立訂單頁面 -->
	<div class="container">
<%@ include file="includeFile/selectdan.inc" %>
		<div class="main"> 
         	<div class="reservation_top">          
            	<div class=" contact_right">
	<%
	int number = 1;
	if (request.getParameter("number")!=null) number=Integer.parseInt(request.getParameter("number"));
		BuildOrder buildOrder = new BuildOrderDAO().findById(number);
	List<ItemInformation> list =new ItemInformationDAO().findByOrdernumber(number);
	ItemInformation item =null;
	ItemInformation counter =null;
	counter = list.get(0);
	%>
					<div class="row">
						<div class="col-md-9"><h1>
							<input form="drink_form" name="drink_title_name" type="text" class="drink_title" style="border-style:none; background-color:#eeede8;" readonly="readonly" value="<%=buildOrder.getItemInformation() %>"/>
						</h1></div>
						<div class="col-md-3"><h1>
							<button id="modify" type="button" class="btn btn-primary">修改</button>
							<button id="success" type="button" class="btn btn-success" style="display:none;">完成</button>
						</h1></div>
						<table>
						<tr>
							<td width="350px"; rowspan="3";>
								<img width="300px"; src="images/<%= buildOrder.getPicture()%>" class="uploadPreview" alt="Image preview"/>
								<input id="uploadImage" type="file" name="myPhoto" onchange="loadImageFile();" form="drink_form" required="required"/>
							</td>
							<td width="600px";><h2>
								團購最大目標：<input id="number_max" form="drink_form" name="drink_itemNeedcounter_name" class="num" style="border-style:none; background-color:#eeede8;"  type="number" min="50" max="999" value="<%=counter.getMax()%>" readonly="readonly"/>
								<button id="modify_2" type="button" class="btn btn-primary">修改</button>
								<button id="success_2" type="button" class="btn btn-success" style="display:none;">完成</button>
							</h2></td>
						</tr>
						<tr>
							<td width="600px";><h2>
								團購最低需求：<input name="drink_counter_name" form="drink_form" id="number_min" class="num" style="border-style:none; background-color:#eeede8;"  type="number" min="10"  value="<%=counter.getMin()%>" readonly="readonly"/>
								<button id="modify_3" type="button" class="btn btn-primary">修改</button>
								<button id="success_3" type="button" class="btn btn-success" style="display:none;">完成</button>
							</h2></td>
						</tr>
						<tr>
							<td width="600px";>
							<h2>截止時間：</h2>
							<h2><input id="date" class="date" type="date" name="drink_deadline_name" form="drink_form" style="border-style:none; background-color:#eeede8;" required="required" readonly="readonly"/></h2>
							<h2><input id="time" type="time" name="drink_deadline_name2" form="drink_form" style="border-style:none; background-color:#eeede8;" required="required" readonly="readonly"/>
								<button id="modify_4" type="button" class="btn btn-primary">修改</button>
								<button id="success_4" type="button" class="btn btn-success" style="display:none;">完成</button>
							</h2>
							</td>
						</tr>
						</table>
					</div>
					<h1>商品介紹
						<button id="modify_5" type="button" class="btn btn-primary">修改</button>
						<button id="success_5" type="button" class="btn btn-success" style="display:none;">完成</button>
					</h1>
					<h4><textarea id="textarea" style="border-style:none; background-color:#eeede8;" placeholder="商品敘述" name="drink_itemInformation_name" form="drink_form" minlength="10" readonly="readonly" required="required"><%=buildOrder.getRemark() %></textarea></h4>
					<h1>商品項目</h1>
					<table id="itemrow" class="table table-hover">
					<tr >
						<td style="width:200px;"><h4>飲品名稱</h4></td>
						<td style="width:100px;"><h4>大杯價錢</h4></td>
						<td style="width:100px;"><h4>中杯價錢</h4></td>
						<td style="width:100px;"><h4>小杯價錢</h4></td>
						<td><h4>甜度</h4></td>
						<td><h4>冰塊</h4></td>
						<td><h4>大小</h4></td>
				<!-- 		<td>刪除</td>   -->
					</tr>
					<%
						for(int i=0; i<list.size(); i++){
							item = list.get(i);
					%>
					<tr>
						<td><h4><input type="text" style="border-style:none; background-color:#eeede8;" placeholder="飲品名稱" name="drink_itemName_name<%=+i+1 %>" form="drink_form" minlength="2" maxlength="20" required="required" value="<%=item.getName() %>"/></h4></td>
						<td><h4><input type="number" style="border-style:none; background-color:#eeede8;" placeholder="價錢" min="10" max="9999" name="drink_bigMoney_name<%=+i+1 %>" form="drink_form" required="required" value="<%=item.getBigPrice() %>"/></h4></td>
						<td><h4><input type="number" style="border-style:none; background-color:#eeede8;" placeholder="價錢" min="10" max="9999" name="drink_midMoney_name<%=+i+1 %>" form="drink_form" required="required" value="<%=item.getMidPrice() %>"/></h4></td>
						<td><h4><input type="number" style="border-style:none; background-color:#eeede8;" placeholder="價錢" min="10" max="9999" name="drink_smallMoney_name<%=+i+1 %>" form="drink_form" required="required" value="<%=item.getSmallPrice() %>"/></h4></td>
						<td><h4>
							<p><input type="checkbox" name="sugar<%=+i+1%>" value="0" form="drink_form" />無糖</p>
							<p><input type="checkbox" name="sugar<%=+i+1%>" value="3" form="drink_form"/>3分</p>
							<p><input type="checkbox" name="sugar<%=+i+1%>" value="5" form="drink_form"/>5分</p>
							<p><input type="checkbox" name="sugar<%=+i+1%>" value="7" form="drink_form"/>7分</p>
							<p><input type="checkbox" name="sugar<%=+i+1%>" value="10" form="drink_form" checked="checked" required="required"/>正常</p>
						</h4></td>
						<td><h4>
							<p><input type="checkbox" name="ice<%=+i+1%>" value="0" form="drink_form"/>熱</p>
							<p><input type="checkbox" name="ice<%=+i+1%>" value="3" form="drink_form" />去冰</p>
							<p><input type="checkbox" name="ice<%=+i+1%>" value="5" form="drink_form" />微冰</p>
							<p><input type="checkbox" name="ice<%=+i+1%>" value="7" form="drink_form"/>少冰</p>
							<p><input type="checkbox" name="ice<%=+i+1%>" value="10" form="drink_form" checked="checked" required="required"/>正常</p>
						</h4></td>
						<td><h4>
							<p><input type="checkbox" name="size<%=+i+1%>" value="b" form="drink_form" checked="checked" required="required"/>大</p>
							<p><input type="checkbox" name="size<%=+i+1%>" value="m" form="drink_form"/>中</p>
							<p><input type="checkbox" name="size<%=+i+1%>" value="s" form="drink_form"/>小</p>
						</h4></td>
			<!--  		<td><button  type="button" class="btn btn-danger" onclick="deleteRow(this)">刪除</button></td>  -->
					</tr>
					<%}%>
				</table>
		<!--	<button type="button" id="drink_add">增加</button>  -->	
				<button  type="submit" class="btn btn-warning" form="drink_form">完成發佈團購</button>



				</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	<!---->
	<%@ include file="includeFile/footer.inc"%>
		<form id="drink_form" action="./buildOrderDrinkServlet" method="post" "></form>
		<input type="text" name="userID" value="<%=nowUser.getUserId()%>" class="hidden" form="drink_form"/>

</body>

</html>	