<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title></title>
<script type="text/javascript">
	<%
		String rs = null;
		switch(Integer.parseInt(request.getAttribute("rs").toString())){
			case 0:
				rs = "更改成功。";
				break;
			case 1:
				rs = "資料填入格式或資料庫傳輸發生錯誤。";
				break;
			case 2:
				rs = "更改失敗，輸入內容未通過驗證。";
				break;
			case 3:
				rs = "欄位不能為空。";
				break;
		}
	%>
	alert('<%=rs%>')
	window.location = './accountInfo.jsp';
</script>

</head>
<body>

</body>
</html>