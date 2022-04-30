<%--
  Created by IntelliJ IDEA.
  User: qhj08
  Date: 2022/4/13
  Time: 20:26
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
    <title>用户信息管理</title>
    <%@ include file="z-head1.html" %>
</head>
<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-theme-layout-auto">
<div class="mdui-container">
    <%@ include file="z-bodyTopAdmin.html" %>
    <%--    start here--%>
    <div class="mdui-container mdui-m-y-4">
        <form action="${url }" method="post">
<%--            <div class="mdui-col-xs-1">--%>
<%--                <span class="mdui-text-color-white-100 mdui-m-r-1"></span>--%>
<%--            </div>--%>
            <div class="mdui-col-xs-3">
                <input class="mdui-textfield-input" placeholder="用户名" name="brand" type="text"/>
            </div>
            <div class="mdui-col-xs-2 ">
                <%--                <i class="mdui-icon material-icons">search</i>--%>
                <input class="mdui-btn  mdui-color-blue-600 mdui-ripple" value="搜索" type="submit"/>

            </div>

        </form>
    </div>
    <div class="mdui-container mdui-valign">
        <table class="mdui-table mdui-table-hoverable  table-text-center">
            <tbody>
            <div class="mdui-row">
                <tr>
                    <td>用户名</td>
                    <td>姓名</td>
                    <td>手机号</td>
                    <td>添加时间</td>
                    <td>权限</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${list}" var="bean">
                    <tr>
                        <td>${bean.userName }</td>
                        <td>${bean.name }</td>
                        <td>${bean.cellPhone }</td>
                        <td>${bean.createTime }</td>
                        <td>
                            <c:if test="${bean.role==1 }">管理人员</c:if>
                            <c:if test="${bean.role==2 }">顾客</c:if>
                        </td>
                        <td>
                            <a href="userupdate22.do?id=${bean.ID }">
                                <button class="mdui-btn mdui-btn-raised mdui-ripple" >
                                    <div class="mdui-text-color-white">编辑信息</div>
                                </button>
                            </a>
                            <a href="userdelete2.do?id=${bean.ID }">
                                <button class="mdui-btn mdui-btn-raised mdui-ripple" >
                                    <div class="mdui-text-color-white">注销账户</div>
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
<script>document.getElementById("userlist").classList.add("mdui-list-item-active");</script>
<%@ include file="z-footer.html" %>
</body>
</html>
