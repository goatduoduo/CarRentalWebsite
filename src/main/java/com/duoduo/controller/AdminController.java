package com.duoduo.controller;

import com.duoduo.model.*;
import com.duoduo.service.*;
import com.duoduo.util.CRWUtil;
import com.duoduo.util.DateUtil;
import com.duoduo.util.FileUtil;
import com.duoduo.util.PagerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Resource
    private UserService userService;
    @Resource
    private CarService carService;
    @Resource
    private RentPriceService rentPriceService;
    @Resource
    private RentLogService rentLogService;
    @Resource
    private MoneyService moneyService;
    @Resource
    private ReservationService reservationService;
    @Resource
    private UserLicenseService userLicenseService;
    @Resource
    private RentStatusService rentStatusService;

    private int pageSize = 12;

    @RequestMapping("/login.do")
    public String login(HttpServletRequest request) {

        request.setAttribute("title", "用户登录");

        return "/new/login";

    }

    //用户登录操作
    @RequestMapping("/login2.do")
    public void login2(HttpServletRequest request, HttpServletResponse response, String username, String password) {

        PrintWriter writer = this.getPrintWriter(response);


        User bean = userService.userlogin(username, password);


        if (bean == null) {

            writer.print("<script language=javascript>alert('用户名或者密码错误，登录失败！');window.location.href='login.do';</script>");


        } else {

            HttpSession session = request.getSession();
            session.setAttribute("qiantai", bean);
            session.setAttribute("username", bean.getUserName());
            session.setAttribute("role", bean.getRole());

            if (bean.getRole() == 2)
                writer.print("<script language=javascript>window.location.href='user/index.do';</script>");
            else
                writer.print("<script language=javascript>window.location.href='admin/index.do';</script>");

        }

    }

    @RequestMapping("/index.do")
    public String index() {
        return "new/adminIndex";
    }

    //车辆列表
    @RequestMapping("/carlist.do")
    public String carlist(HttpServletRequest request, String pagenum, Car bean) {

        //查询条件返回页面
        if (bean.getBrand() != null && !"".equals(bean.getBrand())) {

            request.setAttribute("brand", bean.getBrand());
        }

        if (bean.getCarTypeId() >= 0) {

            request.setAttribute("ctId", bean.getCarTypeId());
        }
        if (bean.getColor() != null && !"".equals(bean.getColor())) {

            request.setAttribute("color", bean.getColor());
        }

        //分页功能默认第一页
        int currentpage = 1;
        //获取当前页
        if (pagenum != null) {
            currentpage = Integer.parseInt(pagenum);
        }

        //查询列表
        List<Car> list = carService.selectBeanList((currentpage - 1) * pageSize, pageSize, bean);

        //列表返回页面
        request.setAttribute("list", list);

        //获取总数量
        int total = carService.selectBeanCount(bean);

        //分页信息返回页面
        request.setAttribute("pagerinfo", PagerUtil.getPagerNormal(total, pageSize,
                currentpage, "carlist.do", "共有" + total + "条记录"));


        request.setAttribute("title", "车辆");


        return "carlist";

    }

    @RequestMapping("/carview.do")
    public String carview(HttpServletRequest request, int id) throws ParseException {

        Car bean = carService.selectBeanById(id);
        RentPrice rentPrice = rentPriceService.selectPriceById(id);
        List<PriceChange> priceChanges = null;
        try{
            priceChanges = rentPriceService.selectDeltaPriceById(id);
        }
        catch (Exception e){

        }
        request.setAttribute("bean", bean);
        bean.setCarYear(CRWUtil.scale(DateUtil.yearsBetween(bean.getCreateTime(), new java.util.Date()), 1));
        bean.setCarMile(CRWUtil.scale(bean.getCarYear() * 20000, 2));
        //TODO:在前端显示淡旺季价格变化
        request.setAttribute("price", rentPrice);
        request.setAttribute("title", "车辆详情");
        //request.getSession()
        HttpSession session = request.getSession();
        int role;
        try {
            role = (int) session.getAttribute("role");
        } catch (NullPointerException e) {
            role = 0;//0是未登录的
        }
        request.setAttribute("role", role);
        return "/new/carview";

    }

    //安全退出操作
    @RequestMapping("/loginout.do")
    public void loginout(HttpServletRequest request, HttpServletResponse response) {

        PrintWriter writer = this.getPrintWriter(response);

        HttpSession session = request.getSession();
        session.removeAttribute("qiantai");

        writer.print("<script language=javascript>alert('退出成功');window.location.href='index.do';</script>");


    }

    //用户管理
    //人员列表
    @RequestMapping("/userlist2.do")
    public String userlist(HttpServletRequest request, String pagenum, String username) {

        //查询条件返回页面
        if (username != null && !"".equals(username)) {

            request.setAttribute("username", username);
        }


        //分页功能默认第一页
        int currentpage = 1;
        //获取当前页
        if (pagenum != null) {
            currentpage = Integer.parseInt(pagenum);
        }

        //查询列表
        List<User> list = userService.selectBeanList((currentpage - 1)
                * pageSize, pageSize, username);

        //列表返回页面
        request.setAttribute("list", list);

        //获取总数量
        int total = userService.selectBeanCount(username);

        //分页信息返回页面
        request.setAttribute("pagerinfo", PagerUtil.getPagerNormal(total, pageSize,
                currentpage, "userlist.do", "共有" + total + "条记录"));

        //查询按钮
        request.setAttribute("url", "userlist.do");

        //添加，更新，删除等按钮
        request.setAttribute("url2", "user");

        request.setAttribute("title", "人员管理");

        return "new/userlist";

    }

    //跳转到修改人员页面
    @RequestMapping("/userupdate22.do")
    public String userupdate(HttpServletRequest request, int id) {

        User bean = userService.selectBeanById(id);

        request.setAttribute("bean", bean);

        request.setAttribute("url", "userupdate2do.do?id=" + id);

        request.setAttribute("title", "修改人员");

        return "new/userupdate2";

    }

    //修改人员操作
    @RequestMapping("/userupdate2.do")
    public void userupdate2(HttpServletResponse response, User bean) {

        userService.updateBean(bean);


        this.getPrintWriter(response).print("<script language=javascript>alert('操作成功');window.location.href='userlist2.do';</script>");
    }

    //修改人员操作
    @RequestMapping("/userdelete2.do")
    public void userdelete(HttpServletResponse response, int id) {
        //todo 删除前需要确保他已经提现完成，且没有车辆在驾驶
        userService.deleteBean(id);


        this.getPrintWriter(response).print("<script language=javascript>alert('操作成功');window.location.href='userlist.do';</script>");
    }

    //修改人员操作
    @RequestMapping("/licenseexamine.do")
    public String licenseexamine(HttpServletRequest request) {
        //request.setAttribute("status", status);
        int status=0;
        List<UserLicense> userLicenses = userLicenseService.selectBeanList(status);
        for(UserLicense u:userLicenses){
            if(u.getExamineStatus()==0){
                u.setDetail("等待审核");
            }
            else if(u.getExamineStatus()==1){
                u.setDetail("通过");
            }
            else{
                u.setDetail("不通过");
            }
        }
        //列表返回页面
        request.setAttribute("list", userLicenses);
        return "new/licenseexamine";
    }

    @RequestMapping("/download.do")
    public String download(HttpServletResponse response,HttpServletRequest request, String filename) {
        //文件的下载操作
        String fileName = request.getParameter("filename");
        System.out.println("2");
        try {
            // 得到要下载的文件
            File file = new File( fileName);

            // 如果文件不存在
            if (!file.exists()) {
                request.setAttribute("message", "您要下载的资源已被删除！！");
                System.out.println("您要下载的资源已被删除！！");
            }
            // 处理文件名
            String realname = fileName.substring(fileName.indexOf("_") + 1);
            // 设置响应头，控制浏览器下载该文件
            response.setHeader("content-disposition", "attachment;filename="
                    + URLEncoder.encode(realname, "UTF-8"));
            // 读取要下载的文件，保存到文件输入流
            FileInputStream in = new FileInputStream( fileName);
            // 创建输出流
            OutputStream out = response.getOutputStream();
            // 创建缓冲区
            byte buffer[] = new byte[1024];
            int len = 0;
            // 循环将输入流中的内容读取到缓冲区当中
            while ((len = in.read(buffer)) > 0) {
                // 输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
            }
            // 关闭文件输入流
            in.close();
            // 关闭输出流
            out.close();
        } catch (Exception e) {

        }
        return "new/licenseexamine";
    }

    //审核通过
    @RequestMapping("/licenseexamine1.do")
    public void licenseexamine1(HttpServletResponse response, int id) {
        userLicenseService.setExamineStatus(id, 1);
        this.getPrintWriter(response).print("<script language=javascript>alert('审核通过设置成功');window.location.href='licenseexamine.do';</script>");
    }

    //审核不通过
    @RequestMapping("/licenseexamine2.do")
    public void licenseexamine2(HttpServletResponse response, int id) {
        userLicenseService.setExamineStatus(id, 2);
        this.getPrintWriter(response).print("<script language=javascript>alert('审核不通过设置成功');window.location.href='licenseexamine.do';</script>");
    }

    //转到跳转页面
    @RequestMapping("/caradd.do")
    public String caradd(HttpServletRequest request) {

        request.setAttribute("url", "caradd2.do");

        request.setAttribute("title", "添加车辆");

        return "new/caradd";

    }

    //添加车辆操作
    @RequestMapping("/caradd2.do")
    public void caradd2(HttpServletResponse response, HttpServletRequest request, String licensePlate,
                        String brand, String drivingLicense, int carTypeId, MultipartFile prodFile,
                        BigDecimal dailyRent, BigDecimal deposit, BigDecimal price, BigDecimal insurance,
                        BigDecimal serviceCharge, String location,String color, Date createTime,
                        int seats, String info) {


        if (prodFile == null || prodFile.getSize() <= 0) {
            this.getPrintWriter(response).print("<script language=javascript>alert('车辆图片不能为空');" +
                    "window.location.href='caradd.do';</script>");
            return;
        }

        String pic = FileUtil.uploadFile(request, prodFile);
        int carID = carService.getCarID();
        //定义price（已弃用，保留）
        price=new BigDecimal(450000);
        //长到离谱的创建函数
        carService.insertBeanByID(carID, licensePlate, brand, drivingLicense,
                carTypeId, pic, dailyRent, deposit, price, insurance, serviceCharge,
                location,color, createTime, seats, info);

        this.getPrintWriter(response).print("<script language=javascript>alert('操作成功');" +
                "window.location.href='carlist2.do';</script>");
    }

    //管理员的车辆页面
    @RequestMapping("/carlist2.do")
    public String carlist2(HttpServletRequest request, String pagenum, String brand, String carTypeId, String DailyRent) throws UnsupportedEncodingException {
        //查询条件返回页面
        if (brand != null && !"".equals(brand)) {

            request.setAttribute("brand", brand);
        }

        if (carTypeId != null && !"".equals(carTypeId)) {

            request.setAttribute("carTypeId", carTypeId);
        }
        if (DailyRent != null && !"".equals(DailyRent)) {

            request.setAttribute("DailyRent", DailyRent);
        }

        //分页功能默认第一页
        int currentpage = 1;
        //获取当前页
        if (pagenum != null) {
            currentpage = Integer.parseInt(pagenum);
        }
        if (Objects.equals(brand, "")) {
            brand = null;
        } else {
            brand = FileUtil.changeCharset(brand, "UTF-8");
        }
        if (Objects.equals(carTypeId, "")) carTypeId = null;
        if (DailyRent == null || DailyRent.equals("")) DailyRent = "0,10000";
        //查询列表x
        BigDecimal min = CRWUtil.getFirst(DailyRent);
        BigDecimal max = CRWUtil.getSecond(DailyRent);
        List<CarBrief> list = carService.selectBriefList((currentpage - 1) * pageSize, pageSize, brand, carTypeId, min, max);
        for (CarBrief e : list) {
            //自动生成简历
            e.setBrief();
        }

        //列表返回页面
        request.setAttribute("list", list);

        //获取总数量
        int total = carService.selectBriefCount(brand, carTypeId, min, max);

        //分页信息返回页面
        request.setAttribute("pagerinfo", PagerUtil.getPagerNormal(total, pageSize,
                currentpage, "carlist2.do", "共有" + total + "条记录"));


        request.setAttribute("title", "车辆");

        return "new/carlist2";
    }

    @RequestMapping("/carupdate.do")
    public String carupdate(HttpServletRequest request, int id) {
        Car bean = carService.selectBeanById(id);
        RentPrice rentPrice=rentPriceService.selectPriceById(id);
        //堆信息

        request.setAttribute("bean", bean);
        request.setAttribute("rentPrice", rentPrice);

        request.setAttribute("url", "carupdate2.do?id=" + id);

        request.setAttribute("title", "修改车辆");
        return "new/carupdate";
    }
    @RequestMapping("/carupdate2.do")
    public String carupdate2(HttpServletResponse response,HttpServletRequest request, int id) {
//        //todo 更新车辆 当前位置，更新基本信息
//        Car bean = carService.selectBeanById(id);
//        RentPrice rentPrice=rentPriceService.selectPriceById(id);
//        //堆信息
//
//        request.setAttribute("bean", bean);
//        request.setAttribute("rentPrice", rentPrice);
//
//        request.setAttribute("url", "carupdate2.do?id=" + id);
//
//        request.setAttribute("title", "修改车辆");
        this.getPrintWriter(response).print("<script language=javascript>alert('操作成功');" +
                "window.location.href='carlist2.do';</script>");
        return "new/carlist2";
    }
    @RequestMapping("/deltaprice.do")
    public String deltaPrice(HttpServletRequest request, int id){
        //进入淡旺季价格变化页面
        Car bean = carService.selectBeanById(id);
        RentPrice rentPrice=rentPriceService.selectPriceById(id);
        List< PriceChange> priceChanges=rentPriceService.selectDeltaPriceById(id);
        request.setAttribute("bean", bean);
        request.setAttribute("rentPrice", rentPrice);
        request.setAttribute("priceChanges", priceChanges);
        return "new/deltaprice";
    }
    //todo 增加和删除
    @RequestMapping("/deltaprice_del.do")
    public void deltaPrice_del(HttpServletResponse response,HttpServletRequest request, int id,Date startTime,Date endTime,BigDecimal deltaPrice){
        rentPriceService.delDeltaPrice(id, startTime, endTime, deltaPrice);
        this.getPrintWriter(response).print("<script language=javascript>alert('删除成功');" +
                "window.location.href='deltaprice.do?id="+id+"';</script>");
    }
    @RequestMapping("/deltaprice_add.do")
    public void deltaPrice_add(HttpServletResponse response,HttpServletRequest request, int id,Date startTime,Date endTime,BigDecimal deltaPrice){
        rentPriceService.addDeltaPrice(id, startTime, endTime, deltaPrice);
        this.getPrintWriter(response).print("<script language=javascript>alert('添加成功');" +
                "window.location.href='deltaprice.do?id="+id+"';</script>");
    }

    @RequestMapping("/cardelete.do")
    public void cardelete(HttpServletResponse response, int id) {

        carService.deleteBean(id);


        this.getPrintWriter(response).print("<script language=javascript>alert('操作成功');window.location.href='carlist2.do';</script>");
    }

    //进入充值与提现页面
    @RequestMapping("/usermoney.do")
    public String usermoney(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("qiantai");
        request.setAttribute("userName", user.getUserName());
        request.setAttribute("curMoney", moneyService.getUserCurMoney(user.getID()));
        return "usermoney";
    }

    //租车日志
    @RequestMapping("/logview.do")
    public String logview(HttpServletRequest request, HttpServletResponse response, String pagenum) {
        //分页功能默认第一页
        int currentpage = 1;
        //获取当前页
        if (pagenum != null) {
            currentpage = Integer.parseInt(pagenum);
        }

        //查询列表
//        List<Car> list = carService.selectBeanList((currentpage - 1)
//                * pageSize, pageSize,null);
        List<RentLog> list = rentLogService.selectRentLog((currentpage - 1) * pageSize, pageSize);

        //列表返回页面
        request.setAttribute("list", list);

        //获取总数量
        int total = rentLogService.selectRentLogCount();

        //分页信息返回页面
        request.setAttribute("pagerinfo", PagerUtil.getPagerNormal(total, pageSize,
                currentpage, "carlist2.do", "共有" + total + "条记录"));

        //查询按钮
        request.setAttribute("url", "carlist2.do");

        //添加，更新，删除等按钮
        request.setAttribute("url2", "carlist2.do");

        request.setAttribute("title", "日志管理");

        return "new/logview2";
    }

    //人员列表
    @RequestMapping("/carstatus.do")
    public String carstatus(HttpServletRequest request, String pagenum, String username) {

        //分页功能默认第一页
        int currentpage = 1;
        //获取当前页
        if (pagenum != null) {
            currentpage = Integer.parseInt(pagenum);
        }

        //查询列表
        List<RentStatus> list = rentStatusService.selectRented((currentpage - 1)
                * pageSize, pageSize);

        //列表返回页面
        request.setAttribute("list", list);

        //获取总数量
        int total = rentStatusService.countRented();

        //分页信息返回页面
        request.setAttribute("pagerinfo", PagerUtil.getPagerNormal(total, pageSize,
                currentpage, "userlist.do", "共有" + total + "条记录"));

        //确认归还按钮
        request.setAttribute("url", "userlist.do");

        request.setAttribute("title", "人员管理");

        return "new/carstatus";

    }

    @RequestMapping("/carreturn.do")
    public String carreturn(HttpServletRequest request, HttpServletResponse response, int carID, int userID,BigDecimal depositSubs) {
        RentPrice rentPrice = rentPriceService.selectPriceById(carID);
        List<PriceChange> priceChange = rentPriceService.selectDeltaPriceById(carID);
        //计算需要的钱
        //根据车辆ID选择租赁状态
        RentStatus rentStatus = rentStatusService.selectOne(carID);

        //获取开始、结束、日期
        Date start = rentStatus.getTime();
        Date today = new Date(System.currentTimeMillis());//租车结束
        //计算租车淡旺季价格变化
        BigDecimal deltaPrice = getPrice(start, today, priceChange);
        //车辆一共租赁了几天
        int rentDays=DateUtil.daysBetween(start,today);
        //计算总花费（由于低于押金，一般是负数的）
        BigDecimal total = rentPrice.getDailyRent().multiply(new BigDecimal(rentDays))
                .add(deltaPrice).add(rentPrice.getInsurance()).add(rentPrice.getServiceCharge()).subtract(rentPrice.getDeposit());
        //统计用于显示
        rentPrice.setDailyRent(rentPrice.getDailyRent().multiply(new BigDecimal(rentDays)));
        request.setAttribute("today", today);
        request.setAttribute("time", rentStatus.getTime());
        request.setAttribute("deltaPrice", deltaPrice);
        request.setAttribute("returnTime ", rentStatus.getReturnTime());

        request.setAttribute("rentPrice", rentPrice);
        request.setAttribute("carID",carID);
        request.setAttribute("userID",userID);
        //输入押金扣除
        depositSubs=(BigDecimal) request.getAttribute("depositSubs");
        if(depositSubs==null){
            depositSubs=new BigDecimal(0);
        }
        request.setAttribute("depositSubs",depositSubs);
        total.subtract(depositSubs);
        request.setAttribute("total", total);
        //session传价格
        HttpSession session=request.getSession();
        session.setAttribute("total", total);
        session.setAttribute("rentPrice",rentPrice);
        return "new/carreturn";
    }

    //确认归还
    @RequestMapping("/carreturn2.do")
    public void carreturn2(HttpServletRequest request, HttpServletResponse response,BigDecimal depositSubs) {
        HttpSession session=request.getSession();
        PrintWriter writer = this.getPrintWriter(response);
        RentPrice rentPrice = (RentPrice) session.getAttribute("rentPrice");
        BigDecimal price = (BigDecimal) session.getAttribute("total");
        price= price.add(depositSubs);//计算额外扣除的押金
        int userID = carService.selectUser(rentPrice.getCarInfoId());
        User user = userService.selectBeanById(userID);
        moneyService.payOrReturn(userID, price); //多退少补
        rentLogService.insertLog(user.getName(), user.getCellPhone(), userID, rentPrice.getCarInfoId(), "还车流程", price, null, null);
        carService.updateRentStatus(rentPrice.getCarInfoId(), null, 0, "available");
        //完成操作后移除元素以防泄露
        session.removeAttribute("total");
        session.removeAttribute("rentPrice");
        writer.print("<script language=javascript>alert('还车成功');window.location.href='carstatus.do';</script>");
    }

    public BigDecimal getPrice(Date start, Date end, List<PriceChange> priceChange)  {
        //用于计算淡旺季价格变化的……
        BigDecimal ans = new BigDecimal(0);
        for (PriceChange a : priceChange) {
            int ansDays = Math.max(DateUtil.daysBetween(
                    DateUtil.max(a.getStartTime(), start), DateUtil.min(a.getEndTime(), end)), 0);
            ans=ans.add(a.getDeltaPrice().multiply(new BigDecimal(ansDays)));
        }

        //输出淡旺季价格总变化（可能为负数）
        return ans;
    }

    // 获取输出对象
    public PrintWriter getPrintWriter(HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return writer;
    }
}
