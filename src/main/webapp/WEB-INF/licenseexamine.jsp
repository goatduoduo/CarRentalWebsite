<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
    <title>汽车租赁网站</title>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">

    <LINK href="qtimages/style.css" type=text/css rel=stylesheet>
    <style >
        <!--
        .STYLE2 {
            color: #0066CC;
            font-weight: bold;
        }
        -->
    </style>
    <style type="text/css">
        <!--
        .STYLE1 {
            color: #FFFFFF
        }

        .STYLE5 {
            color: #CCFFCC;
            font-size: 26pt;
        }

        .STYLE6 {
            color: #288848
        }
        -->
    </style>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<%@ include file="qttop.jsp"%>


<TABLE height=8 cellSpacing=0 cellPadding=0 width=1002 align=center
       bgColor=#ffffff border=0>
    <TBODY>
    <TR>
        <TD></TD>
    </TR>
    </TBODY>
</TABLE>
<TABLE cellSpacing=0 cellPadding=0 width=1002 align=center
       bgColor=#ffffff border=0>
    <TBODY>
    <TR>
        <TD vAlign=top>
            <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                <TBODY>
                <TR>
                    <TD width=8></TD>
                    <TD vAlign=top>
                        <TABLE height=27 cellSpacing=0 cellPadding=0 width="100%"
                               background=qtimages/zjgdj_79.gif border=0>
                            <TBODY>
                            <TR>
                                <TD
                                        style="BACKGROUND-POSITION: left 50%; PADDING-LEFT: 12px; FONT-WEIGHT: bold; FONT-SIZE: 10.5pt; COLOR: #3d3d3d; BACKGROUND-REPEAT: no-repeat"
                                        width=118 background=qtimages/zjgdj_77.gif><IMG
                                        src="qtimages/zjgdj_sy_26.gif" align=absMiddle>&nbsp;${title }</TD>
                                <TD style="PADDING-RIGHT: 1px" align=right
                                    background=qtimages/zjgdj_79.gif></TD>
                                <TD width=7></TD>
                            </TR>
                            </TBODY>
                        </TABLE>

                        <form action="${url }" method="post">
                            &nbsp;状态:<%-- <input
											name="xinghao" type="text" value="${xinghao }" /> --%>
                            <select name="status" >
                                <option value="0" selected="selected">待审核</option>
                                <option value="1">已通过</option>
                                <option value="2">未通过</option>
                            </select>
                            &nbsp;&nbsp; <input
                                value="搜索" type="submit" />

                        </form>

                        <TABLE
                                style="BACKGROUND-POSITION: 50% top; BACKGROUND-REPEAT: repeat-x"
                                cellSpacing=0 cellPadding=8 width="100%"
                                background=qtimages/zjgdj_82.gif border=0>
                            <TBODY>
                            <TR>
                                <TD style="PADDING-TOP: 8px" vAlign=top height=185><TABLE
                                        cellSpacing=0 cellPadding=0 width="100%" border=0>
                                    <TBODY>
                                    <TR>
                                        <TD style="PADDING-TOP: 8px" align=middle class="newsline"><table
                                                width="98%" border="0" align="center" cellpadding="3"
                                                cellspacing="1" bordercolor="#FA9090"
                                                style="border-collapse: collapse">

                                            <tr>
                                                <td>图片</td>
                                                <td>用户ID</td>
                                                <td>手机号</td>
                                                <td>领取日期</td>
                                                <td>过期日期</td>
                                                <td>审核状态</td>
                                                <td>操作</td>



                                            </tr>

                                            <c:forEach items="${list}" var="bean">

                                                <tr>

                                                    <td><img src="<%=basePath %>${bean.picPath }"    height="70" border="0" /></td>
                                                    <td>${bean.ID }</td>
                                                    <td>${bean.cellPhone }</td>
                                                    <td>${bean.receiveDate }</td>
                                                    <td>${bean.expireDate }</td>
                                                    <TD  >
                                                        <c:if test="${bean.examineStatus==0 }">待审核</c:if>
                                                        <c:if test="${bean.examineStatus==1 }">已通过</c:if>
                                                        <c:if test="${bean.examineStatus==2 }">不通过</c:if>
                                                    </TD>
                                                    <td><a href="carview.do?id=${bean.carInfoId }">查看详情</a>
                                                        &nbsp;
                                                        <c:if test="${bean.examineStatus==0 ||bean.examineStatus==2 }">
                                                        <input
                                                                type="button" class="btn btn-danger" value="审核通过"
                                                                href="licenseexamine1.do?id=${bean.ID }"/>
                                                        </c:if>

                                                        <c:if test="${bean.examineStatus==0 ||bean.examineStatus==1 }">
                                                            <input
                                                                    type="button" class="btn btn-danger" value="审核不通过"
                                                                    href="licenseexamine2.do?id=${bean.ID }"/>
                                                        </c:if>
                                                        <input
                                                                type="button" class="btn btn-danger" value="预定租车"
                                                                onClick="if(confirm('确认预定?')){window.location.href='reserveadd.do?carid=${bean.carInfoId }'}return false;"/>

                                                    </td>

                                                </tr>

                                            </c:forEach>

                                        </table> <br>

                                    </TR>


                                    </TBODY>
                                </TABLE></TD>
                            </TR>
                            </TBODY>
                        </TABLE>
                    </TD>
                </TR>
                </TBODY>
            </TABLE>
            <TABLE height=8 cellSpacing=0 cellPadding=0 width="100%"
                   align=center border=0>
                <TBODY>
                <TR>
                    <TD></TD>
                </TR>
                </TBODY>
            </TABLE>
        </TD>
        <TD width=8></TD>
        <TD vAlign=top width=220><%@ include file="qtleft.jsp"%></TD>
    </TR>
    </TBODY>
</TABLE>
<TABLE height=8 cellSpacing=0 cellPadding=0 width=1002 align=center
       bgColor=#ffffff border=0>
    <TBODY>
    <TR>
        <TD></TD>
    </TR>
    </TBODY>
</TABLE>
<TABLE height=8 cellSpacing=0 cellPadding=0 width=1002 align=center
       bgColor=#ffffff border=0>
    <TBODY>
    <TR>
        <TD></TD>
    </TR>
    </TBODY>
</TABLE>
<TABLE
        style="BORDER-RIGHT: #cccccc 1px solid; BORDER-LEFT: #cccccc 1px solid"
        cellSpacing=0 cellPadding=0 width=1002 align=center bgColor=#ffffff
        border=0>
    <TBODY>
    <TR>
        <TD
                style="PADDING-RIGHT: 8px; PADDING-LEFT: 20px; PADDING-BOTTOM: 8px; PADDING-TOP: 8px"
                vAlign=top></TD>
    </TR>
    </TBODY>
</TABLE>
<TABLE height=4 cellSpacing=0 cellPadding=0 width=1002 align=center
       bgColor=#ffffff border=0>
    <TBODY>
    <TR>
        <TD width=5><IMG height=3 src="qtimages/zjgdj_64.gif" width=5></TD>
        <TD background=qtimages/zjgdj_65.gif><IMG height=3
                                                  src="qtimages/zjgdj_65.gif" width=1></TD>
        <TD width=4><IMG height=3 src="qtimages/zjgdj_67.gif" width=4></TD>
    </TR>
    </TBODY>
</TABLE>
<TABLE height=8 cellSpacing=0 cellPadding=0 width=1002 align=center
       bgColor=#ffffff border=0>
    <TBODY>
    <TR>
        <TD></TD>
    </TR>
    </TBODY>
</TABLE>
<%@ include file="qtdown.jsp"%>
</body>
</html>