<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>爽爽訂-確認訂單</title>
	<%@ include file="includeFile/top.inc"%>
	<%@ include file="includeFile/nowUser.inc"%>
	<%@ include file="includeFile/testLog.inc"%>
</head>
<body>
<form id="cjform" action="" method="post"></form>
	<%if(nowUser!=null){%>
		<%@ include file="includeFile/header.inc"%>
	<%
	} else {
	%>
		<%@ include file="includeFile/headerNotLog.inc"%>
	<%}%>
	<div class="container">
		<%@ include file="includeFile/selectdan.inc"%>
		<div class="main jmain">
			<div class="reservation_top">
				<div class="contact_right">
					<div class="confirmJOrder">
						<div class="bg"></div>
								 <div class="content">
										<div class="confirm">
												  <h1 style="margin-top: 15%;">訂單已送出</h1>
												  <p>系統將在<span></span>秒後移置首頁</p>
												  <table id="merge">
												  </table>
												  <a style="font-size: 20px;position: relative;left: 32%;" href="/G/index.jsp">若無跳轉請點此連結</a>
											</div>
									</div>
									 <div class="content2">
										<div class="confirm">
												  <div class="sk-fading-circle">
													  <div class="sk-circle1 sk-circle"></div>
													  <div class="sk-circle2 sk-circle"></div>
													  <div class="sk-circle3 sk-circle"></div>
													  <div class="sk-circle4 sk-circle"></div>
													  <div class="sk-circle5 sk-circle"></div>
													  <div class="sk-circle6 sk-circle"></div>
													  <div class="sk-circle7 sk-circle"></div>
													  <div class="sk-circle8 sk-circle"></div>
													  <div class="sk-circle9 sk-circle"></div>
													  <div class="sk-circle10 sk-circle"></div>
													  <div class="sk-circle11 sk-circle"></div>
													  <div class="sk-circle12 sk-circle"></div>
													</div>
													<p style="margin-top:-8px;">親愛的<%=nowUser.getAccount()%>,請稍候,資料正在傳輸中^_^</p>			  
											</div>
									</div>
						<span class="horiz-flag noise ">
							<h1>確認訂購資料</h1>
						</span>
						<table class="confirm_tab">
							<tr>
								<th class="c_table_top_th0">購買項目</th>
								<th class="c_table_top_th1">數量</th>
								<th class="c_table_top_th2">價格</th>
								<th class="c_table_top_th3">小計</th>
								<%if(request.getParameter("j_chose_ice_0")!=null){%>
								<th class="c_table_top_th4">大小</th>
								<th class="c_table_top_th5">甜度</th>
								<th class="c_table_top_th6">冰塊</th>
								<%}%>
							</tr>
							<tr style="border-top: 1px solid red;">
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<%if(request.getParameter("j_chose_ice_0")!=null){%>
								<td></td>
								<td></td>
								<td></td>
								<%}%>
							</tr>
							<%
							
							 String jget10=request.getParameter("order_number");
							 String jget11=request.getParameter("j_total_money_0");
							
							%>
							<input type="" class="hidden" name="order_number" form="cjform" value=" <%=jget10%>">
							<input type="" class="hidden" name="user_id" form="cjform" value=" <%=nowUser.getUserId()%>">
							<input type="" class="hidden" name="j_total_money" form="cjform" value=" <%=jget11%>">
							<input type="" class="hidden" name="user_name" form="cjform" value=" <%=nowUser.getAccount()%>">
							<% 
										int i=0;
										String jget1=null;
										String jget2=null;
										String jget3=null;
										String jget4=null;
										String jget5=null;
										String jget6=null;
										String jget7=null;
										String jget8=null;
										String size="";
										String suger="";
										String ice="";
										while(request.getParameter("j_item_"+i)!=null){

										size="";
										suger="";
										ice="";

										 jget1=request.getParameter("j_item_"+i);
										 //jget1 = new String(jget1.getBytes("ISO8859_1"), "UTF-8");若為get就需要用到
										 
										 jget2=request.getParameter("j_item_quan_"+i);
										
										 jget3=request.getParameter("j_itemNumber_" + i);
										 
										 jget4=request.getParameter("j_price_"+i);
										 
										 jget5=request.getParameter("j_total_price_"+i);

										 jget6=request.getParameter("j_chose_suger_" + i);

										 jget7=request.getParameter("j_chose_ice_" + i);
									
										 jget8=request.getParameter("j_chose_" + i);
									if(jget7!=null){
										 if(jget8.equals("b")){
											size="大杯";
										 }else if(jget8.equals("m")){
										 	size="中杯";
										 }else{
										 	size="小杯";
										 }
										 switch(Integer.parseInt(jget6)){
										 	case 10:suger="正常";break;
										 	case 7:suger="7分";break;
										 	case 5:suger="半糖";break;
										 	case 3:suger="3分";break;
										 	case 0:suger="無糖";break;
										 }
										  switch(Integer.parseInt(jget7)){
										 	case 10:ice="正常";break;
										 	case 5:ice="少冰";break;
										 	case 0:ice="去冰";break;
										 }
									}
							%>
							<tr>
								<td><%=jget1%></td>
								<td><%=jget2%></td>
								<td><%=jget4%>元</td>
								<td><%=jget5%>元</td>
								<%if(jget7!=null){%>
									<td><%=size%></td>
									<td><%=suger%></td>
									<td><%=ice%></td>
								<%}%>
							</tr>

							<input type="" class="hidden" name="j_item_<%=i%>" form="cjform" value=" <%=jget1%>">
							<input class="hidden" name="j_item_quan_<%=i%>" form="cjform" value=" <%=jget2%>">
							<input class="hidden" name="j_itemNumber_<%=i%>" form="cjform" value=" <%=jget3%>">
							<input class="hidden" name="j_price_<%=i%>" form="cjform" value=" <%=jget4%>">
							<input class="hidden" name="j_total_price_<%=i%>" form="cjform" value=" <%=jget5%>">
							<input class="hidden" name="j_chose_suger_<%=i%>" form="cjform" value=" <%=jget6%>">
							<input class="hidden" name="j_chose_ice_<%=i%>" form="cjform" value=" <%=jget7%>">
							<input class="hidden" name="j_chose_<%=i%>" form="cjform" value=" <%=jget8%>">

							<%
								
																							 
																											i++;
																							}
							%>
							<tr style="border-bottom: 1px solid red;">
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<%if(request.getParameter("j_chose_ice_0")!=null){%>
								<td></td>
								<td></td>
								<td></td>
								<%}%>
							</tr>
						</table>
						<div style="margin-top: 30px;">
							<p style="font-size: 25px;">
								<%String jget9=request.getParameter("j_total_money_0"); %>
								<button style="margin-left: 20%" class="btn btn-success"
									onclick="history.back()">修改訂餐</button>
								<button style="margin-left: 30px" class="btn btn-danger confirmJoinOrder">確認訂餐</button>
								<span style="margin-left: 50px">總計:<%=jget9 %>元</span>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	<!---->
	<%@ include file="includeFile/footer.inc"%>
	<%if(request.getParameter("j_chose_ice_0")!=null){%>
		<script>
			$('.confirmJOrder').css('width','100%');
			$('.confirmJOrder table').css('width','100%','font-size','25px');
			$('.confirmJOrder button').eq(0).css('margin-left','30%');
			$('table th').eq(0).css('width','22%');
			$('table th').eq(1).css('width','12%');
			$('table th').eq(2).css('width','13.2%');
			$('table th').eq(3).css('width','16%');
			$('table th').eq(4).css('width','13.2%');
			$('table th').eq(5).css('width','13.2%');
			$('table tr').last().css('border-top','1px solid red');
		</script>
	<%}%>
	<script>
		$('.confirmJoinOrder').on('click',function(){
			 $('.bg').css({'display':'block'});
             $('.content2').css({'display':'block'});
				$.ajax({
				    url : './test',
				    data:$("#cjform").serialize(),
				    type:"POST",
				    success : function(data) {
				    	console.log(data);
				    	$('.content2').css({'display':'none'});
				    	if(data=='ok'){
				    		 $('.bg').css({'display':'block'});
				             $('.content').css({'display':'block'});
				    		var c=8;
				    		setInterval(function(){
				    			$('.confirmJoinOrder').unbind('click')
				    			$('.confirm span').html(c);
				    			c--;
				    			if(c==0){window.location.href='/G/index.jsp';}
				    		},1000);
				    	}
				    }
				});
		});
		 /*$('.bg').click(function(){
             $('.bg').fadeOut(100);
             $('.content').fadeOut(100);
 		});*/
	</script>
</body>
</html>

