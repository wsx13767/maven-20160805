<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
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
		$("#pwdform").validate({
			rules:{
				 Opwd:{required:true,minlength:6,maxlength:21,letter_num_only:true},
				 Npwd:{required:true,minlength:6,maxlength:21,letter_num_only:true},
				 Cpwd:{required:true,equalTo:"#Npwd" }
            }
		});
		$("#nameform").validate({
			rules:{
				 Nname:{required:true}
            }
		});
		$("#phoneform").validate({
			rules:{
				 Nph:{required:true,num_only:true}
           	}
		});
		$("#emailform").validate({
			rules:{
				 email:{required:true,email:true}
          	}
		});
	});
   </script>
   <style type="text/css">
	label.error{
		color: #e4007f;
		display: inline-block;
		padding: 3px 3px;
		font-size: 12px;
		line-height: 1;
		width: auto;
	}
	</style>