<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>爽爽訂-帳戶登入</title>
	<%@ include file="includeFile/top.inc" %>
	<%@ include file="includeFile/nowUser.inc"%>
	 <script type="text/javascript">
	 $(function(){ 
		 
		 $('#loginbtn').click(function(){
		 
	     userid = $('#userid').val();
		 userpw = $('#userpw').val();
		 console.log(userid+userpw);
		 
		 $.ajax({
	         url:"./LoginCheck" ,
	         data: {"userID":userid,"passWD":userpw},
	         type:"POST",
	         success: function(e){
	        	 console.log(e);
	        	 if(e=='true'){
	        		 alertify.alert('登入成功 <br> 歡迎回來  <span style="color:red;">13</span>  ');
	        		 setTimeout(function(){ window.location = './index.jsp'; }, 2000);}
	        	 else{
	        		 alertify.alert('登入失敗 <br> 請重新輸入');
	        		 setTimeout(function(){ window.location = './login.jsp'; },2000);}
	        	 
	         },

	          
	     });
		 
		 });
		 
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
      	   <div class="account_grid">
			   <div class=" login-right">
			  	<h3>登入</h3>
				<p>請輸入帳號密碼 以便進行登入 </p>
				
				  <div>
					<span >帳號</span>
					<input type="text" name="userID" id="userid"> 
				  </div>
				  <div>
					<span>密碼</span>
					<input type="password" name="passWD" id="userpw" > 
				  </div>
				  <br>
				  <input type="button" value="登入" class="btn btn-danger" id="loginbtn">
				  <a class="forgot" href="./forgetpw.jsp">  忘記密碼?</a>
			       
		   </div>	
			    
			   <div class="clearfix"> </div>
			 </div>
			
	   		    <div class="clearfix"> </div>
      	 </div>
	<!---->
	<%@ include file="includeFile/footer.inc" %>
</body>
</html>