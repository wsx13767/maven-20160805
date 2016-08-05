<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>爽爽訂-連絡我們</title>
	<%@ include file="includeFile/top.inc" %>
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
			<!---->
		 <div class="main"> 
         <div class="reservation_top">          
            	<div class=" contact_right">
            		<h3>連絡我們</h3>
            		<div class="contact-form">
							<form method="post" action="contact-post.jsp">
								<input type="text" class="textbox" value="Name" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Name';}">
								<input type="text" class="textbox" value="Email" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Email';}">
								<textarea value="Message" onfocus="this.value= '';" onblur="if (this.value == '') {this.value = 'Message';}">Message</textarea>
								<input type="submit" value="Send">
								<div class="clearfix"> </div>
							</form>
							<address class="address">
                    <p>9870 St Vincent Place, <br>Glasgow, DC 45 Fr 45.</p>
                    <dl>
                        <dt> </dt>
                        <dd>Freephone:<span> +1 800 254 2478</span></dd>
                        <dd>Telephone:<span> +1 800 547 5478</span></dd>
                        <dd>FAX: <span>+1 800 658 5784</span></dd>
                        <dd>E-mail:&nbsp; <a href="mailto@vintage.com">info(at)bigshop.com</a></dd>
                    </dl>
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