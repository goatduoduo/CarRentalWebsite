<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/bootstrap.min.css" type=text/css>
<link href="../css/reset.css" rel="stylesheet" type="text/css" />
<link href="../css/webmain.css" rel="stylesheet" type="text/css" />
<link href="../css/ddsmoothmenu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../scripts/jquery.min.js"></script>
<script type="text/javascript"
	src="../scripts/jquery.KinSlideshow-1.2.1.js"></script>
<script type="text/javascript" src="../scripts/webtry_roll.js"></script>
<script type="text/javascript" src="../scripts/ddsmoothmenu.js"></script>
<script src="../scripts/bootstrap.min.js"></script>


</HEAD>
<TABLE height=26 cellSpacing=0 cellPadding=0 width=1002 align=center
	   bgColor=#ffffff background=../qtimages/zjgdj_02.gif border=0>
	<TBODY>
		<TR>
			<TD align=right width=300></TD>
			<TD align=right></TD>
		</TR>
	</TBODY>
</TABLE>
<TABLE cellSpacing=0 cellPadding=0 width=1002 align=center
	bgColor=#ffffff border=0>
	<TBODY>
		<TR>
			<TD height="188" background="../qtimages/11.jpg"><p>&nbsp;</p>
				<p>&nbsp;</p>
			</TD>
		</TR>
	</TBODY>
</TABLE>
<TABLE cellSpacing=0 cellPadding=0 width=1002 align=center
	bgColor=#ffffff border=0>
	<TBODY>
		<TR>
			<TD height="33" background="../qtimages/22.gif"><table width="98%"
																   border="0" align="center" cellpadding="0" cellspacing="0"
																   class="red">
					<tr>
						<td align="center"><strong><a href="index.do"><font
									class="STYLE1">首页</font></a></strong></td>
						<td align="center"><strong><a href="carlist.do"><font
									class="STYLE1">所有车辆</font></a></strong></td>
									<c:if test="${qiantai==null}">
						<td align="center"><strong><a href="register.do"><font
									class="STYLE1">注册</font></a></strong></td>
						<td align="center"><strong><a href="login.do"><font
									class="STYLE1">登录</font></a></strong></td>
									</c:if>
<%--						用户模块--%>
						<c:if test="${qiantai!=null && role==2}">
							<td align="center"><strong><a ><span
									class="STYLE1">顾客：${username}</span></a></strong></td>
							<td align="center"><strong><a href="reserveList.do"><span
										class="STYLE1">我的预定</span></a></strong></td>
							<td align="center"><strong><a href="guestLog.do"><span
										class="STYLE1">租车日志</span></a></strong></td>
							<td align="center"><strong class=" dropdown"><a
									href="#" class=" dropdown-toggle" data-toggle="dropdown"><font
										class=" dropdown STYLE1">个人信息</font><b class="caret"></b>
										<ul class="dropdown-menu">
											<li><a href="userupdate.do">信息修改</a></li>
											<li class="divider"></li>
											<li><a href="usermoney.do">个人财务</a></li>
											<li class="divider"></li>
											<li><a href="loginout.do">退出登录</a></li>
											<li class="divider"></li>
											<li><a href="accountCancellation.do">注销账号</a></li>
											<li class="divider"></li>
										</ul> </a> </strong></td>

						</c:if>
<%--						管理员模块--%>
						<c:if test="${qiantai!=null && role==1}">
							<td align="center"><strong><a ><span
									class="STYLE1">管理员：${username}</span></a></strong></td>
							<td align="center"><strong><a href="caradd.do"><span
									class="STYLE1">发布车辆</span></a></strong></td>
							<td align="center"><strong><a href="licenseexamine.do?status=1"><span
									class="STYLE1">证件审核</span></a></strong></td>
							<td align="center"><strong><a href="carstatus.do"><span
									class="STYLE1">还车确认</span></a></strong></td>
							<td align="center"><strong class=" dropdown"><a
									href="#" class=" dropdown-toggle" data-toggle="dropdown"><font
									class=" dropdown STYLE1">信息管理</font><b class="caret"></b>
								<ul class="dropdown-menu">
									<li><a href="carlist2.do">车辆管理</a></li>
									<li class="divider"></li>
									<li><a href="userlist.do">用户管理</a></li>
									<li class="divider"></li>
									<li><a href="logview.do">租车日志</a></li>
									<li class="divider"></li>
									<li><a href="loginout.do">退出登录</a></li>
									<li class="divider"></li>
								</ul> </a> </strong></td>


						</c:if>

					</tr>
				</table></TD>
		</TR>
	</TBODY>
</TABLE>