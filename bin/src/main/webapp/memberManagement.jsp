<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>爽爽訂-會員管理</title>
	<%@ include file="includeFile/top.inc" %>
	<%@ include file="includeFile/nowUser.inc"%>
	<%@ include file="includeFile/testLog.inc"%>
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
		 <!-- 會員管理 -->
         <div class="reservation_top">          
            	<div class=" contact_right">
            		<h3 class="mana1">會員管理</h3>
            		<div>
            			<!-- 帳戶資訊 -->
            			<a href="accountInfo.jsp" class="accChoose bImgAc">帳戶資訊</a>
            			<!-- 發起團購紀錄 -->
            			<a href="orderSearch.jsp?searchBy=BU" class="accChoose bImgbuildOrder">發起團購紀錄</a> <br>
            			<!-- 加入團購紀錄 -->
            			<a href="orderSearch.jsp?searchBy=JU" class="accChoose bImgJoinOrder">加入團購紀錄</a>
            			<!-- 帳戶交易紀錄 -->
            			<a href="paySearch.jsp" class="accChoose bImgMoney">帳戶交易紀錄</a>
            			<div style="clear:both;"></div>
            		</div>
            		<div class="contact-form">
					<address class="address">
                	</address>
					</div>
           		</div>
            </div>
            <!---->
           </div>
		   
	   		    <div class="clearfix"> </div> 
	</div>
	<!---->
	<%@ include file="includeFile/footer.inc" %>
</body>
</html>