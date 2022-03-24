<%--
  Created by IntelliJ IDEA.
  User: qhj08
  Date: 2022/3/24
  Time: 10:03
  To change this template use File | Settings | File Templates.
  新前端的模板页面，直接复制黏贴就对了！
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>欢迎！</title>
    <%@ include file="z-head1.html"%>
</head>
<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-theme-layout-auto">
<div class="mdui-container">
    <%@ include file="z-bodyTopUser.html"%>
    <div class="mdui-row mdui-row-gapless mdui-m-a-1" >
        <div class="mdui-col-xs-4">这是主界面</div>
    </div>
    <div class="mdui-row">
        <div class="mdui-col-xs-6 mdui-col-sm-4 mdui-col-md-3 mdui-m-y-1">
            <a href="carlist.do">
                <div class="mdui-card mdui-hoverable mdui-card-aligned">
                    <div class="mdui-card-media">
                        <img src="../../img/carList.jpg" alt="汽车查询"/>
                    </div>
                    <div class="mdui-card-primary">
                        <div class="mdui-card-primary-title">汽车查询</div>
                        <div class="mdui-card-primary-subtitle">查询或租赁汽车</div>
                    </div>
                </div>
            </a>
        </div>
        <div class="mdui-col-xs-6 mdui-col-sm-4 mdui-col-md-3 mdui-m-y-1">
            <a href="reserveList.do">
                <div class="mdui-card mdui-hoverable mdui-card-aligned">
                    <div class="mdui-card-media">
                        <img src="../../img/carList.jpg" alt="我的预定"/>
                    </div>
                    <div class="mdui-card-primary">
                        <div class="mdui-card-primary-title">我的预定</div>
                        <div class="mdui-card-primary-subtitle">查看目前我预定的汽车</div>
                    </div>
                </div>
            </a>
        </div>
        <div class="mdui-col-xs-6 mdui-col-sm-4 mdui-col-md-3 mdui-m-y-1">
            <a href="guestLog.do">
                <div class="mdui-card mdui-hoverable mdui-card-aligned">
                    <div class="mdui-card-media">
                        <img src="../../img/carList.jpg" alt="租车日志"/>
                    </div>
                    <div class="mdui-card-primary">
                        <div class="mdui-card-primary-title">租车日志</div>
                        <div class="mdui-card-primary-subtitle">查看我的租车记录</div>
                    </div>
                </div>
            </a>
        </div>
        <div class="mdui-col-xs-6 mdui-col-sm-4 mdui-col-md-3 mdui-m-y-1">
            <a href="userupdate.do">
                <div class="mdui-card mdui-hoverable mdui-card-aligned">
                    <div class="mdui-card-media">
                        <img src="../../img/carList.jpg" alt="编辑信息"/>
                    </div>
                    <div class="mdui-card-primary">
                        <div class="mdui-card-primary-title">编辑信息</div>
                        <div class="mdui-card-primary-subtitle">编辑个人信息</div>
                    </div>
                </div>
            </a>
        </div>
        <div class="mdui-col-xs-6 mdui-col-sm-4 mdui-col-md-3 mdui-m-y-1">
            <a href="usermoney.do" target="_blank"
               rel="nofollow">
                <div class="mdui-card mdui-hoverable mdui-card-aligned">
                    <div class="mdui-card-media">
                        <img src="../../img/carList.jpg" alt="财务管理"/>
                    </div>
                    <div class="mdui-card-primary">
                        <div class="mdui-card-primary-title">财务管理</div>
                        <div class="mdui-card-primary-subtitle">充值或提现并查看自己的流水</div>
                    </div>
                </div>
            </a>
        </div>
    </div>

    <%@ include file="z-beian.html"%>
</div>
<script>document.getElementById("home").classList.add("mdui-list-item-active");</script>
<%@ include file="z-footer.html"%>
</body>
</html>
