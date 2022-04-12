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
    <title>管理系统</title>
    <%@ include file="z-head1.html"%>
</head>
<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-theme-layout-auto">
<div class="mdui-container">
    <%@ include file="z-bodyTopAdmin.html"%>
    <div class="mdui-row mdui-row-gapless mdui-m-a-1" >
        <div class="mdui-col-xs-4">管理员界面</div>
    </div>
    <div class="mdui-row">
        <div class="mdui-col-xs-6 mdui-col-sm-4 mdui-col-md-3 mdui-m-y-1">
            <a href="carlist.do">
                <div class="mdui-card mdui-hoverable mdui-card-aligned">
                    <div class="mdui-card-media">
                        <img src="../../img/admin.jpg" alt="汽车查询"/>
                    </div>
                    <div class="mdui-card-primary">
                        <div class="mdui-card-primary-title">还车确认</div>
                        <div class="mdui-card-primary-subtitle">完成车辆归还流程</div>
                    </div>
                </div>
            </a>
        </div>
        <div class="mdui-col-xs-6 mdui-col-sm-4 mdui-col-md-3 mdui-m-y-1">
            <a href="reserveList.do">
                <div class="mdui-card mdui-hoverable mdui-card-aligned">
                    <div class="mdui-card-media">
                        <img src="../../img/admin.jpg" alt="我的预定"/>
                    </div>
                    <div class="mdui-card-primary">
                        <div class="mdui-card-primary-title">证件审核</div>
                        <div class="mdui-card-primary-subtitle">审核顾客的证件</div>
                    </div>
                </div>
            </a>
        </div>
        <div class="mdui-col-xs-6 mdui-col-sm-4 mdui-col-md-3 mdui-m-y-1">
            <a href="guestLog.do">
                <div class="mdui-card mdui-hoverable mdui-card-aligned">
                    <div class="mdui-card-media">
                        <img src="../../img/admin.jpg" alt="租车日志"/>
                    </div>
                    <div class="mdui-card-primary">
                        <div class="mdui-card-primary-title">发布车辆</div>
                        <div class="mdui-card-primary-subtitle">发布新的车辆</div>
                    </div>
                </div>
            </a>
        </div>
        <div class="mdui-col-xs-6 mdui-col-sm-4 mdui-col-md-3 mdui-m-y-1">
            <a href="userupdate.do">
                <div class="mdui-card mdui-hoverable mdui-card-aligned">
                    <div class="mdui-card-media">
                        <img src="../../img/admin.jpg" alt="编辑信息"/>
                    </div>
                    <div class="mdui-card-primary">
                        <div class="mdui-card-primary-title">车辆管理</div>
                        <div class="mdui-card-primary-subtitle">编辑或删除车辆信息</div>
                    </div>
                </div>
            </a>
        </div>
        <div class="mdui-col-xs-6 mdui-col-sm-4 mdui-col-md-3 mdui-m-y-1">
            <a href="usermoney.do" target="_blank"
               rel="nofollow">
                <div class="mdui-card mdui-hoverable mdui-card-aligned">
                    <div class="mdui-card-media">
                        <img src="../../img/admin.jpg" alt="财务管理"/>
                    </div>
                    <div class="mdui-card-primary">
                        <div class="mdui-card-primary-title">用户管理</div>
                        <div class="mdui-card-primary-subtitle">编辑或注销用户</div>
                    </div>
                </div>
            </a>
        </div>
        <div class="mdui-col-xs-6 mdui-col-sm-4 mdui-col-md-3 mdui-m-y-1">
            <a href="usermoney.do" target="_blank"
               rel="nofollow">
                <div class="mdui-card mdui-hoverable mdui-card-aligned">
                    <div class="mdui-card-media">
                        <img src="../../img/admin.jpg" alt="财务管理"/>
                    </div>
                    <div class="mdui-card-primary">
                        <div class="mdui-card-primary-title">日志追踪</div>
                        <div class="mdui-card-primary-subtitle">提供全方面的追踪功能</div>
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
