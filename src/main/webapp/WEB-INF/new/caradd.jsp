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
    <title>发布车辆</title>
    <%@ include file="z-head1.html" %>
</head>
<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-theme-layout-auto">
<div class="mdui-container">
    <%@ include file="z-bodyTopAdmin.html" %>
    <%--    start here--%>
    <div class="mdui-row">
        <div class="mdui-typo-title mdui-m-y-2">发布车辆</div>
    </div>
    <form name="form1" method="post" action="caradd2.do" onsubmit="return checkfrom()"
          enctype="multipart/form-data">
        <div class="mdui-row">
            <div class="mdui-col-xs-12 mdui-col-md-8">
                <table class="mdui-table mdui-table-hoverable update-little-table table-text-center">
                    <tbody>
                    <TR>
                        <TD>车牌号:</TD>
                        <TD>
                            <input class="mdui-textfield-input" type="text" name="licensePlate" id='licensePlateid'
                                   size="50"/>

                        </TD>
                    </TR>

                    <TR>
                        <TD>品牌:</TD>
                        <TD>
                            <input class="mdui-textfield-input" type="text" name="brand" id='brandid' size="50"/>

                        </TD>
                    </TR>

                    <TR>
                        <TD>行驶证号:</TD>
                        <TD>
                            <input class="mdui-textfield-input" type="text" name="drivingLicense" id='drivingLicenseid'
                                   size="50"/>

                        </TD>
                    </TR>
                    <TR>
                        <TD>出厂日期:</TD>
                        <TD>
                            <input class="mdui-textfield-input" type="date" name="createTime" id='ctimeid' size="50"/>

                        </TD>
                    </TR>

                    <TR>
                        <TD>型号:</TD>
                        <TD>
                            <select name="carTypeId" class="mdui-select">
                                <option value="1" selected="selected">经济型</option>
                                <option value="2">纯电动</option>
                                <option value="3">舒适型</option>
                                <option value="4">SUV</option>
                                <option value="5">商务车</option>
                                <option value="6">豪华型</option>
                                <option value="7">跑车</option>
                                <option value="8">房车</option>
                                <option value="9">特殊</option>
                            </select>

                        </TD>
                    </TR>

                    <TR height=>
                        <TD>车辆图片:</TD>
                        <TD>
                            <input name="prodFile" type="file" id='uploadfileid' size="50"/>

                        </TD>
                    </TR>

                    </tbody>
                </table>
            </div>
            <div class="mdui-col-xs-12 mdui-col-md-4">
                <table class="mdui-table mdui-table-hoverable update-little-table table-text-center">
                    <tbody>
                    <tr>
                        <td>日租报价</td>
                        <td><input class="mdui-textfield-input" type="text" name="dailyRent" id='dailyRentid'
                        /></td>
                    </tr>
                    <tr>
                        <td>押金</td>
                        <td><input class="mdui-textfield-input" type="text" name="deposit" id='depositid'
                        /></td>
                    </tr>
                    <tr>
                        <td>保险费</td>
                        <td><input class="mdui-textfield-input" type="text" name="insurance" id='priceid'
                        /></td>
                    </tr>
                    <tr>
                        <td>手续费</td>
                        <td><input class="mdui-textfield-input" type="text" name="serviceCharge" id='insuranceid'/></td>
                    </tr>

                    </tbody>
                </table>
            </div>
        </div>
        <div class="mdui-row mdui-m-y-4">
            <div class="mdui-col-xs-12 mdui-col-md-12">
                <table class="mdui-table mdui-table-hoverable update-little-table table-text-center">
                    <tbody>
                    <tr>
                        <td>租车地点</td>
                        <td><input class="mdui-textfield-input" type="text" name="location" id='locationid'/></td>
                    </tr>
                    <tr>
                        <td>座位数</td>
                        <td><input class="mdui-textfield-input" type="text" name="seats" id='seatsid'/></td>
                    </tr>
                    <tr>
                        <td>颜色</td>
                        <td><input class="mdui-textfield-input" type="text" name="color" id='colorid'/></td>
                    </tr>
                    <tr>
                        <td>简介</td>
                        <td><input class="mdui-textfield-input" type="text" name="info" id='infoid'/></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="mdui-row-xs-2 mdui-m-t-2 mdui-m-b-8">
            <div class="mdui-col">
                <input type="submit" name="Submit5" value="提交"
                       class="mdui-btn mdui-btn-raised mdui-color-blue-a200 action-btn"/>
            </div>
            <div class="mdui-col">
                <a href="index.do">
                    <button class="mdui-btn mdui-btn-block mdui-ripple">
                        <div class="mdui-text-color-white">返回</div>
                    </button>
                </a>
            </div>
        </div>
    </form>
    <%@ include file="z-beian.html" %>
</div>
<script>document.getElementById("add").classList.add("mdui-list-item-active");</script>
<%@ include file="z-footer.html" %>
</body>
</html>
