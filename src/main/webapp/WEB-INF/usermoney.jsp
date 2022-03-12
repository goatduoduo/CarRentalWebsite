<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
    <META http-equiv=Content-Type content="text/html; charset=gb2312">
    <LINK href="css/admin.css" type="text/css" rel="stylesheet">

</HEAD>
<BODY>
<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
    <TR height=28>
        <TD background=images/title_bg1.jpg>当前位置:---》》${title }</TD></TR>
    <TR>
        <TD bgColor=#b1ceef height=1></TD></TR>
    <TR height=20>
        <TD background=images/shadow_bg.jpg></TD></TR></TABLE>

<%@ include file="qttop.jsp"%>

<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=1>

    <TR >
        <TD align="center" >用户名</TD>
        <TD align="center" >余额</TD>
    </TR>
            <TD align="center" >${userName }</TD>
            <TD align="center" >${curMoney }</TD>


        </TR>

    <TR >
        <form action="userrecharge.do" method="post">
            充值:<input name="recharge" type="text" value="${recharge }" />
            &nbsp;&nbsp; 元 <input
                value="充值" type="submit" />

        </form>
        <form action="userimpose.do" method="post">
            提现:<input name="impose" type="text" value="${impose }" />
            &nbsp;&nbsp; 元 <input
                value="提现" type="submit" />

        </form>
    </TR>


</TABLE>
<%@ include file="qtdown.jsp"%>
</BODY></HTML>
