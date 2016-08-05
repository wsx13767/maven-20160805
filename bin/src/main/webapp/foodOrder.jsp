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
		$('.food_title').removeAttr("readonly");
		$('.food_title').removeAttr("style");
		$('#modify').hide();
		$('#success').fadeToggle();
	});
	$('#success').click(function(){
		$('.food_title').attr("readonly","readonly");
		$('.food_title').attr("style","border-style:none;background-color:#eeede8;");
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

	
});


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
							<input form="food_form" name="food_title_name" type="text" class="food_title" style="border-style:none; background-color:#eeede8;" readonly="readonly" value="<%=buildOrder.getItemInformation() %>"/>
						</h1></div>
						<div class="col-md-3"><h1>
							<button id="modify" type="button" class="btn btn-primary">修改</button>
							<button id="success" type="button" class="btn btn-success" style="display:none;">完成</button>
						</h1></div>
						<table>
						<tr>
							<td width="350px"; rowspan="3";>
								<img width="300px"; src="images/<%= buildOrder.getPicture()%>" class="uploadPreview" alt="Image preview"/>
								<input id="uploadImage" type="file" name="food_picture_name" onchange="loadImageFile();" form="food_form" required="required"/>
							</td>
							<td width="600px";><h2>
								團購最大目標：<input id="number_max" form="food_form" name="food_itemNeedCounter_name" class="num" style="border-style:none; background-color:#eeede8;"  type="number" min="50" max="999" value="<%=counter.getMax()%>" readonly="readonly"/>
								<button id="modify_2" type="button" class="btn btn-primary">修改</button>
								<button id="success_2" type="button" class="btn btn-success" style="display:none;">完成</button>
							</h2></td>
						</tr>
						<tr>
							<td width="600px";><h2>
								團購最低需求：<input name="food_counter_name" form="food_form" id="number_min" class="num" style="border-style:none; background-color:#eeede8;"  type="number" min="10"  value="<%=counter.getMin()%>" readonly="readonly"/>
								<button id="modify_3" type="button" class="btn btn-primary">修改</button>
								<button id="success_3" type="button" class="btn btn-success" style="display:none;">完成</button>
							</h2></td>
						</tr>
						<tr>
							<td width="600px";>
							<h2>截止時間：</h2>
							<h2><input id="date" class="date" type="date" name="food_deadline_name" form="food_form" style="border-style:none; background-color:#eeede8;" required="required" readonly="readonly"/></h2>
							<h2><input id="time" type="time" name="food_deadline_name2" form="food_form" style="border-style:none; background-color:#eeede8;" required="required" readonly="readonly"/>
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
					<h4><textarea id="textarea" style="border-style:none; background-color:#eeede8;" placeholder="商品敘述" name="food_itemInformation_name" form="food_form" minlength="10" readonly="readonly" required="required"><%=buildOrder.getRemark() %></textarea></h4>
					<h1>商品項目</h1>
					<table class="table table-hover">
					<tr >
						<td style="width:200px;"><h4>飲品名稱</h4></td>
						<td style="width:100px;"><h4>價錢</h4></td>
					</tr>
					<%
						for(int i=0; i<list.size(); i++){
							item = list.get(i);
					%>
					<tr>
						<td><h4><input type="text" style="border-style:none; background-color:#eeede8;" placeholder="食品名稱" name="food_itemName_name<%=+i+1 %>" form="food_form" minlength="2" maxlength="20" required="required" value="<%=item.getName() %>"/></h4></td>
						<td><h4><input type="number" style="border-style:none; background-color:#eeede8;" placeholder="價錢" min="10" max="9999" name="food_money_name<%=+i+1 %>" form="food_form" required="required" value="<%=item.getMoney() %>"/></h4></td>
					</tr>
					<%}%>
				</table>
				<button  type="submit" class="btn btn-warning" form="food_form">完成發佈團購</button>



				</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	<!---->
	<%@ include file="includeFile/footer.inc"%>
		<form id="food_form" action="./buildOrderServlet" method="post" "></form>
		<input type="text" name="userID" value="<%=nowUser.getUserId()%>" class="hidden" form="food_form"/>

</body>

</html>	