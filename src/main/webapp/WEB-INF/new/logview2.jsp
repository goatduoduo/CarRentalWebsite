<%--
  Created by IntelliJ IDEA.
  User: qhj08
  Date: 2022/4/13
  Time: 20:54
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
    <title>汽车租赁网站</title>
    <%@ include file="z-head1.html"%>
</head>
<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-theme-layout-auto">
<div class="mdui-container">
    <%@ include file="z-bodyTopAdmin.html"%>
    <%--    start here--%>
    <div class="mdui-container mdui-m-y-4">
        <form  action="${url }" method="post" >
            <div class="mdui-col-xs-3">
                <input  class="mdui-textfield-input" placeholder="车牌" name="brand" type="text" />
            </div>
            <div class="mdui-col-xs-3">
                <input  class="mdui-textfield-input" placeholder="姓名" name="brand" type="text" />
            </div>
            <div class="mdui-col-xs-3">
                <input  class="mdui-textfield-input" placeholder="时间" name="brand" type="date" />
            </div>
            <div class="mdui-col-xs-2 ">
                <input class="mdui-btn  mdui-color-blue-600 mdui-ripple" value="搜索" type="submit" />
            </div>
        </form>
    </div>

    <div class="mdui-row">
        <table class="mdui-table mdui-table-hoverable  table-text-center">
            <tbody>
            <tr>
                <td>车牌号</td>
                <td>客户姓名</td>
                <td>客户手机号</td>
                <td>操作类型</td>
                <td>操作时间</td>
                <td>预计租车天数</td>
                <td>流水</td>
            </tr>
            <c:forEach items="${list}" var="bean">
                <tr>
                    <td>${bean.licensePlate }</td>
                    <td>${bean.name }</td>
                    <td>${bean.cellPhone }</td>
                    <td>${bean.status }</td>
                    <td>${bean.logTime }</td>
                    <td>${bean.rentDays }</td>
                    <td>${bean.deltaMoney }</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
    <div class="mdui-row mdui-m-y-4" >
        <p align="center">&nbsp;${pagerinfo }</p>
    </div>
    <%@ include file="z-beian.html"%>
</div>
<script>document.getElementById("logview").classList.add("mdui-list-item-active");</script>
<%@ include file="z-footer.html"%>
</body>
</html>
