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
		List<BuildOrder> list = null;
		if(request.getParameter("searchBy")==null){
			out.print("<script>window.location = './memberManagement.jsp';</script>");
			return;
		}else if(request.getParameter("searchBy").equals("BU")){
			list = new OrderSearchDAO().findByBUId((int)(nowUser.getUserId()));
		}else if(request.getParameter("searchBy").equals("JU")){
			list = new OrderSearchDAO().findByJUId((int)(nowUser.getUserId()));
		}else{
			out.print("<script>window.location = './memberManagement.jsp';</script>");
			return;
		}
		BuildOrder buildOrder = null;
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
            		發起團購記錄
            		<%}else{%>
            		加入團購記錄
            		<%}%>
            		</h3>
            		<div>
            			<table class="payBorder" border=1>
            				<thead>
            					<tr class="tr1">
            						<td>編號</td>
            						<td>團購名稱</td>
            						<td>發起人</td>
            						<td>發起時間</td>
            						<td>結束時間</td>
            						<td>狀態</td>
            						<td>詳細資訊</td>
            					</tr>
            				</thead>
            				<tbody>
            					<%
            						for(int i=0;i<Math.min(list.size(),30);i++){
            							buildOrder = list.get(i);
            					%>
            					<tr>
									<td><%=i+1%></td>
									<td><%=buildOrder.getItemInformation()%></td>
									<td><%=buildOrder.getUserData().getAccount()%></td>
									<td><%=buildOrder.getBuildTimeString()%></td>
									<td><%=buildOrder.getDeadlineString()%></td>
									<td><%=buildOrder.getBuildState()%></td>
									<td>
										<a href="orderSearchMoreInfo.jsp?searchBy=<%=request.getParameter("searchBy")%>&searchOrder=<%=buildOrder.getOrderNumber()%>">
											進入
										</a>
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