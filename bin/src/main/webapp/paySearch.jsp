<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>爽爽訂-帳戶資訊</title>
	<%@ include file="includeFile/top.inc" %>
	<%@ include file="includeFile/nowUser.inc"%>
	<%@ include file="includeFile/testLog.inc"%>
	<%
		List<PaySearch> list = new PaySearchDAO().findById((int)(nowUser.getUserId()));
		PaySearch paySearch = null;
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
            		<h3 class="mana1"><a href="memberManagement.jsp">會員管理</a> > 帳戶交易記錄</h3>
            		<div>
            			<table class="payBorder" border=1>
            				<thead>
            					<tr class="tr1">
            						<td>編號</td>
            						<td>交易日期</td>
            						<td>儲值</td>
            						<td>消費</td>
            						<td>詳細內容</td>
            					</tr>
            				</thead>
            				<tbody>
            					<%
            						for(int i=0;i<Math.min(list.size(),30);i++){
            							paySearch = list.get(i);
            					%>
								<tr>
									<td><%=i+1%></td>
									<td><%=paySearch.getTime()%></td>
									<td>
										<%if(paySearch.getMoneyAdd()==null){%>
											<%=""%>
										<%} else {%>
											<%=paySearch.getMoneyAdd()%>
										<%}%>
									</td>
									<td>
										<%if(paySearch.getMoney()==null){%>
											<%=""%>
										<%} else {%>
											<%=paySearch.getMoney()%>
										<%}%>
									</td>
									<td>
										<%if(paySearch.getItemName()!=null && paySearch.getCount()!=null){%>
										<%=paySearch.getItemName()+"*"+paySearch.getCount()%>
										<%}else{%>
										<%=""%>
										<%}%>
									</td>
								</tr>
								<%
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