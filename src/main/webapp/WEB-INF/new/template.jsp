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
    <title>汽车租赁网站模板页</title>
    <%@ include file="z-head1.html"%>
</head>
<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-theme-layout-auto">
<div class="mdui-container">
    <%@ include file="z-bodyTop.html"%>
    <div class="mdui-row mdui-row-gapless mdui-m-a-1" >
        <div class="mdui-col-xs-4">这是主界面</div>
    </div>
    <div class="mdui-row">
        <div class="mdui-col-xs-6 mdui-col-sm-4 mdui-col-md-3">
            <a href="/apk">
                <div class="mdui-card mdui-hoverable mdui-card-aligned">
                    <div class="mdui-card-media">
                        <img src="https://cdn.cocservice.top/homepage/apkdownload.jpg" alt="安装包下载"/>
                    </div>
                    <div class="mdui-card-primary">
                        <div class="mdui-card-primary-title">安装包下载</div>
                        <div class="mdui-card-primary-subtitle">各渠道商版本下载</div>
                    </div>
                </div>
            </a>
        </div>
        <div class="mdui-col-xs-6 mdui-col-sm-4 mdui-col-md-3">
            <a href="/update">
                <div class="mdui-card mdui-hoverable mdui-card-aligned">
                    <div class="mdui-card-media">
                        <img src="https://cdn.cocservice.top/homepage/update_data.jpg" alt="升级数据"/>
                    </div>
                    <div class="mdui-card-primary">
                        <div class="mdui-card-primary-title">升级数据</div>
                        <div class="mdui-card-primary-subtitle">游戏数据一览无余</div>
                    </div>
                </div>
            </a>
        </div>
        <div class="mdui-col-xs-6 mdui-col-sm-4 mdui-col-md-3">
            <a href="/p">
                <div class="mdui-card mdui-hoverable mdui-card-aligned">
                    <div class="mdui-card-media">
                        <img src="https://cdn.cocservice.top/homepage/passages.jpg" alt="攻略教程"/>
                    </div>
                    <div class="mdui-card-primary">
                        <div class="mdui-card-primary-title">攻略教程</div>
                        <div class="mdui-card-primary-subtitle">收录优质内容</div>
                    </div>
                </div>
            </a>
        </div>
        <div class="mdui-col-xs-6 mdui-col-sm-4 mdui-col-md-3">
            <a href="/sponsor">
                <div class="mdui-card mdui-hoverable mdui-card-aligned">
                    <div class="mdui-card-media">
                        <img src="https://cdn.cocservice.top/homepage/spons.jpg" alt="付费服务&联系作者"/>
                    </div>
                    <div class="mdui-card-primary">
                        <div class="mdui-card-primary-title">付费服务&联系作者</div>
                        <div class="mdui-card-primary-subtitle">账号交易、代充等广告</div>
                    </div>
                </div>
            </a>
        </div>
        <div class="mdui-col-xs-6 mdui-col-sm-4 mdui-col-md-3">
            <a href="https://www.yuque.com/books/share/7a36c5ba-3c5e-492b-82c6-38fa813f9367" target="_blank"
               rel="nofollow">
                <div class="mdui-card mdui-hoverable mdui-card-aligned">
                    <div class="mdui-card-media">
                        <img src="https://cdn.cocservice.top/homepage/base_design.jpg" alt="阵型收录"/>
                    </div>
                    <div class="mdui-card-primary">
                        <div class="mdui-card-primary-title">阵型收录</div>
                        <div class="mdui-card-primary-subtitle">晴天阵型收集</div>
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
