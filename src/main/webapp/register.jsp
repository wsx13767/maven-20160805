<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>爽爽訂-帳戶註冊</title>
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
				 username = $('#name').val();
				 userphone = $('#phone').val();
				 useremail = $('#email').val();
		
			      $(form).ajaxSubmit({
			    	  
			    	 	 url:"./Register" ,
				         data: {"account":useraccount,"password":userpassword,"name":username,"phone":userphone,"email":useremail},
				         type:"POST",
				         success: function(e){
				        	 console.log(e);
				        	 if(e=='true'){
				        		 alertify.alert('註冊成功 <br>     ');
				        		 setTimeout(function(){ window.location = './login.jsp'; }, 2000);}
				        	 else{
				        		 alertify.alert('註冊失敗 <br> 請重新註冊');
				        		 setTimeout(function(){ window.location = './register.jsp'; },2000);}
				        	 
				         },
	    	  			    	  			    	  
			      });     
			   }  ,
			
			 rules:{
				 account:{required:true,minlength:3,maxlength:21,letter_num_only:true,remote:{url:"./RegisterAccountCheck",type: "post"}},
				 password1:{required:true,minlength:6,maxlength:21,letter_num_only:true},
				 password2:{required:true,minlength:6,maxlength:21,letter_num_only:true,equalTo:"#password" },
				 name:{required:true},
				 phone:{required:true,num_only:true},           
                 email:{required:true,email:true,remote: {url: "./RegisterEmailCheck",type: "post" }}
                  },
           messages: {
        	   			account: { remote: "此帳號已有人使用"},
        	   			email: { remote: "此email已有人使用"}
                	  },
			
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
        <div class="register">
           <form id="myform" class="cmxform"  >
                <div class="  register-top-grid">
                    <h3>登入資料</h3>
                    <div class="mation">
                        <span>帳號<label>*</label></span>
                        <input type="text" name="account" id="account">
                        <span>密碼<label>*</label></span>
                        <input type="password" name="password1" id="password">
                        <span>確認密碼<label>*</label></span>
                        <input type="password" name="password2">
                    </div>
                </div>
                <div class="  register-bottom-grid">
                    <h3>個人資料</h3>
                    <div class="mation">
                        <span>姓名<label>*</label></span>
                        <input type="text" name="name" id="name">
                        <span>連絡電話<label>*</label></span>
                        <input type="text" name="phone" id="phone">
                        <span>Email<label>*</label></span>
                        <input type="text" name="email" id="email" >
                    </div>
                </div>
                 <br>
                 <input type="submit" value="送出" id="registerbtn" class="btn btn-danger" >
            </form>
            <div class="clearfix"> </div>
            <div class="register-but">
                <form>
                   
                    <div class="clearfix"> </div>
                </form>
            </div>
        </div>
        
                <div class="clearfix"> </div>
    </div>
    <!---->
  <%@ include file="includeFile/footer.inc" %>
</body>

</html>
