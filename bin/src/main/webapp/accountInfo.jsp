<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>爽爽訂-帳戶資訊</title>
	<%@ include file="includeFile/top.inc" %>
	<%@ include file="includeFile/nowUser.inc"%>
	<%@ include file="includeFile/testLog.inc"%>
	<%
		String phone = "+886 ";
		
		if(phone!=null && !nowUser.getAccount().equals("root")){
			phone = phone+nowUser.getPhone().split("")[1];
			for(int i=0;i<nowUser.getPhone().length()/2;i++){
				phone = phone+"*";
			}
			phone = phone+nowUser.getPhone().substring(nowUser.getPhone().length()/2+1);
		} else {
			phone = null;
		}
	%>
	<%@ include file="includeFile/accountCheckEdit.inc"%>
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
		<!-- main -->
		 <div class="main"> 
         <div class="reservation_top">          
            	<div class=" contact_right">
            		<h3 class="mana1"><a href="memberManagement.jsp">會員管理</a> > 帳戶資訊</h3>
            		<div>
            			<!-- ACCOUNT -->
            			<table style="width: 100%;">
            				<tbody>
            					<tr class="accountInfo">
            						<th class="accInfo_li1">帳號名稱：</th>
            						<td style="width: 2%"></td>
            						<td class="accInfo_li2"><%=nowUser.getAccount()%></td>
            						<td class="accInfo_li3"></td>
            					</tr>
            				</tbody>
            			</table>
            			<!-- PASSWORD -->
            			<form id="pwdform" action="./ChangePassword" method="post">
            			<input type="password" name="userAccount" value="<%=nowUser.getAccount()%>" class="hidden">
            			<table style="width: 100%;">
            				<tbody>
            					<tr class="accountInfo topLine">
            						<th class="accInfo_li1">密碼：</th>
            						<td style="width: 2%"></td>
            						<td class="accInfo_li2">********</td>
            						<td class="accInfo_li3"><a href="#" id="Apwd">編輯</a></td>
            					</tr>
            					<!-- change PASSWORD -->
            					<tr class="hidden Apwd AOths">
            						<th class="accInfo_li1"></th>
            						<td style="width: 2%"></td>
            						<td class="accInfo_li2"><pre>舊密碼　：<input type="password" name="Opwd"></pre></td>
            						<td class="accInfo_li3"></td>
            					</tr>
            					<tr class="hidden Apwd AOths">
            						<th class="accInfo_li1"></th>
            						<td style="width: 2%"></td>
            						<td class="accInfo_li2"><pre>新密碼　：<input type="password" name="Npwd" id="Npwd"></pre></td>
            						<td class="accInfo_li3"></td>
            					</tr>
            					<tr class="hidden Apwd AOths">
            						<th class="accInfo_li1"></th>
            						<td style="width: 2%"></td>
            						<td class="accInfo_li2"><pre>確認密碼：<input type="password" name="Cpwd"></pre></td>
            						<td class="accInfo_li3"></td>
            					</tr>
            					<tr class="hidden Apwd AOths">
            						<th class="accInfo_li1"></th>
            						<td style="width: 2%"></td>
            						<td class="accInfo_li2" style="text-align: right;"><input id="sendpwd" type="button" value="送出"></input></td>
            						<td class="accInfo_li3"></td>
            					</tr>
            				</tbody>
            			</table>
            			
            			</form>
            			<!-- NAME -->
            			<form id="nameform" action="./ChangeName" method="post">
            			<input type="password" name="userAccount" value="<%=nowUser.getAccount()%>" class="hidden">
            			<table style="width: 100%;">
            				<tbody>
            					<tr class="accountInfo topLine">
            						<th class="accInfo_li1">姓名：</th>
            						<td style="width: 2%"></td>
            						<td class="accInfo_li2"><%=nowUser.getName()%></td>
            						<td class="accInfo_li3"><a href="#" id="Aname">編輯</a></td>
            					</tr>
            					<!-- change NAME -->
            					<tr class="hidden Aname AOths">
            						<th class="accInfo_li1"></th>
            						<td style="width: 2%"></td>
            						<td class="accInfo_li2"><pre>新姓名：<input id="Nname" type="text" name="Nname"></pre></td>
            						<td class="accInfo_li3"></td>
            					</tr>
            					<tr class="hidden Aname AOths">
            						<th class="accInfo_li1"></th>
            						<td style="width: 2%"></td>
            						<td class="accInfo_li2" style="text-align: right;"><input id="sendname" type="button" value="送出"></input></td>
            						<td class="accInfo_li3"></td>
            					</tr>
            				</tbody>
            			</table>
            			</form>
            			<!-- PHONE -->
            			<form id="phoneform" action="./ChangePhone" method="post">
            			<input type="password" name="userAccount" value="<%=nowUser.getAccount()%>" class="hidden">
            			<table style="width: 100%;">
            				<tbody>
            					<tr class="accountInfo topLine">
            						<th class="accInfo_li1">連絡電話：</th>
            						<td style="width: 2%"></td>
            						<td class="accInfo_li2"><%=phone%></td>
            						<td class="accInfo_li3"><a href="#" id="Aph">編輯</a></td>
            					</tr>
            					<!-- change PHONE -->
            					<tr class="hidden Aph AOths">
            						<th class="accInfo_li1"></th>
            						<td style="width: 2%"></td>
            						<td class="accInfo_li2"><pre>新號碼：<input id="Nph" type="text" name="Nph"></pre></td>
            						<td class="accInfo_li3"></td>
            					</tr>
            					<tr class="hidden Aph AOths">
            						<th class="accInfo_li1"></th>
            						<td style="width: 2%"></td>
            						<td class="accInfo_li2" style="text-align: right;"><input id="sendph" type="button" value="送出"></input></td>
            						<td class="accInfo_li3"></td>
            					</tr>
            				</tbody>
            			</table>
            			</form>
            			<!-- EMAIL -->
            			<form id="emailform" action="./ChangeEmail" method="post">
            			<input type="password" name="userAccount" value="<%=nowUser.getAccount()%>" class="hidden">
            			<table style="width: 100%;">
            				<tbody>
            					<tr class="accountInfo topLine">
            						<th class="accInfo_li1">email：</th>
            						<td style="width: 2%"></td>
            						<td class="accInfo_li2"><%=nowUser.getEmail()%></td>
            						<td class="accInfo_li3"><a href="#" id="Amail">編輯</a></td>
            					</tr>
            					<!-- change EMAIL -->
            					<tr class="hidden Amail AOths">
            						<th class="accInfo_li1"></th>
            						<td style="width: 2%"></td>
            						<td class="accInfo_li2"><pre>新信箱：<input id="Nmail" type="text" name="Nmail"></pre></td>
            						<td class="accInfo_li3"></td>
            					</tr>
            					<tr class="hidden Amail AOths">
            						<th class="accInfo_li1"></th>
            						<td style="width: 2%"></td>
            						<td class="accInfo_li2" style="text-align: right;"><input id="sendemail" type="button" value="送出"></input></td>
            						<td class="accInfo_li3"></td>
            					<!-- line -->
            					<tr class="topLine"><th></th><td></td><td></td><td></td></tr>
            				</tbody>
            			</table>
            			</form>
            		</div>
            		<div class="contact-form">
							<address class="address">
                </address>
						</div>
            	</div>
            </div>
           </div>
           <!---->
		 
	   		    <div class="clearfix"> </div>
	</div>
	<!---->
	<%@ include file="includeFile/footer.inc" %>
</body>
</html>