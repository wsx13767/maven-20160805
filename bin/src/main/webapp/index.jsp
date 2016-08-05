<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>爽爽訂，訂餐真方便</title>
	<%@ include file="includeFile/top.inc" %>
		<script src="js/index.js"></script>
	<%@ include file="includeFile/nowUser.inc"%>
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
				<!--上方選單-->
			<div class="shoes-grid">
				<div class="ppt_all">
					<div class="powerpoint" >
						<div class="powerpoint_big_pic">
							<ul>
								<li class="hidden" id="ppt_1" ><img src=""  alt=""></li>
								<li class="hidden" id="ppt_2"><img src="" alt=""></li>
								<li class="hidden" id="ppt_3"><img src="" alt=""></li>
								<li class="hidden" id="ppt_4"><img src="" alt=""></li>
								<li class="hidden" id="ppt_5"><img src="" alt=""></li>
							</ul>
						</div>	
					</div>
					<div class="powerpoint_right">
						<ul>
							<li><a href="#"><span class="glyphicon glyphicon-play" id="ppt_r0"></span><span>
							</span></a></li>
							<li><a href="#"><span class="glyphicon" id="ppt_r1"></span><span>	
							</span></a></li>
							<li><a href="#"><span class="glyphicon" id="ppt_r2"></span><span>
							</span></a></li>
							<li><a href="#"><span class="glyphicon" id="ppt_r3"></span><span>
							</span></a></li>
							<li><a href="#"><span class="glyphicon" id="ppt_r4"></span><span>
							</span></a></li>		
						</ul>
					</div>
				</div>
		   		      <!---->
		   		      <input type="text" value="1" class="hidden" id="rowval" form="eeeeerrrrr">
		   		<div class="shoes-grid-left">
					

	   		     </div>
		   		     <div class="clearfix"> </div>
		   		   </div>   
				   
				</div>
		   		    <div class="clearfix"> </div>        	         
			</div>
		
		<!---->
		<!--  
		<div id="loadingBar" style="display:block; position:fixed; bottom:300px; left:0px; right:0px; color:rgb(255,255,255);text-align:center; background-color:rgb(0,0,0);">
			<svg width='32px' height='32px' xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 100" preserveAspectRatio="xMidYMid" class="uil-default"><rect x="0" y="0" width="100" height="100" fill="none" class="bk"></rect><rect  x='46.5' y='40' width='7' height='20' rx='5' ry='5' fill='#00b2ff' transform='rotate(0 50 50) translate(0 -30)'>  <animate attributeName='opacity' from='1' to='0' dur='1s' begin='0s' repeatCount='indefinite'/></rect><rect  x='46.5' y='40' width='7' height='20' rx='5' ry='5' fill='#00b2ff' transform='rotate(30 50 50) translate(0 -30)'>  <animate attributeName='opacity' from='1' to='0' dur='1s' begin='0.08333333333333333s' repeatCount='indefinite'/></rect><rect  x='46.5' y='40' width='7' height='20' rx='5' ry='5' fill='#00b2ff' transform='rotate(60 50 50) translate(0 -30)'>  <animate attributeName='opacity' from='1' to='0' dur='1s' begin='0.16666666666666666s' repeatCount='indefinite'/></rect><rect  x='46.5' y='40' width='7' height='20' rx='5' ry='5' fill='#00b2ff' transform='rotate(90 50 50) translate(0 -30)'>  <animate attributeName='opacity' from='1' to='0' dur='1s' begin='0.25s' repeatCount='indefinite'/></rect><rect  x='46.5' y='40' width='7' height='20' rx='5' ry='5' fill='#00b2ff' transform='rotate(120 50 50) translate(0 -30)'>  <animate attributeName='opacity' from='1' to='0' dur='1s' begin='0.3333333333333333s' repeatCount='indefinite'/></rect><rect  x='46.5' y='40' width='7' height='20' rx='5' ry='5' fill='#00b2ff' transform='rotate(150 50 50) translate(0 -30)'>  <animate attributeName='opacity' from='1' to='0' dur='1s' begin='0.4166666666666667s' repeatCount='indefinite'/></rect><rect  x='46.5' y='40' width='7' height='20' rx='5' ry='5' fill='#00b2ff' transform='rotate(180 50 50) translate(0 -30)'>  <animate attributeName='opacity' from='1' to='0' dur='1s' begin='0.5s' repeatCount='indefinite'/></rect><rect  x='46.5' y='40' width='7' height='20' rx='5' ry='5' fill='#00b2ff' transform='rotate(210 50 50) translate(0 -30)'>  <animate attributeName='opacity' from='1' to='0' dur='1s' begin='0.5833333333333334s' repeatCount='indefinite'/></rect><rect  x='46.5' y='40' width='7' height='20' rx='5' ry='5' fill='#00b2ff' transform='rotate(240 50 50) translate(0 -30)'>  <animate attributeName='opacity' from='1' to='0' dur='1s' begin='0.6666666666666666s' repeatCount='indefinite'/></rect><rect  x='46.5' y='40' width='7' height='20' rx='5' ry='5' fill='#00b2ff' transform='rotate(270 50 50) translate(0 -30)'>  <animate attributeName='opacity' from='1' to='0' dur='1s' begin='0.75s' repeatCount='indefinite'/></rect><rect  x='46.5' y='40' width='7' height='20' rx='5' ry='5' fill='#00b2ff' transform='rotate(300 50 50) translate(0 -30)'>  <animate attributeName='opacity' from='1' to='0' dur='1s' begin='0.8333333333333334s' repeatCount='indefinite'/></rect><rect  x='46.5' y='40' width='7' height='20' rx='5' ry='5' fill='#00b2ff' transform='rotate(330 50 50) translate(0 -30)'>  <animate attributeName='opacity' from='1' to='0' dur='1s' begin='0.9166666666666666s' repeatCount='indefinite'/></rect></svg>
			沒有下一項拉!
		</div>
		-->
		<%@ include file="includeFile/footer.inc" %>
		
	</body>
</html>