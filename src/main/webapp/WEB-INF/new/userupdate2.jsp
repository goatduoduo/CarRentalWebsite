<%--
  Created by IntelliJ IDEA.
  User: qhj08
  Date: 2022/3/28
  Time: 11:39
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
    <title>更新用户信息</title>
    <%@ include file="z-head1.html" %>
</head>
<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-theme-layout-auto">
<div class="mdui-container">
    <%@ include file="z-bodyTopAdmin.html" %>
    <%--    start here--%>
    <div class="mdui-card">
        <div class="mdui-card-primary">
            <form name="form1" method="post" action="userupdate2.do" onsubmit="return checkfrom()">
                <div class="mdui-typo-headline mdui-m-y-2">
                    基本信息
                </div>
                <table class="mdui-table mdui-table-hoverable update-little-table table-text-center">
                    <tr>
                        <td>姓名：</td>
                        <td>
                            <input class="mdui-textfield-input" name='name' value="${bean.name }" type='text'
                                   style="width: 300px;"/>
                        </td>
                    </tr>
                    <tr>
                        <td>身份证：</td>
                        <td>
                                <input class="mdui-textfield-input" name='identity' value="${bean.identity}" type='text'
                                       style="width: 300px;"/>
                        </td>
                    </tr>
                    <tr>
                        <td>密码：</td>
                        <td>
                            <input class="mdui-textfield-input" name='pwd' value="${bean.pwd}" type='text'
                                   style="width: 300px;"/>
                        </td>
                    </tr>

                    <tr>
                        <td>用户名：</td>
                        <td><input class="mdui-textfield-input" name='userName' value="${bean.userName}" type='text'
                                   id='nameid'
                                   style="width: 300px;"/>
                        </td>
                    </tr>

                    <tr>
                        <td>手机号码：</td>
                        <td><input class="mdui-textfield-input" name='cellPhone' value="${bean.cellPhone }" type='text'
                                   id='tejid'
                                   style="width: 300px;"/>
                        </td>
                    </tr>

                    <tr>
                        <td>驾驶证号：</td>
                        <td><input class="mdui-textfield-input" name='licenseNumber' value="${bean.licenseNumber }"
                                   type='text' id='licenseNumberid'
                                   style="width: 300px;"/>
                        </td>
                    </tr>

                    <tr>
                        <td>用户权限(1管理员 2顾客）：</td>
                        <td><input class="mdui-textfield-input" name='role' value="${bean.role }"
                                   type='text'
                                   style="width: 300px;"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <input type="submit" name="Submit5" value="更新基本信息"
                                   class="mdui-btn mdui-btn-raised mdui-color-blue-a200 action-btn"/>
                    </tr>
                </table>
            </form>
        </div>

    </div>
    <%@ include file="z-beian.html" %>
</div>
<script>document.getElementById("home").classList.add("mdui-list-item-active");</script>
<%@ include file="z-footer.html" %>
</body>
</html>