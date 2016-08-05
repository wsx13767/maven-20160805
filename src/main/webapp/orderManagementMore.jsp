<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>爽爽訂-管理團購</title>
	<%@ include file="includeFile/top.inc" %>
	<%@ include file="includeFile/nowUser.inc"%>
	<%@ include file="includeFile/testLog.inc"%>
	<%
		Date time = new Date();
		List<JoinOrder> list = null;
		JoinOrder joinOrder = null;
		BuildOrder buildOrder = null;
		String order_number = null;
		int user_id = (int)(nowUser.getUserId());
		if(request.getParameter("searchOrder")!=null){
			try{
				order_number = request.getParameter("searchOrder");
				list = new OrderManagementMoreDAO().findByOrderNumber(order_number, user_id);
				buildOrder = new OrderInfoDAO().findById(order_number);
			
				if(!buildOrder.getUserData().getAccount().equals(nowUser.getAccount())){
					out.print("<script>window.location = './orderManagement.jsp';</script>");
					return;
				}
			}catch(Exception e){
				out.print("<script>window.location = './orderManagement.jsp';</script>");
				return;
			}
		} else {
			out.print("<script>window.location = './orderManagement.jsp';</script>");
			return;
		}
		long deadHours = Integer.parseInt(buildOrder.getDeadlineString().split(" ")[1].split(":")[0]);
		long deadMinutes = Integer.parseInt(buildOrder.getDeadlineString().split(" ")[1].split(":")[1]);
		long deadSeconds = Integer.parseInt(buildOrder.getDeadlineString().split(" ")[1].split(":")[2]);
		long deadTimes = deadHours*3600+deadMinutes*60+deadSeconds;
		long nowTimes = time.getHours()*3600+time.getMinutes()*60+time.getSeconds();
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
            	<div class="span7 contact_right" style="margin-top:20px;">
            	<h3 class="mana1"><a href="orderManagement.jsp">管理團購</a> > 團購詳細</h3>
            	<form id="stateform" action="./ChangeState" method="post">
            		<input class="hidden" type="password" name="searchOrder" value="<%=request.getParameter("searchOrder")%>">
            		<input class="hidden" type="password" name="orderState" value="<%=buildOrder.getBuildState()%>">
            	</form>
            	<pre style="white-space: pre-wrap; word-wrap: break-word;">團購名稱：<%=buildOrder.getItemInformation()%>
團購狀態：<%=buildOrder.getBuildState()%>
發起時間：<%=buildOrder.getBuildTimeString()%>
截止時間：<%=buildOrder.getDeadlineString()%><%if(time.after(buildOrder.getDeadline()) && deadTimes <= nowTimes){%> (已逾期)<%}%>
備註：
<%="&nbsp;&nbsp;&nbsp;&nbsp;"%><%=buildOrder.getRemark().replaceAll("\\s+", "")%></pre>
								<table class="table table-striped table-bordered" style="text-align: center;">
									<thead>
										<tr>
											<td style="width: 14%;">訂購者</td>
											<td style="width: 25%;">品項</td>
											<td style="width: 11%;">份數</td>
											<td style="width: 11%;">金額</td>
											<td style="width: 14%;">訂購時間</td>
											<td style="width: 17%;">郵件地址</td>
											<td style="width: 8%;">領收</td>
										</tr>
									</thead>
									<tbody>
										<%
										int getval = 0;
										int total = 0;
										for(int i=0;i<list.size();i++){
											joinOrder = list.get(i);
											total = total + joinOrder.getMoney().intValue();
											getval = getval + joinOrder.getGet().intValue();
										%>
										<tr>
											<td><%=joinOrder.getUserData().getAccount()%></td>
											<td><%=joinOrder.getItemInformation().getName()+"<br>"+joinOrder.getSize()+joinOrder.getSuger()+joinOrder.getIce()%></td>
											<td><%=joinOrder.getCount()%></td>
											<td><%=joinOrder.getMoney()%></td>
											<td><%=joinOrder.getJoinTimeString()%></td>
											<td><a href="#"><%=joinOrder.getUserData().getEmail()%></a></td>
											<%if(buildOrder.getBuildState().equals("發佈中")){%>
											<td style="color: red;">未領</td>
											<%} else if(joinOrder.getGet().intValue()<1 && buildOrder.getBuildState().equals("已截止")){%>
											<td><a href="#" style="color: red;" onclick="$('#updateGet').val(<%=joinOrder.getJoinLog()%>); if(confirm('確認變更為領收?(更改後無法復原)')){$('#updateGetForm').submit();}  return false;">未領</a></td>
											<%} else {%>
											<td>已領</td>
											<%}%>
										</tr>
										<%}%>
										<%
											if(buildOrder.getBuildState().equals("已截止") && getval==list.size()){
												BuildOrderDAO changeState = new BuildOrderDAO();
												order_number = request.getParameter("searchOrder");
												changeState.updateStateToFinish(order_number);
												out.print("<script>window.location = './orderManagement.jsp';</script>");
											}
										%>
									</tbody>
								</table>
								<div style="text-align: right; padding: 0 0 15px 0;">
									總計：<%=total%>元 <br><br>
									<p><%if(buildOrder.getBuildState().equals("發佈中")){%><input id="changeState" type="button" value="截止此發佈" style="font-size: 14px; padding: 5px 5px 5px 5px;" onclick="if(confirm('確認截止此團購?(更改後無法復原)')){$('#stateform').submit();}"><%}%><p>	
								</div>
							</div>
            </div>
           </div>
          				<form id="updateGetForm" method="post" action="./ChangeGet">
            				<input id="updateGet" class="hidden" type="password" name="getNO" value="null">
            				<input class="hidden" type="password" name="searchOrder" value="<%=request.getParameter("searchOrder")%>">
            			</form>
		   
	   		    <div class="clearfix"> </div>
	     </div>
	<!---->
	<%@ include file="includeFile/footer.inc" %>
</body>
</html>