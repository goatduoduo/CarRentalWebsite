<%--
  Created by IntelliJ IDEA.
  User: qhj08
  Date: 2022/4/13
  Time: 19:29
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
    <title>设置的淡旺季价格变化</title>
    <%@ include file="z-head1.html" %>
</head>
<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-theme-layout-auto">
<div class="mdui-container">
    <%@ include file="z-bodyTopAdmin.html" %>
    <%--    start here--%>
    <div class="mdui-row">
        <div class="mdui-typo-title mdui-m-y-2">设置 "${bean.brand}" 的淡旺季价格变化</div>
    </div>
        <div class="mdui-row">
            <div class="mdui-col-xs-12 mdui-col-md-8">
                <table class="mdui-table mdui-table-hoverable update-little-table table-text-center">
                    <tbody>
                    <tr>
                        <td>开始时间</td>
                        <td>结束时间</td>
                        <td>日租变化（正数为涨价）</td>
                        <td>操作</td>
                    </tr>
                    <c:forEach items="${priceChanges}" var="dp">
                        <tr>
                            <td>${dp.startTime }</td>
                            <td>${dp.endTime}</td>
                            <td>${dp.deltaPrice }</td>
                            <td>
                                <a href="deltaprice_del.do?deltaPrice=${dp.deltaPrice }&startTime=${dp.startTime }&endTime=${dp.endTime }&id=${bean.carInfoId}">
                                    <button class="mdui-btn mdui-btn-raised mdui-ripple" >
                                        <div class="mdui-text-color-white">删除</div>
                                    </button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    <form name="form1" method="post" action="deltaprice_add.do?id=${bean.carInfoId }">
                    <tr>
                        <td><input class="mdui-textfield-input" type="date" name="startTime" size="50" /></td>
                        <td><input class="mdui-textfield-input" type="date" name="endTime" size="50" /></td>
                        <td><input class="mdui-textfield-input" type="text" name="deltaPrice" size="50" /></td>
                        <td>
                            <input type="submit" name="Submit5" value="添加！"
                                   class="mdui-btn mdui-btn-raised mdui-color-blue-a200 action-btn"/>
                        </td>
                    </tr>
                    </form>
                    </tbody>
                </table>
            </div>
            <div class="mdui-col-xs-12 mdui-col-md-4">
                <table class="mdui-table mdui-table-hoverable update-little-table table-text-center">
                    <tbody>
                    <tr>
                        <td>日租报价</td>
                        <td>${rentPrice.dailyRent}</td>
                    </tr>
                    <tr>
                        <td>押金</td>
                        <td>${rentPrice.deposit}</td>
                    </tr>
                    <tr>
                        <td>保险费</td>
                        <td>${rentPrice.insurance}</td>
                    </tr>
                    <tr>
                        <td>手续费</td>
                        <td>${rentPrice.serviceCharge}</td>
                    </tr>

                    </tbody>
                </table>
            </div>
        </div>

    <%@ include file="z-beian.html" %>
</div>
<script>document.getElementById("home").classList.add("mdui-list-item-active");</script>
<%@ include file="z-footer.html" %>
</body>
</html>
