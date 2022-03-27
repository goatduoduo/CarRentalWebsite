<%--
  Created by IntelliJ IDEA.
  User: qhj08
  Date: 2022/3/24
  Time: 10:03
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
    <div class="mdui-row">
        <div class="mdui-typo-title mdui-m-y-2">我的预定</div>
<%--        <form  action="${url }" method="post" >--%>
<%--            车牌:<input name="chepai"  type="text" value="${brand }"/>--%>
<%--            <input value="搜索" type="submit"/>--%>
<%--        </form>--%>
    </div>
    <div class="mdui-row">
        <table class="mdui-table mdui-table-hoverable  table-text-center">
            <tbody>
            <tr>
                <td>客户姓名</td>
                <td>客户手机号</td>
                <td>预定状态</td>
                <td>预定时间</td>
                <td>预计还车日期</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${list}" var="bean">
                <tr>
                    <td>${bean.name }</td>
                    <td>${bean.cellPhone }</td>
                    <td>${bean.status }</td>
                    <td>${bean.time }</td>
                    <td>${bean.returnTime }</td>
                    <td>
                        <a href="carview.do?id=${bean.carID }">
                            <button class="mdui-btn mdui-btn-raised mdui-ripple" >
                                <div class="mdui-text-color-white">查看车辆详情</div>
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
<script>document.getElementById("home").classList.add("mdui-list-item-active");</script>
<%@ include file="z-footer.html" %>
</body>
</html>
