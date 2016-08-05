<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title></title>
<script type="text/javascript">
	alert('已成功領收');
	window.location = './orderManagementMore.jsp?searchOrder=<%=request.getParameter("searchOrder")%>';
</script>

</head>
<body>

</body>
</html>