<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>爽爽訂-儲值服務</title>
	<%@ include file="includeFile/top.inc" %>
	<%@ include file="includeFile/nowUser.inc"%>
	<%@ include file="includeFile/testLog.inc"%>
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
            <div class="ui-widget">
           
                <label for="search_cash">請輸入姓名或電話查詢會員資料  <span style="color:red">※ 加值後餘額 請勿超過10000元</span> </label>
                <br>
                
                <div class="form-group">
                
                <input type="text" id="userinfo" name="userinfo"   class="form-control" >
                <br>
                <input id="try" type="button" value="查詢" class="btn btn-info" ></input> 
                
                </div>
                <div id="cashinfo" class="cashinfo" >
                </div>
                <br>
                
                <br>
                <P class="search_result"style="display:none;">請輸入欲加值金額</P> 
                <input type="number" name="cashmoney" id="cashmoney" min = 0  value ="0"  class="search_result form-control" style="display:none;width:100px;">
                <br>
                <br>
                <button id="cash" class="search_result btn btn-success" style="display:none;">確認加值</button>
                <button id="cancal" class="search_result btn btn-danger" style="display:none;">取消</button>
               
            </div>
            <div class="clearfix"> </div>
        </div>
        
                <div class="clearfix"> </div>
    </div>
    <!---->
    <%@ include file="includeFile/footer.inc" %>
</body>

</html>
