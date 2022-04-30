<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>证件审核</title>
    <%@ include file="z-head1.html" %>
</head>
<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-theme-layout-auto">
<div class="mdui-container">
    <%@ include file="z-bodyTopAdmin.html" %>
    <%--    start here--%>
    <div class="mdui-container mdui-valign">
        <table class="mdui-table mdui-table-hoverable  table-text-center">
            <tbody>
            <div class="mdui-row">
                <tr>
                    <td>手机号</td>
                    <td>驾驶证号</td>
                    <td>审核状态</td>
                    <td>领取日期</td>
                    <td>过期日期</td>
                    <td>查看图片</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${list}" var="bean">
                    <tr>
                        <td>
                            ${bean.cellPhone}
                        </td>
                        <td>${bean.licenseNumber}</td>
                        <td>${bean.detail}</td>
                        <td>${bean.receiveDate}</td>
                        <td>${bean.expireDate}</td>
                        <td>
                            <img class=".mdui-img-rounded  img-obj" src="<%=basePath %>${bean.path }" alt="图片" height="60px"/>
                        </td>
                        <td>
                            <a href="licenseexamine1.do?id=${bean.ID}">
                                <button class="mdui-btn mdui-btn-raised mdui-ripple mdui-color-blue-500" >
                                    <div class="mdui-text-color-white">审核通过</div>
                                </button>
                            </a>

                            <a href="licenseexamine2.do?id=${bean.ID }">
                                <button class="mdui-btn mdui-btn-raised mdui-ripple mdui-color-red-500" >
                                    <div class="mdui-text-color-white">审核不通过</div>
                                </button>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </div>
            </tbody>
        </table>



    </div>
    <div class="mdui-container mdui-m-y-4">
        <p align="center">&nbsp;${pagerinfo }</p>
    </div>

    <%@ include file="z-beian.html" %>
</div>
<script>document.getElementById("update").classList.add("mdui-list-item-active");</script>
<%@ include file="z-footer.html" %>
</body>
</html>
</html>
