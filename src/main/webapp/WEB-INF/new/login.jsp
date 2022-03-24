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
    <div id="login-card" class="mdui-card">
        <div id="title" class="mdui-dialog-title mdui-m-a-1">登录</div>
        <div class="mdui-card-primary">
            <script type="text/javascript">
                function checkfrom() {
                    if (document.getElementById('usernameid').value == "") {
                        alert("用户名不能为空");
                        return false;
                    }
                    var valid = /^\w+$/;
                    if (!valid.test(document.getElementById('usernameid').value)) {
                        alert("用户名必须是数字、字母或下划线");
                        return false;
                    }

                    if (document.getElementById('passwordid').value == "") {
                        alert("密码不能为空");
                        return false;
                    }
                    if (document.getElementById('passwordid').value.length < 6) {
                        alert("密码长度必须大于6位");
                        return false;
                    }
                    return true;
                }
                var $ = mdui.$;
                var inst = new mdui.Dialog('#dialog');
            </script>
            <form name="form1" method="post" action="login2.do"
            onsubmit="return checkfrom()">
                <div id="login-username" class="mdui-textfield">
                    <label class="mdui-textfield-label">手机号</label>
                    <input class="mdui-textfield-input" id='usernameid' name="username" type="text"/>
                    <div class="mdui-textfield-error"></div>
                </div>
                <div id="login-password" class="mdui-textfield">
                    <label class="mdui-textfield-label">密码</label>
                    <input class="mdui-textfield-input password-input" id='passwordid' name="password" type="password"/>
                    <div class="mdui-textfield-error"></div>
                </div>

                <div id="login-actions" class="mdui-card-actions">
                    <input type="submit" name="Submit5" value="登录"
                            class="mdui-btn mdui-btn-raised mdui-color-blue-a200 action-btn">
                    </button>
                </div>
            </form>
            <div class="mdui-dialog" id="dialog">
                <div class="mdui-dialog-title">登录失败</div>
                <div class="mdui-dialog-content">你输入的手机号或者密码错误！</div>
                <div class="mdui-dialog-actions">
                    <a href="index.do"><button class="mdui-btn mdui-ripple" mdui-dialog-confirm>确认</button></a>
                </div>
            </div>

        </div>
    </div>
    <%@ include file="z-beian.html"%>
</div>
<script>document.getElementById("home").classList.add("mdui-list-item-active");</script>
<%@ include file="z-footer.html"%>
</body>
</html>
