<%--
  Created by IntelliJ IDEA.
  User: qhj08
  Date: 2022/4/13
  Time: 17:59
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
        <div class="mdui-typo-title mdui-m-y-2">还车确认</div>
    </div>
    <div class="mdui-row">
        <div class="mdui-col-xs-12 mdui-col-md-8">
            <div class="mdui-list">
                <a class="mdui-list-item">
                    <i class="mdui-list-item-icon mdui-icon material-icons">access_time</i>
                    <div class="mdui-list-item-content">取车日期：2022-04-12</div>
                </a>
                <a class="mdui-list-item">
                    <i class="mdui-list-item-icon mdui-icon material-icons">av_timer</i>
                    <div class="mdui-list-item-content">预计归还日期：2022-04-16</div>
                </a>
                <a class="mdui-list-item">
                    <i class="mdui-list-item-icon mdui-icon material-icons">timelapse</i>
                    <div class="mdui-list-item-content">实际归还日期：2022-04-15</div>
                </a>
            </div>
        </div>
        <div class="mdui-col-xs-12 mdui-col-md-4">
            <table class="mdui-table mdui-table-hoverable update-little-table table-text-center">
                <tbody>
                <tr>
                    <td>押金</td>
                    <td>${rentPrice.deposit }￥</td>
                </tr>
                <tr>
                    <td>日租累计</td>
                    <td>${rentPrice.dailyRent }￥</td>
                </tr>
                <tr>
                    <td>淡旺季价格变化</td>
                    <td>-200￥</td>
                </tr>
                <tr>
                    <td>保险费</td>
                    <td>${rentPrice.insurance }￥</td>
                </tr>
                <tr>
                    <td>手续费</td>
                    <td>${rentPrice.serviceCharge }￥</td>
                </tr>

                </tbody>
            </table>
        </div>
    </div>
    <div class="mdui-row">
        <div class="mdui-textfield">
            <span class="mdui-text-color-white-100 mdui-m-r-1">押金扣除</span>
            <input class="mdui-textfield-input" placeholder="押金扣除" name="brand" type="text" value="0"/>
            <div class="mdui-textfield-helper">根据车辆和驾驶员情况酌情扣除，最多5000。</div>
        </div>
    </div>
    <div class="mdui-row mdui-m-y-4">
        <span class="mdui-typo-headline mdui-text-color-white-100 mdui-m-r-1">应退还：3372￥</span>
    </div>
    <div class="mdui-row-xs-2 mdui-m-t-2 mdui-m-b-8">
        <div class="mdui-col">
            <a href="carreturn2.do">
                <button class="mdui-btn mdui-btn-block mdui-color-blue-400 mdui-ripple mdui-m-r-1">
                    <div class="mdui-text-color-white">确认还车</div>
                </button>
            </a>
        </div>
        <div class="mdui-col">
            <a href="carstatus.do">
                <button class="mdui-btn mdui-btn-block mdui-ripple">
                    <div class="mdui-text-color-white">返回</div>
                </button>
            </a>
        </div>
    </div>

    <%@ include file="z-beian.html" %>
</div>
<script>document.getElementById("home").classList.add("mdui-list-item-active");</script>
<%@ include file="z-footer.html" %>
</body>
</html>
