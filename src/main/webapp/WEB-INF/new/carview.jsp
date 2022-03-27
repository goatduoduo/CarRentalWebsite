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
    <title>汽车租赁网站模板页</title>
    <%@ include file="z-head1.html" %>
</head>
<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-theme-layout-auto">
<div class="mdui-container">
    <%@ include file="z-bodyTopUser.html" %>
    <%--    start here--%>
    <div class="mdui-row mdui-typo-title page-title">
        车辆详情
    </div>

    <div class="mdui-row ">
        <div class="mdui-card-media">
            <img class=".mdui-img-rounded  img-obj" height="400px" src="<%=basePath %>${bean.picPath }" alt="车辆图片"/>
        </div>

    </div>
        <div class="mdui-row mdui-m-y-4 ">
            <div class="mdui-col-xs-12  mdui-p-x-3">简介：${bean.info }</div>
        </div>

    <div class="mdui-row">
        <div class="mdui-col-xs-12 mdui-col-md-8">
            <div class="update-property-parent">
                <div class="mdui-card update-property-card">
                    <div class="update-property-key">品牌：</div>
                    <div class="update-property-value">${bean.brand }</div>
                </div>
                <div class="mdui-card update-property-card">
                    <div class="update-property-key">颜色：</div>
                    <div class="update-property-value">${bean.color }</div>
                </div>
                <div class="mdui-card update-property-card">
                    <div class="update-property-key">车牌号：</div>
                    <div class="update-property-value">${bean.licensePlate }</div>
                </div>
                <div class="mdui-card update-property-card">
                    <div class="update-property-key">行驶证：</div>
                    <div class="update-property-value">${bean.drivingLicense }</div>
                </div>
                <div class="mdui-card update-property-card">
                    <div class="update-property-key">座位数：</div>
                    <div class="update-property-value">${bean.seats }</div>
                </div>
                <div class="mdui-card update-property-card">
                    <div class="update-property-key">取车/还车地点：</div>
                    <div class="update-property-value">${bean.location }</div>
                </div>
                <div class="mdui-card update-property-card">
                    <div class="update-property-key">车龄：</div>
                    <div class="update-property-value">${bean.carYear } 年</div>
                </div>
                <div class="mdui-card update-property-card">
                    <div class="update-property-key">里程：</div>
                    <div class="update-property-value">${bean.carMile } 公里</div>
                </div>
<%--                <div class="mdui-card update-property-card flex-item-placeholder"></div>--%>
<%--                <div class="mdui-card update-property-card flex-item-placeholder"></div>--%>
            </div>
        </div>
        <div class="mdui-col-xs-12 mdui-col-md-4">
            <table class="mdui-table mdui-table-hoverable update-little-table table-text-center">
                <tbody>
                <tr>
                    <td>日租</td>
                    <td>${price.dailyRent }￥/天</td>
                </tr>
                <tr>
                    <td>押金</td>
                    <td>${price.deposit }￥</td>
                </tr>
                <tr>
                    <td>保险费</td>
                    <td>${price.insurance }￥</td>
                </tr>
                <tr>
                    <td>手续费</td>
                    <td>${price.serviceCharge }￥</td>
                </tr>

                </tbody>
            </table>
        </div>
    </div>

    <div class="mdui-row-xs-2 mdui-m-t-2 mdui-m-b-8">

        <c:if test="${ price.rentStatus=='available' && role==2 }">
            <div class="mdui-col">
                <a href="reserveadd.do?carid=${bean.carInfoId }">
                    <button class="mdui-btn mdui-btn-block mdui-color-blue-400 mdui-ripple mdui-m-r-1">
                        <div class="mdui-text-color-white">预定租车</div>
                    </button>
                </a>
            </div>

        </c:if>
        <div class="mdui-col">
            <a href="carlist.do">
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
