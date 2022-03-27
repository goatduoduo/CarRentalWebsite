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
    <title>准备租赁</title>
    <%@ include file="z-head1.html" %>
</head>
<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-theme-layout-auto">
<div class="mdui-container">
    <%@ include file="z-bodyTopUser.html" %>
    <%--    start here--%>
    <form name="form1" method="post" action="${url }" onsubmit="return checkfrom()" >
        <div class="mdui-typo-display-2">即将租赁的汽车：${car.brand }</div>
        <div class="mdui-typo-display-1-opacity">租赁人身份证号码：${user.identity }</div>
        <div class="mdui-textfield mdui-textfield-floating-label">
            <label class="mdui-textfield-label">姓名</label>
            <input class="mdui-textfield-input" name='name' value="${user.name }" type="text"/>
            <label class="mdui-textfield-label" >手机号</label>
            <input class="mdui-textfield-input" name='cellPhone' value="${user.cellPhone }" type="text"/>
            <label class="mdui-textfield-label">预计租赁天数</label>
            <input class="mdui-textfield-input" name='rentDays' value="3" type="text"/>
        </div>

        <div >
            <%--                <i class="mdui-icon material-icons">search</i>--%>
            <input class="mdui-btn  mdui-color-blue-600 mdui-ripple" value="提交！" type="submit" />

        </div>
    </form>
    <%@ include file="z-beian.html" %>
</div>
<script>document.getElementById("home").classList.add("mdui-list-item-active");

function checkform() {
    if (document.getElementById('nameid').value == "") {
        alert("客户姓名不能为空");
        return false;
    }
    if (document.getElementById('cellPhoneid').value == "") {
        alert("联系电话不能为空");
        return false;
    }
    if (document.getElementById('rentDays').value == "") {
        alert("请填写租赁天数");
        return false;
    }
    return true;
}
</script>
<%@ include file="z-footer.html" %>
</body>
</html>
