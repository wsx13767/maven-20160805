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
		 
		 $('#sendbtn').click(function(){
		 
	     useremail = $('#email').val();
		
		 console.log(useremail);
		 
		 $.ajax({
	         url:"./ForgetPW" ,
	         data: {"useremail":useremail},
	         type:"POST",
	         success: function(e){
	        	 console.log(e);
	        	 if(e=='true'){
	        		 alertify.alert('發送失敗 <br> 請重新輸入 ');
	        		 setTimeout(function(){ window.location = './forgetpw.jsp'; }, 2000);}
	        	 else{
	        		 alertify.alert('發送成功 <br> 請至EMAIL 查看信件');
	        		 setTimeout(function(){ window.location = './index.jsp'; },2000);}
	        	 
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
			  	<h3>忘記密碼</h3>
				<p>請輸入註冊時的Email 以便發送變更密碼信件 </p>
				
				  <div>
					<span >Email</span>
					<input  type="email" name="email" id="email" class="form-control"> 
				  </div>
				  <br>
				  <input type="button" value="送出" class="btn btn-danger" id="sendbtn">
				 
			       
		   </div>	
			    
			   <div class="clearfix"> </div>
			 </div>
			
	   		    <div class="clearfix"> </div>
      	 </div>
	<!---->
	<%@ include file="includeFile/footer.inc" %>
</body>
</html>