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
		 
		 $.validator.addMethod("letter_num_only",function(value, element) {
			   var re = new RegExp('^[a-z0-9A-Z\-]+$');
			   return this.optional(element) || re.test(value);
			   },
			   "*只能輸入英文或數字"
			   );
	   $.validator.addMethod("num_only",function(value, element) {
			   var re = new RegExp('^[0-9\]+$');
			   return this.optional(element) || re.test(value);
			   },
			   "*只能輸入數字"
			   );
	
	 $("#myform").validate({
			
		 submitHandler: function(form) 
		   {      
			 useraccount = $('#account').val();
			 userpassword = $('#password').val();
			 checkcode = location.href.split('checkCode=')[1];
			
		      $(form).ajaxSubmit({
		    	   	 url:"./ResetPW" ,
			         data: {"account":useraccount,"password":userpassword,"checkcode":checkcode},
			         type:"post",
			         success: function(e){
			        	 console.log(e);
			        	 if(e=='true'){
			        		 alertify.alert('修改成功 <br> ');
			        		 setTimeout(function(){ window.location = './login.jsp'; }, 2000);}
			        	 else{
			        		 alertify.alert('修改失敗 <br> 請重新發送EMAIL');
			        		 setTimeout(function(){ window.location = './forgetpw.jsp'; },2000);}
			        	 
			         },
    	  			    	  			    	  
		      });     
		   }  ,
		
		 rules:{
			 password1:{required:true,minlength:6,maxlength:21,letter_num_only:true},
			 password2:{required:true,minlength:6,maxlength:21,letter_num_only:true,equalTo:"#password" },
			 
              }
      
	});
	 });
   
   </script>
  
	<style type="text/css">
		#myform label.error{
			color: #e4007f;
			display: inline-block;
			padding: 3px 3px;
			font-size: 12px;
			line-height: 1;
			width: auto;
		}
	</style>         
   
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
			  	      <form id="myform" class="cmxform"  >
                <div class="  register-top-grid">
                    <h3>修改密碼</h3>
                    <div class="mation">
                        <span>帳號<label>*</label></span>
                        <input type="text" name="account" id="account">
                        <span>密碼<label>*</label></span>
                        <input type="password" name="password1" id="password">
                        <span>確認密碼<label>*</label></span>
                        <input type="password" name="password2">
                    </div>
                </div>
                
                 
                 <input type="submit" value="送出" id="resetbtn" class="btn btn-danger" >
            </form>
			       
		   </div>	
			    
			   <div class="clearfix"> </div>
			 </div>
			
	   		    <div class="clearfix"> </div>
      	 </div>
	<!---->
	<%@ include file="includeFile/footer.inc" %>
</body>
</html>