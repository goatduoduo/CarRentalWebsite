<%--
  Created by IntelliJ IDEA.
  User: qhj08
  Date: 2022/3/28
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>汽车租赁网站</title>
    <%@ include file="z-head1.html" %>
</head>
<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-theme-layout-auto">
<div class="mdui-container">
    <%@ include file="z-bodyTopUser.html" %>
    <%--    start here--%>
    <div class="mdui-row mdui-typo-title page-title">
        当前余额：${curMoney }￥
    </div>
    <div class="mdui-row mdui-m-t-5">
        <div class="mdui-col-xs-12 mdui-col-md-4">
            <form action="userrecharge.do" method="post">
                <input class="mdui-textfield-input" name="recharge" type="text" value="${recharge }"
                       placeholder="充值金额"/>
                &nbsp;&nbsp; <input class="mdui-m-t-4 mdui-btn mdui-btn-raised mdui-color-blue-a200 action-btn"
                                    value="充值" type="submit"/>

            </form>
        </div>
        <div class="mdui-col-xs-12 mdui-col-md-1">
        </div>
        <div class="mdui-col-xs-12 mdui-col-md-4">
            <form action="userimpose.do" method="post">
                <input class="mdui-textfield-input" name="imposes" type="text" value="${imposes }"
                       placeholder="提现金额"/>
                &nbsp;&nbsp; <input class="mdui-m-t-4 mdui-btn mdui-btn-raised mdui-color-blue-a200 action-btn"
                                    value="提现" type="submit"/>

            </form>
        </div>
    </div>
    <%@ include file="z-beian.html" %>
</div>
<script>document.getElementById("money").classList.add("mdui-list-item-active");</script>
<%@ include file="z-footer.html" %>
</body>
</html>