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
		List<BuildOrder> list = new BuildOrderDAO().findByUserId((int)(nowUser.getUserId()));
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
            		<h3 class="mana1">管理團購</h3>
            		<div class="contact-form">
						<div class="banner-matter">
							<%
								for(int i=0;i<list.size();i++){
									buildOrder = list.get(i);
									if(!buildOrder.getBuildState().equals("finsh")){
							%>
									<div class="col-md-4 chain-grid">
					   		     		<a href="orderManagementMore.jsp?searchOrder=<%=buildOrder.getOrderNumber()%>"><img class="img-responsive chain imgpk"  src="images/<%=buildOrder.getPicture()%>" alt=" " /></a>
					   		     		<span class="star"> </span>
					   		     		<div class="grid-chain-bottom">
					   		     			<h6><%=buildOrder.getItemInformation()%></h6>
					   		     			<div class="star-price">
						   		     			<div class="dolor-grid"> 
							   		     		<h6><a href="orderManagementMore.jsp?searchOrder=<%=buildOrder.getOrderNumber()%>">選我</a></h6>
						   		     			</div>
						   		     				
						   		     				<div class="clearfix"> </div>	
											</div>
					   		     		</div>
					   		     	</div>
							<%}}%>
							<div class="clearfix"> </div>
						</div>
						
					 	<article style="position: absolute; width: 100%; opacity: 0;">	
					 	</article>
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