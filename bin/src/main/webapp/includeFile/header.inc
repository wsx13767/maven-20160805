<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<!--header-->
    <div class="header">
        <div class="top-header">
            <div class="container">
                
                <div class="top-header-left">
                    <ul class="support">
                        <li><a href="#"><label> </label></a></li>
                        <li><a href="index.jsp">爽爽訂，訂餐真方便</a></li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="top-header-right">
                    <div class="down-top">      
                        <select class="in-drop">
                            <option value="中文" class="in-of">中文</option>
                            <option value="English" class="in-of">English</option>
                        </select>
                     </div>
                    <div class="down-top top-down"></div>
                    <div class="clearfix"> </div>   
                </div>
                <div class="clearfix"> </div>       
            </div>
        </div>
        <div class="bottom-header">
            <div class="container">
                <div class="header-bottom-left">
                    <div class="logo">
                        <a href="index.jsp"><img src="images/logo.png" alt=" " class="logo_img"/></a>
                    </div>
                    <div class="search">
                        <input type="text" id="search_food" value="" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '';}" >
                        <input type="submit"  value="搜尋">

                    </div>
                    <div class="clearfix"> </div>
                </div>
                <div class="header-bottom-right">                   
                    <div class="account"><a href="accountInfo.jsp"><span>    </span><%=nowUser.getAccount()%></a>
                    </div>

		<form action="./Logout" method="post">
                    <ul class="login">
			<li><a href="#" onclick="$(this).closest('form').submit(); return false;"><span> </span>登出</a></li>
                        <li ><span>    </span></li>
                    </ul>
		</form>

                    <% if(nowUser.getUserId()==0){%>
                    <div class="cart"><a href="cash.jsp"><span> </span>儲值</a>
                    <%}else{%>
                    <div class="cart"><a href="paySearch.jsp"><span> </span><%=nowUser.getMoney()%></a>
                    <%}%>
                    </div>
                    <div class="clearfix"> </div>
                </div>
                <div class="clearfix"> </div>   
            </div>
        </div>
    </div>
    <!---->