<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%! UserData nowUser = null;%>
<% 
	String account = null;
	nowUser = null;
	if(request.getSession().getAttribute("login")!=null){
		try{
			account = request.getSession().getAttribute("login").toString();
			nowUser = new UserDataDAO().findByAccount(account);
		} catch (Exception e) {
				e.printStackTrace();
			}
	}
%>