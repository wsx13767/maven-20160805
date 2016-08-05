<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" import="dao.*,model.*,java.util.*"%>

<%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<META charset="utf-8">
<title>爽爽訂，訂餐真方便</title>
	<%@ include file="includeFile/top.inc"%>
	<%@ include file="includeFile/nowUser.inc"%>
						<%
						String search = request.getParameter("searchDrinkQQOO");
						List<SearchFoodModel> list = new BuildItemDAO().findBySearch2(search); 
						SearchFoodModel item = null;
						SearchFoodModel pass= null;
						for(int o=0;o<list.size();o++){
							pass =list.get(o);
							if(pass.getMoney() != null){%>
								<script>
								alert("不是飲品");
								window.location = './buildItem.jsp';
								</script>
						<%	
							}
						}
					%>
	
	
</head>

<body>
<script>
function gotoURL(tr){
	var number=$(tr).find("td:first").text();
	window.location='drinkOrder.jsp?number='+number;
}
function init(){
	$(".item_row").click(function(){gotoURL(this);});
}
$(document).ready(function(){init();});
</script>

	<%if(nowUser!=null){%>
	<%@ include file="includeFile/header.inc"%>
	<%} else {%>
	<%@ include file="includeFile/headerNotLog.inc"%>
	<%}%>
	<div class="container">
		<%@ include file="includeFile/selectdan.inc"%>
		<div class="main"> 
			<div class="reservation_top">          
				<div class=" contact_right">

					<table class="table table-hover">
						<tr>
							<td style="display:none;">number</td>
							<td style="width:30%;">商品圖片</td>
							<td style="width:10%;">商品標題</td>
							<td style="width:60%;">商品敘述</td>
						</tr>
						<%
							for(int i=0; i<list.size(); i++){
							item = list.get(i);
						%>
						<tr class="item_row">
							<td style="display:none;"><%=item.getOrderNumber() %></td>
							<td style="width:30%;"><img src="images/<%=item.getPicture() %>" style="width:100%;"></td>
							<td style="width:10%;"><%=item.getIteminformation() %></td>
							<td style="width:60%;"><%=item.getRemark() %></td>
						</tr>	
						<%}%>
					</table>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	<!---->
	<%@ include file="includeFile/footer.inc"%>

</body>

</html>	