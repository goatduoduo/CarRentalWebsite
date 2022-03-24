<%--
  Created by IntelliJ IDEA.
  User: qhj08
  Date: 2022/3/24
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>汽车租赁网站模板页</title>
    <%@ include file="z-head1.html"%>
</head>
<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-theme-layout-auto">
<div class="mdui-container">
    <%@ include file="z-bodyTopUser.html"%>
<%--    start here--%>

    <%@ include file="z-beian.html"%>
</div>
<script>document.getElementById("home").classList.add("mdui-list-item-active");</script>
<%@ include file="z-footer.html"%>
</body>
</html>
