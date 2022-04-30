<%--
  Created by IntelliJ IDEA.
  User: qhj08
  Date: 2022/4/13
  Time: 19:59
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
    <div class="mdui-container mdui-m-y-4">
        <form action="${url }" method="post">
            <div class="mdui-col-xs-1">
                <span class="mdui-text-color-white-100 mdui-m-r-1">品牌筛选</span>
            </div>
            <div class="mdui-col-xs-3">
                <input class="mdui-textfield-input" placeholder="品牌" name="brand" type="text"/>
            </div>
            <div class="mdui-col-xs-3">
                <span class="mdui-text-color-white-100 mdui-m-x-1">车型筛选</span>
                <select class="mdui-select" mdui-select name="carTypeId">
                    <option value="" selected="selected">任意车型</option>
                    <option value="1">经济型</option>
                    <option value="2">纯电动</option>
                    <option value="3">舒适型</option>
                    <option value="4">SUV</option>
                    <option value="5">商务车</option>
                    <option value="6">豪华型</option>
                    <option value="7">跑车</option>
                    <option value="8">房车</option>
                    <option value="9">特殊</option>
                </select>
            </div>
            <div class="mdui-col-xs-3">
                <span class="mdui-text-color-white-100 mdui-m-x-2">日租报价</span>
                <select class="mdui-select" mdui-select name="DailyRent">
                    <option value="0,10000" selected="selected">任意价格</option>
                    <option value="0,150">150元以下</option>
                    <option value="150,250">150-250元</option>
                    <option value="250,400">250-400元</option>
                    <option value="400,800">400-800元</option>
                    <option value="800,1200">800-1200元</option>
                    <option value="1200,10000">1200元以上</option>
                </select>
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
                    <td>图片</td>
                    <td>品牌</td>
                    <td>简要</td>
                    <td>日租</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${list}" var="bean">
                    <tr>
                        <td>
                        <div class="mdui-card-media">
                            <img class=".mdui-img-rounded  img-obj" src="<%=basePath %>${bean.picPath }" alt="车辆图片" height="60px"/>
                        </div>
                        </td>
                        <td>${bean.brand }</td>
                        <td>${bean.brief}</td>
                        <td>${bean.dailyRent }</td>
                        <td>
                            <a href="carupdate.do?id=${bean.carInfoId }">
                                <button class="mdui-btn mdui-btn-raised mdui-ripple" >
                                    <div class="mdui-text-color-white">编辑信息</div>
                                </button>
                            </a>

                            <a href="deltaprice.do?id=${bean.carInfoId }">
                                <button class="mdui-btn mdui-btn-raised mdui-ripple" >
                                    <div class="mdui-text-color-white">设置淡旺季变化</div>
                                </button>
                            </a>

                            <a href="cardelete.do?id=${bean.carInfoId }">
                                <button class="mdui-btn mdui-btn-raised mdui-ripple" >
                                    <div class="mdui-text-color-white">删除车辆</div>
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
<script>document.getElementById("carlist").classList.add("mdui-list-item-active");</script>
<%@ include file="z-footer.html" %>
</body>
</html>
