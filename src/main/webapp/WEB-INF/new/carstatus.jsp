<%--
  Created by IntelliJ IDEA.
  User: qhj08
  Date: 2022/4/13
  Time: 16:28
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
    <title>还车确认</title>
    <%@ include file="z-head1.html" %>
</head>
<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-theme-layout-auto">
<div class="mdui-container">
    <%@ include file="z-bodyTopAdmin.html" %>
    <%--    start here--%>
    <div class="mdui-row">
        <div class="mdui-typo-title mdui-m-y-2">已租赁的车辆</div>
    </div>
    <div class="mdui-row">
        <table class="mdui-table mdui-table-hoverable  table-text-center">
            <tbody>
            <tr>
                <td>客户ID</td>
                <td>客户姓名</td>
                <td>客户手机号</td>
                <td>车辆ID</td>
                <td>取车日期</td>
                <td>预计还车日期</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${list}" var="bean">
                <tr>
                    <td>${bean.userID }</td>
                    <td>${bean.name }</td>
                    <td>${bean.cellPhone }</td>
                    <td>${bean.carID }</td>
                    <td>${bean.time }</td>
                    <td>${bean.returnTime }</td>
                    <td>
                        <a href="carreturn.do?carID=${bean.carID }&userID=${bean.userID}">
                            <button class="mdui-btn mdui-color-blue-400 mdui-btn-raised mdui-ripple">
                                <div class="mdui-text-color-white">确认还车</div>
                            </button>
                        </a>
                        <a href="carview.do?id=${bean.carID }">
                            <button class="mdui-btn mdui-btn-raised mdui-ripple">
                                <div class="mdui-text-color-white">车辆详情</div>
                            </button>
                        </a>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
    <%@ include file="z-beian.html" %>
</div>
<script>document.getElementById("apk").classList.add("mdui-list-item-active");</script>
<%@ include file="z-footer.html" %>
</body>
</html>
