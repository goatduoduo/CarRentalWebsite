package com.duoduo.controller;

import com.duoduo.model.*;
import com.duoduo.service.*;
import com.duoduo.util.CRWUtil;
import com.duoduo.util.DateUtil;
import com.duoduo.util.FileUtil;
import com.duoduo.util.PagerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

@Controller
@RequestMapping(value = "/user")
public class UserController {
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

    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<User> userList = userService.findAll();
        for (User user : userList) {
            System.out.println("id : " + user.getID());
            System.out.println("name : " + user.getName());
        }
        return "hello";
    }

    @RequestMapping("/index.do")
    public String testindex() {
        return "/new/userIndex";
    }

    //车辆列表
    @RequestMapping("/carlist.do")
    public String carlist(HttpServletRequest request, String pagenum, String brand, String carTypeId, String DailyRent) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

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
        if(DailyRent==null || DailyRent.equals("")) DailyRent="0,10000";
        //查询列表x
        BigDecimal min = CRWUtil.getFirst(DailyRent);
        BigDecimal max = CRWUtil.getSecond(DailyRent);
        List<CarBrief> list = carService.selectBriefList((currentpage - 1) * pageSize, pageSize, brand, carTypeId, min, max);
        for(CarBrief e:list){
            //自动生成简历
            e.setBrief();
        }

        //列表返回页面
        request.setAttribute("list", list);

        //获取总数量
        int total = carService.selectBriefCount(brand, carTypeId, min, max);

        //分页信息返回页面
        request.setAttribute("pagerinfo", PagerUtil.getPagerNormal(total, pageSize,
                currentpage, "carlist.do", "共有" + total + "条记录"));


        request.setAttribute("title", "车辆");


        return "/new/carlist";

    }

    @RequestMapping("/carview.do")
    public String carview(HttpServletRequest request, int id) throws ParseException {

        Car bean = carService.selectBeanById(id);
        RentPrice rentPrice = rentPriceService.selectPriceById(id);
        List<PriceChange> priceChanges = rentPriceService.selectDeltaPriceById(id);

        request.setAttribute("bean", bean);
        bean.setCarYear(CRWUtil.scale( DateUtil.yearsBetween(bean.getCreateTime(),new java.util.Date()),1));
        bean.setCarMile(CRWUtil.scale( bean.getCarYear()*20000,2));
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

    //跳转预定租车页面
    @RequestMapping("/reserveadd.do")
    public String yudingadd(HttpServletRequest request, HttpServletResponse response, int carid) {

        PrintWriter writer = this.getPrintWriter(response);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("qiantai");

        if (user == null) {
            writer.print("<script  language='javascript'>alert('请先登录');window.location.href='login.do';</script>");
            return null;
        }
        //没有驾驶证或者驾驶证不通过的也不可以哦
        if (userService.hasAvailableLicense(user.getID()) < 1) {
            writer.print("<script  language='javascript'>alert('需要有效驾驶证，请在个人信息中提交');window.location.href='index.do';</script>");
            return null;
        }

        Car car = carService.selectBeanById(carid);

        request.setAttribute("car", car);

        //user = userService.selectBeanById(user.getID());

        request.setAttribute("user", user);


        request.setAttribute("url", "reserveadd2.do?carid=" + carid);
        request.setAttribute("title", "预定租车");

        return "/new/reserveadd";

    }

    //预定租车操作
    @RequestMapping("/reserveadd2.do")
    public void reserveadd2(HttpServletRequest request, HttpServletResponse response,
                            int carid,String name,String cellPhone,String rentDays) throws IOException {

        PrintWriter writer = this.getPrintWriter(response);

        Car car = carService.selectBeanById(carid);
        name=FileUtil.changeCharset(name,"UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("qiantai");
        //获取租金
        RentPrice rentPrice = rentPriceService.selectPriceById(car.getCarInfoId());
        //todo 插入预计租车时间属性
        //当钱不够支付押金时,拒绝服务!
        if (moneyService.canAfford(user.getID(), rentPrice.getDeposit()).doubleValue() < 0) {
            writer.print("<script  language='javascript'>alert('需要有效驾驶证，请在个人信息中提交');window.location.href='.';</script>");
            return;
        }

        //完成支付并写入日志
        moneyService.payOrReturn(user.getID(), rentPrice.getDeposit());
        rentLogService.insertLog(name, cellPhone, user.getID(), car.getCarInfoId(), "已预定",
                rentPrice.getDeposit(), car.getLicensePlate(),Integer.parseInt( rentDays));
        Calendar c=Calendar.getInstance();
        c.add( Calendar.DATE,Integer.parseInt( rentDays));
        //租赁状态更新
        carService.updateRentStatus2(car.getCarInfoId(), user.getCellPhone(), user.getID(), "rented",name, (java.sql.Date)c.getTime());
        writer.print("<script  language='javascript'>alert('操作成功');window.location.href='reserveList.do'; </script>");

    }

    //查询已经预定的
    @RequestMapping("/reserveList.do")
    public String reserveList(HttpServletRequest request, String pagenum, String brand) {

        //查询条件返回页面
        if (brand != null && !"".equals(brand)) {

            request.setAttribute("brand", brand);
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("qiantai");

        //分页功能默认第一页
        int currentpage = 1;
        //获取当前页
        if (pagenum != null) {
            currentpage = Integer.parseInt(pagenum);
        }
        //一旦预定无法取消！
        //查询列表
        List<Reservation> list = reservationService.selectUserReservation((currentpage - 1) * pageSize,
                pageSize, user.getID(), brand);
        //yudingService.selectBeanList((currentpage - 1)
        //* pageSize, pageSize,chepai,null,user.getId(),0,null);

        //列表返回页面
        request.setAttribute("list", list);

        //获取总数量
        int total = reservationService.selectUserReservationCount(user.getID(), brand);

        //分页信息返回页面
        request.setAttribute("pagerinfo", PagerUtil.getPagerNormal(total, pageSize,
                currentpage, "reserveList.do", "共有" + total + "条记录"));


        request.setAttribute("title", "我的预定");


        return "/new/reserveList";

    }

    //查询用户自己的日志
    @RequestMapping("/guestLog.do")
    public String guestLog(HttpServletRequest request, String pagenum, HttpServletResponse response,String brand) throws UnsupportedEncodingException {
        //todo：搜索功能先挂在那里
        HttpSession session = request.getSession();
        if (Objects.equals(brand, "")) {
            brand = null;
        } else {
            brand = FileUtil.changeCharset(brand, "UTF-8");
        }
        User user = (User) session.getAttribute("qiantai");
        //分页功能默认第一页
        int currentpage = 1;
        //获取当前页
        if (pagenum != null) {
            currentpage = Integer.parseInt(pagenum);
        }
        //查询
        List<RentLog> list = rentLogService.selectUserRentLog(user.getID(), (currentpage - 1) * pageSize, pageSize,brand);
        //获取总数量
        int total = rentLogService.selectUserRentLogCount(user.getID(),brand);
        //列表返回页面
        request.setAttribute("list", list);

        //分页信息返回页面
        request.setAttribute("pagerinfo", PagerUtil.getPagerNormal(total, pageSize,
                currentpage, "reserveList.do", "共有" + total + "条记录"));


        request.setAttribute("title", "我的预定");
        return "/new/logview";
    }

    //更新信息
    @RequestMapping("/userupdate.do")
    public String userupdate(HttpServletRequest request) {

        request.setAttribute("title", "个人信息维护");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("qiantai");


        User bean = userService.selectBeanById(user.getID());
        UserLicense license=userLicenseService.selectUserLicense(user.getID());

        request.setAttribute("bean", bean);
        if(license!=null){
            if(license.getExamineStatus()==0){
                request.setAttribute("exam", "等待审核");
            }
            else if(license.getExamineStatus()==1){
                request.setAttribute("exam", "审核通过");
            }
            else{
                request.setAttribute("exam", "审核不通过");
            }
            request.setAttribute("license", license);
        }

        return "/new/userupdate";

    }

    //个人信息维护操作
    @RequestMapping("/userupdate2.do")
    public void userupdate2(HttpServletRequest request, HttpServletResponse response, String userName, String cellPhone) {

        PrintWriter writer = this.getPrintWriter(response);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("qiantai");


        User bean = userService.selectBeanById(user.getID());


        bean.setUserName(userName);
        bean.setName(cellPhone);

        userService.updateBean(bean);

        writer.print("<script language=javascript>alert('修改成功');window.location.href='userupdate.do';</script>");


    }

    //顾客上传三证件供审核
    @RequestMapping("/uploadLicense.do")
    public String uploadLicense(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = this.getPrintWriter(response);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("qiantai");

        if (user == null) {
            writer.print("<script  language='javascript'>alert('请先登录');window.location.href='login.do';</script>");
            return null;
        }

        request.setAttribute("user", user);

        request.setAttribute("title", "证件上传");

        return "uploadLicense";
    }

    //顾客上传证件后供管理员审核
    @RequestMapping("/uploadLicense2.do")
    public void uploadLicense2(HttpServletRequest request, HttpServletResponse response, UserLicense bean, MultipartFile prodFile1) {
        PrintWriter writer = this.getPrintWriter(response);


        if (prodFile1 == null || prodFile1.getSize() <= 0) {
            this.getPrintWriter(response).print("<script language=javascript>alert('必须上传证件');" +
                    "window.location.href='uploadLicense.do?';</script>");
            return;
        }


        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("qiantai");

        bean.setLicenseNumber(user.getLicenseNumber());
        bean.setID(user.getID());
        bean.setExamineStatus(0);

        String file = FileUtil.uploadFile(request, prodFile1);
        bean.setPath(file);

        userLicenseService.deleteBean(user.getID());
        userLicenseService.insertBean(bean);

        writer.print("<script  language='javascript'>alert('操作成功');window.location.href='uploadLicense.do'; </script>");

    }

    //申请注销
    @RequestMapping("/accountCancellation.do")
    public void accountCancellation(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = this.getPrintWriter(response);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("qiantai");
        //todo 如果还有租赁中的，不许注销
        //未提现到0也不可以哦
        //增加注销申请
        rentLogService.insertLog(user.getName(), user.getCellPhone(), user.getID(), 0, "申请注销", BigDecimal.valueOf(0), null,null);
        //return "accountCancellation";
    }

    //安全退出操作
    @RequestMapping("/loginout.do")
    public void loginout(HttpServletRequest request, HttpServletResponse response) {

        PrintWriter writer = this.getPrintWriter(response);

        HttpSession session = request.getSession();
        session.removeAttribute("qiantai");

        writer.print("<script language=javascript>window.location.href='index.do';</script>");


    }

    //用户管理
    //进入充值与提现页面
    @RequestMapping("/usermoney.do")
    public String usermoney(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("qiantai");
        request.setAttribute("userName", user.getUserName());
        moneyService.getUserCurMoney(user.getID());
        BigDecimal money=moneyService.getUserCurMoney(user.getID());
        request.setAttribute("curMoney", money);
        return "/new/usermoney";
    }

    //充值
    @RequestMapping("/userrecharge.do")
    public void userrecharge(HttpServletRequest request, HttpServletResponse response, BigDecimal recharge) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("qiantai");
        moneyService.payOrReturn(user.getID(), recharge.multiply(new BigDecimal(-1)));
        rentLogService.insertLog(user.getName(), user.getCellPhone(), user.getID(), 0, "充值", recharge, "",null);
        this.getPrintWriter(response).print("<script language=javascript>alert('操作成功');window.location.href='usermoney.do';</script>");
    }

    //提现
    @RequestMapping("/userimpose.do")
    public void userimpose(HttpServletRequest request, HttpServletResponse response, BigDecimal impose) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("qiantai");
        moneyService.payOrReturn(user.getID(), impose.multiply(new BigDecimal(1)));
        rentLogService.insertLog(user.getName(), user.getCellPhone(), user.getID(), 0, "提现", impose, "",null);
        this.getPrintWriter(response).print("<script language=javascript>alert('操作成功');window.location.href='usermoney.do';</script>");
    }

    @RequestMapping("/login.do")
    public String login(HttpServletRequest request) {

        request.setAttribute("title", "用户登录");

        return "new/login";

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


            writer.print("<script language=javascript>window.location.href='index.do';</script>");

        }

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

    //来注册吧！
    @RequestMapping("/register.do")
    public String register(HttpServletRequest request) {

        request.setAttribute("title", "用户注册");

        return "/new/register";

    }

    //用户注册操作
    @RequestMapping("/register2.do")
    public void register2(HttpServletRequest request, HttpServletResponse response, User user) {
        //当表单中组件的 name 和对象的属性名一致时 可以在controller  方法的参数中 写一个对应的对应，spring会自动封装成一个对象
        PrintWriter writer = this.getPrintWriter(response);
        if (userService.existCellphone(user.getCellPhone()) > 0) {

            writer.print("<script language=javascript>alert('该手机号已被注册，注册失败！');window.location.href='register.do';</script>");

            return;
        }

        if (userService.existIdentity(user.getIdentity()) > 0) {

            writer.print("<script language=javascript>alert('该身份证已经存在，注册失败！');window.location.href='register.do';</script>");

            return;
        }
        Date date = new Date(System.currentTimeMillis());
        user.setCreateTime(date);
        user.setRole(2);

        userService.insertBean(user);

        writer.print("<script language=javascript>alert('注册成功');window.location.href='login.do';</script>");


    }
}
