<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
    <META http-equiv=Content-Type content="text/html; charset=gb2312">
    <LINK href="css/admin.css" type="text/css" rel="stylesheet">
    <script language="javascript" type="text/javascript">

        function checkform()
        {
            // if (document.getElementById('chepaiid').value=="")
            // {
            //     alert("车牌号不能为空");
            //     return false;
            // }
            // if (document.getElementById('chejiaid').value=="")
            // {
            //     alert("车架号不能为空");
            //     return false;
            // }
            // if (document.getElementById('pinpaiid').value=="")
            // {
            //     alert("品牌不能为空");
            //     return false;
            // }
            // if (document.getElementById('xinghaoid').value=="")
            // {
            //     alert("型号不能为空");
            //     return false;
            // }
            // if (document.getElementById('uploadfileid').value=="")
            // {
            //     alert("车辆图片不能为空");
            //     return false;
            // }
            // if (document.getElementById('priceid').value=="")
            // {
            //     alert("车价不能为空");
            //     return false;
            // }
            // if (document.getElementById('xszid').value=="")
            // {
            //     alert("行驶证号不能为空");
            //     return false;
            // }
            // if (document.getElementById('yanseid').value=="")
            // {
            //     alert("颜色不能为空");
            //     return false;
            // }
            // if (document.getElementById('zujinid').value=="")
            // {
            //     alert("租金不能为空");
            //     return false;
            // }
            //
            //
            // //验证正整数
            // var reg1 =  /^\d+$/;
            //
            // if (document.getElementById('zujinid').value.match(reg1) == null)
            // {
            //     alert("租金必须为正整数");
            //     return false;
            //
            // }

            return true;

        }


    </script>

</HEAD>
<BODY>
<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
    <TR height=28>
        <TD background=images/title_bg1.jpg>当前位置:---》》${title }</TD></TR>
    <TR>
        <TD bgColor=#b1ceef height=1></TD></TR>
    <TR height=20>
        <TD background=images/shadow_bg.jpg></TD></TR></TABLE>
<form action="${url }" method="post" onsubmit="return checkform()" enctype="multipart/form-data">
    <TABLE cellSpacing=0 cellPadding=0 width="80%" align=center border=1>

        <TR height=>
            <TD align="center" size="25" >车牌号:</TD>
            <TD align="center">
                <input  type="text" name="licensePlate"  id='licensePlateid'  size="50"   />

            </TD>
        </TR>

        <TR height=>
            <TD align="center" size="25">品牌:</TD>
            <TD align="center">
                <input  type="text" name="brand"  id='brandid'  size="50"  />

            </TD>
        </TR>

        <TR height=>
            <TD align="center" size="25">行驶证号:</TD>
            <TD align="center">
                <input  type="text" name="drivingLicense"  id='drivingLicenseid'  size="50"  />

            </TD>
        </TR>
        <TR height=>
            <TD align="center" size="25">出厂日期:</TD>
            <TD align="center">
                <input  type="date" name="createTime"  id='ctimeid'  size="50"  />

            </TD>
        </TR>

        <TR height=>
            <TD align="center" size="25">型号:</TD>
            <TD align="center">
                <select name="carTypeId" >
                    <option value="1" selected="selected">经济型</option>
                    <option value="2">纯电动</option>
                    <option value="3">舒适型</option>
                    <option value="4" >SUV</option>
                    <option value="5" >商务车</option>
                    <option value="6" >豪华型</option>
                    <option value="7" >跑车</option>
                    <option value="8" >房车</option>
                    <option value="9" >特殊</option>
                </select>

            </TD>
        </TR>

        <TR height=>
            <TD align="center" size="25">车辆图片:</TD>
            <TD align="center">
                <input name="prodFile"  type="file"  id='uploadfileid'  size="50"  />

            </TD>
        </TR>

        <TR height=>
            <TD align="center" size="25">日租报价:</TD>
            <TD align="center">
                <input  type="text" name="dailyRent"  id='dailyRentid'  size="50"  />

            </TD>
            <TD align="center" size="25">押金:</TD>
            <TD align="center">
                <input  type="text" name="deposit"  id='depositid'  size="50"  />

            </TD>
            <TD align="center" size="25">整车价格:</TD>
            <TD align="center">
                <input  type="text" name="price"  id='priceid'  size="50"  />

            </TD>
            <TD align="center" size="25">车保:</TD>
            <TD align="center">
                <input  type="text" name="insurance"  id='insuranceid'  size="50"  />

            </TD>
            <TD align="center" size="25">手续费:</TD>
            <TD align="center">
                <input  type="text" name="serviceCharge"  id='serviceChargeid'  size="50"  />

            </TD>
        </TR>





        <TR height=>
            <TD align="center" size="25">租车地点:</TD>
            <TD align="center">
                <input  type="text" name="location"  id='locationid'  size="50"  />

            </TD>
        </TR>
        <TR height=>
            <TD align="center" size="25">座位数:</TD>
            <TD align="center">
                <input  type="text" name="seats"  id='seatsid'  size="50"  />

            </TD>
        </TR>

        <TR height=>
            <TD align="center" size="25">颜色:</TD>
            <TD align="center">
                <input  type="text" name="color"  id='colorid'  size="50"  />

            </TD>
        </TR>

        <TR height=>
            <TD align="center" size="25">简介:</TD>
            <TD align="center">
                <input  type="text" name="info"  id='infoid'  size="50"  />

            </TD>
        </TR>





        <TR height=>
            <TD align="center" > 操作：</TD>
            <TD align="center">
                <input type="submit" value="提交" style="width: 60px" />
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input  onclick="javascript:history.go(-1);" style="width: 60px" type="button" value="返回" />

            </TD>
        </TR>

    </TABLE>
</form>
</BODY></HTML>
