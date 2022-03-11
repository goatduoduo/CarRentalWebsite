<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<html>
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
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<%@ include file="qttop.jsp"%>


<TABLE height=8 cellSpacing=0 cellPadding=0 width=1002 align=center
       bgColor=#ffffff border=0>
    <TBODY>
    <TR>
        <TD></TD></TR></TBODY></TABLE>
<TABLE cellSpacing=0 cellPadding=0 width=1002 align=center bgColor=#ffffff
       border=0>
    <TBODY>
    <TR>
        <TD vAlign=top>
            <TABLE height=8 cellSpacing=0 cellPadding=0 width="100%" align=center
                   border=0>
                <TBODY>
                <TR>
                    <TD>
                        <img src="qtimages/index.jpg">
                    </TD></TR></TBODY></TABLE>
        </TD>
        <TD width=8></TD>
        <TD vAlign=top width=220>
            <%@ include file="qtleft.jsp"%></TD></TR></TBODY></TABLE>
<TABLE height=8 cellSpacing=0 cellPadding=0 width=1002 align=center
       bgColor=#ffffff border=0>
    <TBODY>
    <TR>
        <TD></TD></TR></TBODY></TABLE>
<TABLE height=8 cellSpacing=0 cellPadding=0 width=1002 align=center
       bgColor=#ffffff border=0>
    <TBODY>
    <TR>
        <TD></TD></TR></TBODY></TABLE>
<TABLE style="BORDER-RIGHT: #cccccc 1px solid; BORDER-LEFT: #cccccc 1px solid"
       cellSpacing=0 cellPadding=0 width=1002 align=center bgColor=#ffffff border=0>
    <TBODY>
    <TR>
        <TD
                style="PADDING-RIGHT: 8px; PADDING-LEFT: 20px; PADDING-BOTTOM: 8px; PADDING-TOP: 8px"
                vAlign=top>

        </TD></TR></TBODY></TABLE>
<TABLE height=4 cellSpacing=0 cellPadding=0 width=1002 align=center
       bgColor=#ffffff border=0>
    <TBODY>
    <TR>
        <TD width=5><IMG height=3 src="qtimages/zjgdj_64.gif" width=5></TD>
        <TD background=qtimages/zjgdj_65.gif><IMG height=3
                                                  src="qtimages/zjgdj_65.gif" width=1></TD>
        <TD width=4><IMG height=3 src="qtimages/zjgdj_67.gif"
                         width=4></TD></TR></TBODY></TABLE>
<TABLE height=8 cellSpacing=0 cellPadding=0 width=1002 align=center
       bgColor=#ffffff border=0>
    <TBODY>
    <TR>
        <TD></TD></TR></TBODY></TABLE>
<%@ include file="qtdown.jsp"%>
</body>
</html>