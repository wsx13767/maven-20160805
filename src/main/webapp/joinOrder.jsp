<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>爽爽訂-加入訂單</title>
	<%@ include file="includeFile/top.inc" %>
	<%@ include file="includeFile/nowUser.inc"%>
	<%@ include file="includeFile/testLog.inc"%>
<script src="js/joinOrder.js"></script>
</head>
<body>
	<form id="jform" action="./setJCookie" method="post" ></form>
	<form name="myForm" action="" method="get" id="delItem"></form>
	<span class="order_number"></span>
	<%if(nowUser!=null){%>
		<%@ include file="includeFile/header.inc"%>
	<%
	} else {
	%>
		<%@ include file="includeFile/headerNotLog.inc"%>
	<%}%>
	<div class="container">
	<%@ include file="includeFile/selectdan.inc" %>
	<input type="" class="hidden" name="user_id" form="jform" value=" <%=nowUser.getUserId()%>">
								 <div class="bg"></div>
								 <div class="content">
									 	<div class="confirm">
											  <h1>訂單項目重複</h1>
											  <p>您勾選的項目將被合併</p>
											  <table id="merge">
											  </table>
											  <button >取消</button><!-- autofocus -->
											  <button >合併</button>
										</div>
 								</div>
		 <div class="main jmain"> 
         <div class="reservation_top">          
            	<div class="contact_right">
            	<h2 class="item_name">君悅排骨</h2>
            	<HR>
            	<div class="join_food_limit" >
            		
            	</div>
            		<div style="clear:both;"></div>
            		<div id="submit">
						<div id="join_food_edit">
							<table class="join_font insertTab" id="join_food_table">
								<tr class="join_font m_table_top">
									<th class="m_table_top_th1"><button class=" btn btn-link delbtn" id="join_btn3">刪除</button></th>
									<th class="m_table_top_th2"><p>項目</p></th>
									<th class="m_table_top_th3">數量</th>
									<th class="m_table_top_th4">價格</th>
									<th class="m_table_top_th5">小計</th>
									<th class="m_table_top_th6">項目選項</th>
								</tr>
								<tr style="border-top:1px solid red;">
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								</table>
							
							<div class="join_hrDiv">
								<button class="appJoinF btn btn-success " id="join_btn1">新增項目</button>
								<div class="join_total join_font"><p></p><span class="hideInputTotal_0" ></span></div>
								<div style="position:relative; left:20%; top:5px;">
									<textarea class="hide" name="j_text" class="countChar" cols="30" rows="10" placeholder="限100字以內"  form="jform"></textarea>
								</div>
								
							</div>
							<button class="aaa btn btn-danger" id="join_btn7"  onclick="history.back()">上一步</button>	
							<button class="aaa btn btn-danger" id="join_btn2"  >下一步</button>	
					</div>	
					</div>
            	</div>
            </div>
           </div>
	   		    <div class="clearfix"> </div> 
	</div>
	<!---->
	<%@ include file="includeFile/footer.inc" %>
</body>
</html>

