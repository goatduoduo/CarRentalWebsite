<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<head>
    <title>汽车租赁网站</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <LINK href="qtimages/style.css" type=text/css rel=stylesheet>

    <style type="text/css">
        <!--
        .STYLE2 {	color: #0066CC;
            font-weight: bold;
        }
        -->
    </style>
    <style type="text/css">
        <!--
        .STYLE1 {color: #FFFFFF}
        .STYLE5 {color: #CCFFCC;
            font-size: 26pt;
        }
        .STYLE6 {color: #288848}
        -->
    </style>
</head>
<BODY>
<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
    <TR height=28>
        <TD background=images/title_bg1.jpg>当前位置:---》》${title }</TD></TR>
    <TR>
        <TD bgColor=#b1ceef height=1></TD></TR>
    <TR height=20>
        <TD background=images/shadow_bg.jpg></TD></TR></TABLE>

<%@ include file="qttop.jsp"%>
<TABLE height=8 cellSpacing=0 cellPadding=0 width=1002 align=center
       bgColor=#ffffff border=0>
    <TBODY>
    <TR>
        <TD></TD></TR></TBODY></TABLE>
<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=1>

    <TR >
        <TD align="center" >用户名</TD>
        <TD align="center" >余额</TD>
    </TR>
            <TD align="center" >${userName }</TD>
            <TD align="center" >${curMoney }</TD>


        </TR>

    <TR >
        <form action="userimpose.do" method="post">
            提现:<input name="imposes" type="text" value="${imposes }" />
            &nbsp;&nbsp; 元 <input
                value="提现" type="submit" />

        </form>
        <form action="userrecharge.do" method="post">
            充值:<input name="recharge" type="text" value="${recharge }" />
            &nbsp;&nbsp; 元 <input
                value="充值" type="submit" />

        </form>

    </TR>


</TABLE>
<%@ include file="qtdown.jsp"%>
</BODY></HTML>
