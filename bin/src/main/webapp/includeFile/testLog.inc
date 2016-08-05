<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%
	if(nowUser==null){
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		return;
	}
%>