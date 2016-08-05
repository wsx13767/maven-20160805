<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>爽爽訂-加入團購</title>
	<%@ include file="includeFile/top.inc" %>
	<%@ include file="includeFile/nowUser.inc"%>
	<script src="js/orderInformation.js"></script>
<script>
			jQuery(document).ready(function($){
				$('#etalage').etalage({
					thumb_image_width: 300,
					thumb_image_height: 300,
					source_image_width: 900,
					source_image_height: 1200,
					show_hint: true,
					click_callback: function(image_anchor, instance_id){
						//alert('Callback example:\nYou clicked on an image with the anchor: "'+image_anchor+'"\n(in Etalage instance: "'+instance_id+'")');
					}
				});
				$('#etalage').css('margin-bottom','0px');
			});
		</script>

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
	 	<div class=" single_top">
		  <h1 id="order_name"></h1>
		  <hr>
	      <div class="single_grid">
				<div class="grid images_3_of_2">
								<a href="optionallink.jsp">
									<img class="etalage_thumb_image" src="" style="display: inline; width: 300px; height: 300px; opacity: 1;" />
								</a>
						 <div class="clearfix"> </div>	
				  </div> 
				  <div class="desc1 span_3_of_2">	
				 <h6 id="goal_max"></h6>
				 <h6 id="goal_min"></h6>
				 <h6 id="goal_r"> </h6>
				 <h6 id="goal_c"></h6>
				 <h6 id="build_time"></h6>
			   	 <h6 id="dead_time"></h6>
			   	<div class="toogle">

				     </div>	
          	   </div>
				</div>
          	    <div class="clearfix"> </div>
          	    <h3 style="text-align:center;font-weight: 600;" class="m_3">商品介紹</h3>
          	    <hr style="width:50%;margin-bottom: 20px;">
          	    <pre class="m_text" id="item_remark"></pre>
          	    <h3 style="text-align: center; margin-top:25px;font-weight: 600;" class="m_3">商品項目</h3>	
          	    <hr style="width:50%">
				<div id="input_information">	
					
					
				</div>
			   
				<button id="sinBtn" class="btn btn-danger" style="font-size: 20px;position:relative;left:45%;">加入團購</button>
          	   </div>
          	   <!---->
			
	   		    <div class="clearfix"> </div>			
		</div>
	<!---->
	<%@ include file="includeFile/footer.inc" %>
</body>
</html>