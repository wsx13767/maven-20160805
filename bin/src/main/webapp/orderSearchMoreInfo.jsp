<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.math.BigDecimal,java.text.DecimalFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>爽爽訂-帳戶資訊</title>
	<%@ include file="includeFile/top.inc" %>
	<%@ include file="includeFile/nowUser.inc"%>
	<%@ include file="includeFile/testLog.inc"%>
	<%
		List<JoinOrder> joinList = null;
		List<SaleTotal> saleList = null;
		BuildOrder buildOrder = null;
		JoinOrder joinOrder = null;
		SaleTotal saleTotal = null;
		DecimalFormat df = new DecimalFormat("#.00");
		int test = 1;
		if(request.getParameter("searchBy")!=null && request.getParameter("searchOrder")!=null){
			try{
				buildOrder = new OrderInfoDAO().findById(request.getParameter("searchOrder"));
				joinList = new JoinInfoDAO().findByOrderNumber(request.getParameter("searchOrder"));
				if(request.getParameter("searchBy").equals("BU") && !buildOrder.getUserData().getAccount().equals(nowUser.getAccount())){
					out.print("<script>window.location = './memberManagement.jsp';</script>");
					return;
				}else if(request.getParameter("searchBy").equals("JU")){
					test = 0;
				}
				saleList = new SaleTotalDAO().findByOrderNumber(request.getParameter("searchOrder"));
			}catch(Exception e){
				out.print("<script>window.location = './memberManagement.jsp';</script>");
				return;
			}
		}else{
			out.print("<script>window.location = './memberManagement.jsp';</script>");
			return;
		}
		
	%>
</head>
<body> 
	<%if(nowUser!=null){%>
		<%@ include file="includeFile/header.inc"%>
	<%
	} else {
	%>
		<%@ include file="includeFile/headerNotLog.inc"%>
	<%}%>
	<div class="container">
		
           <%@ include file="includeFile/selectdan.inc" %>
			<!---->
		 <div class="main"> 
         <div class="reservation_top">          
            	<div class=" contact_right">
            		<h3 class="mana1"><a href="memberManagement.jsp">會員管理</a> > 
            		<%if(request.getParameter("searchBy").equals("BU")){%>
            		<a href="orderSearch.jsp?searchBy=BU">發起團購記錄</a>
            		<%}else{%>
            		<a href="orderSearch.jsp?searchBy=JU">加入團購記錄</a>
            		<%}%>
            		 > 團購詳細內容
            		</h3>
            		<div>
            			<!-- order information -->
            			<div class="payBorder order_title">團購名稱：<%=buildOrder.getItemInformation()%>［<%=buildOrder.getBuildState()%>］</div>
            			<table class="payBorder allCenter" border=1>
            				<tbody>
            					<tr class="white">
            						<td style="width: 10%;">發起人</td>
            						<td style="width: 30%;"><%=buildOrder.getUserData().getAccount()%></td>
            						<td style="width: 15%;">發起時間</td>
            						<td style="width: 15%;"><%=buildOrder.getBuildTimeString()%></td>
            						<td style="width: 15%;">截止時間</td>
            						<td style="width: 15%;"><%=buildOrder.getDeadlineString()%></td>
            					</tr>
            				</tbody>
            			</table>
            			<table class="payBorder allCenter" border=1>
            				<tbody>
            					<tr class="white">
            						<td style="width: 10%;">備註</td>
            						<td class="remark"><%="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"%><%=buildOrder.getRemark()%></td>
            					</tr>
            				</tbody>
            			</table>
            			<!-- sale total -->
            			<div class="payBorder order_title">商品內容</div>
            			<table class="payBorder allCenter" border=1>
            				<thead>
            					<tr class="white tr1">
            						<td style="width: 10%;">編號</td>
            						<td style="width: 30%;">商品名稱</td>
            						<td style="width: 30%;">單價</td>
            						<td style="width: 30%;">購買統計</td>
            					</tr>
            				</thead>
            				<tbody>
            					<%for(int i=0;i<saleList.size();i++){
            							saleTotal = saleList.get(i);
            					%>
            						<tr class="white">
            							<td><%=i+1%></td>
            							<td><%=saleTotal.getName()%></td>
            							<td><%=df.format(saleTotal.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())%></td>
            							<td><%=saleTotal.getCount()%></td>
            						</tr>
            					<%}%>
            				</tbody>
            			</table>
            			<!-- join information -->
            			<div class="payBorder order_title">加入清單</div>
            			<form method="post" id="changeGet"></form>
            			<table class="payBorder allCenter" border=1>
            				<tbody>
            					<tr class="white tr1">
            						<td style="width: 10%;">編號</td>
            						<td style="width: 15%;">加入者</td>
            						<td style="width: 15%;">購買商品</td>
            						<td style="width: 15%;">數量</td>
            						<td style="width: 15%;">加入時間</td>
            						<td style="width: 15%;">消費金額</td>
            						<td style="width: 15%;">領收狀態</td>
            					</tr>
            					<%
            						for(int i=0;i<joinList.size();i++){
            						joinOrder = joinList.get(i);
            						if(joinOrder.getUserData().getAccount().equals(nowUser.getAccount())){
            							test = 1;
            						}
            						if(joinOrder.getUserData().getAccount().equals(nowUser.getAccount())){%>
            						<tr class="nowUser">
            						<%} else {%>
            						<tr class="white">
            						<%}%>
            							<td><%=i+1%></td>
            							<td><%=joinOrder.getUserData().getAccount()%></td>
            							<td><%=joinOrder.getItemInformation().getName()%></td>
            							<td><%=joinOrder.getCount()%></td>
            							<td><%=joinOrder.getJoinTime()%></td>
            							<td><%=joinOrder.getMoney()%></td>
            							<%if(joinOrder.getGet().intValue()==0){%>
            							<td style="color: red;">未領</td>
            							<%} else {%>
            							<td>已領</td>
            							<%}%>
            						</tr>
								<%
									}
            						if(test==0){
            							out.print("<script>window.location = './memberManagement.jsp';</script>");
            							return;
            						}
								%>
            				</tbody>
            			</table>
            			
            		</div>
            		<div class="contact-form">
							<address class="address">
                </address>
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