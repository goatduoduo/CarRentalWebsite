<%--
  Created by IntelliJ IDEA.
  User: qhj08
  Date: 2022/3/28
  Time: 10:39
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
    <div class="mdui-card">
        <div class="mdui-card-primary">
            <form name="form1" method="post" action="register2.do"
                  onsubmit="return checkfrom()">
                <div class="mdui-typo-headline mdui-m-y-2">
                    注册新用户
                </div>
                <div class="mdui-textfield">
                    <label class="mdui-textfield-label">用户名</label>
                    <input class="mdui-textfield-input" id='usernameid' name="userName" type="text"/>
                </div>
                <div class="mdui-textfield">
                    <label class="mdui-textfield-label">密码</label>
                    <input class="mdui-textfield-input" id='passwordid' name="pwd" type="password"/>
                </div>
                <div class="mdui-textfield">
                    <label class="mdui-textfield-label">确认密码</label>
                    <input class="mdui-textfield-input" id='password2id' name="password2" type="password"/>
                </div>
                <div class="mdui-textfield">
                    <label class="mdui-textfield-label">姓名</label>
                    <input class="mdui-textfield-input" id='nameid' name="name" type="text"/>
                </div>
                <div class="mdui-textfield">
                    <label class="mdui-textfield-label">身份证</label>
                    <input class="mdui-textfield-input" id='sfzid' name="identity" type="text"/>
                </div>
                <div class="mdui-textfield">
                    <label class="mdui-textfield-label">手机号码</label>
                    <input class="mdui-textfield-input" id='tejid' name="cellPhone" type="text"/>
                </div>
                <div id="login-actions" class="mdui-card-actions">
                    <input type="submit" name="Submit5" value="注册！"
                           class="mdui-btn mdui-btn-raised mdui-color-blue-a200 action-btn">
                </div>
            </form>
        </div>
    </div>

    <%@ include file="z-beian.html" %>
</div>
<script>
    //document.getElementById("home").classList.add("mdui-list-item-active");

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
    if (document.getElementById('password2id').value != document.getElementById('passwordid').value) {
        alert("确认密码与密码不一致");
        return false;
    }
    if (document.getElementById('nameid').value == "") {
        alert("姓名不能为空");
        return false;
    }
    if (document.getElementById('sfzid').value == "") {
        alert("身份证不能为空");
        return false;
    }

    if (document.getElementById('sfzid').value.length < 6) {
        alert("身份证长度必须等于18位");
        return false;
    }


    if (document.getElementById('tejid').value == "") {
        alert("手机不能为空");
        return false;
    }

    valid = /^0?1[3,4,5,6,7,8,9][0,1,2,3,4,5,6,7,8,9]\d{8}$/;
    if (!valid.test(document.getElementById('tejid').value)) {
        alert("请输入正确的手机格式");
        return false;
    }


    return true;
}
</script>
<%@ include file="z-footer.html" %>
</body>
</html>
