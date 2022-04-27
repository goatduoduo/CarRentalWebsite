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
    <title>汽车租赁网站</title>
    <%@ include file="z-head1.html" %>
</head>
<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-theme-layout-auto">
<div class="mdui-container">
    <%@ include file="z-bodyTopUser.html" %>
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
                            ${bean.name }
                        </td>
                    </tr>
                    <tr>
                        <td>身份证：</td>
                        <td>
                            ${bean.identity}
                        </td>
                    </tr>

                    <tr>
                        <td>用户名：</td>
                        <td><input class="mdui-textfield-input" name='userName' value="${bean.userName}" type='text' id='nameid'
                                   style="width: 300px;"/>
                        </td>
                    </tr>

                    <tr>
                        <td>手机号码：</td>
                        <td><input class="mdui-textfield-input" name='cellPhone' value="${bean.cellPhone }" type='text' id='tejid'
                                   style="width: 300px;"/>
                        </td>
                    </tr>

                    <tr>
                        <td>驾驶证号：</td>
                        <td><input class="mdui-textfield-input" name='licenseNumber' value="${bean.licenseNumber }" type='text' id='licenseNumberid'
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
        <div class="mdui-card-primary">
            <form name="form2" method="post" action="uploadLicense2.do" onsubmit="return checkfrom()"
                  enctype="multipart/form-data">
                <div class="mdui-typo-headline mdui-m-y-2">
                    证件信息
                </div>
                <table class="mdui-table mdui-table-hoverable update-little-table table-text-center">
                    <tr>
                        <td>证件状态：</td>
                        <td>${exam}
                        </td>
                    </tr>
                    <tr>
                        <td>驾驶证领取日期：</td>
                        <td><input class="mdui-textfield-input" name='receiveDate' type='date' id='receiveDate' value="${license.receiveDate }"/>
                        </td>
                    </tr>
                    <tr>
                        <td>驾驶证过期日期：</td>
                        <td><input class="mdui-textfield-input" name='expireDate' type='date' id='expireDate' value="${license.expireDate }"/>
                        </td>
                    </tr>

                    <tr>
                        <td>上传身份证、驾驶证和信用卡：</td>
                        <td><input name="prodFile1" type="file" id='uploadfile1id' style="width: 300px;"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <input type="submit" name="Submit5" value="更新证件"
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