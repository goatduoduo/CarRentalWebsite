package com.duoduo.controller;

import com.duoduo.model.*;
import com.duoduo.service.*;
import com.duoduo.util.FileUtil;
import com.duoduo.util.PagerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Resource
    private UserService userService;
    @Resource
    private CarService carService;
    @Resource
    private RentPriceService rentPriceService;
    //@Resource
    //private RentLogService rentLogService;
    @Resource
    private MoneyService moneyService;
    @Resource
    private ReservationService reservationService;
    @Resource
    private UserLicenseService userLicenseService;

    private int pageSize=12;

    @RequestMapping("/login.do")
    public String login(HttpServletRequest request){

        request.setAttribute("title", "用户登录");

        return "login";

    }
    //用户登录操作
    @RequestMapping("/login2.do")
    public void login2(HttpServletRequest request, HttpServletResponse response, String username, String password){

        PrintWriter writer = this.getPrintWriter(response);


        User bean = userService.userlogin(username, password);



        if(bean==null){

            writer.print("<script language=javascript>alert('用户名或者密码错误，登录失败！');window.location.href='login.do';</script>");


        }else{

            HttpSession session = request.getSession();
            session.setAttribute("qiantai", bean);
            session.setAttribute("username", bean.getUserName());
            session.setAttribute("role",bean.getRole());


            writer.print("<script language=javascript>alert('登录成功');window.location.href='index.do';</script>");

        }

    }
    @RequestMapping("/index.do")
    public String index(HttpServletRequest request,HttpServletResponse response){
        System.out.println(request.getServletContext().getRealPath("/"));
        return "index";
    }
    //车辆列表
    @RequestMapping("/carlist.do")
    public String carlist(HttpServletRequest request, String pagenum, Car bean){

        //查询条件返回页面
        if (bean.getBrand() != null && !"".equals(bean.getBrand())) {

            request.setAttribute("brand", bean.getBrand());
        }

        if (bean.getCarTypeId() >=0) {

            request.setAttribute("ctId", bean.getCarTypeId());
        }
        if (bean.getColor() != null && !"".equals(bean.getColor() )) {

            request.setAttribute("color", bean.getColor() );
        }

        //分页功能默认第一页
        int currentpage = 1;
        //获取当前页
        if (pagenum != null) {
            currentpage = Integer.parseInt(pagenum);
        }

        //查询列表
        List<Car> list = carService.selectBeanList((currentpage - 1)* pageSize, pageSize, bean);

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
    public String carview(HttpServletRequest request,int id){

        Car bean = carService.selectBeanById(id);
        RentPrice rentPrice= rentPriceService.selectPriceById(id);
        List<PriceChange> priceChanges=rentPriceService.selectDeltaPriceById(id);

        request.setAttribute("bean", bean);
        //TODO:在前端显示淡旺季价格变化
        request.setAttribute("price",rentPrice);
        request.setAttribute("title", "车辆详情");
        //request.getSession()
        HttpSession session=request.getSession();
        int role;
        try {
            role= (int) session.getAttribute("role");
        }
        catch (NullPointerException e){
            role=0;//0是未登录的
        }
        request.setAttribute("role",role);
        return "carview";

    }

    //跳转预定租车页面
    @RequestMapping("/reserveadd.do")
    public String yudingadd(HttpServletRequest request,HttpServletResponse response,int carid) {

        PrintWriter writer = this.getPrintWriter(response);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("qiantai");

        if (user == null) {
            writer.print("<script  language='javascript'>alert('请先登录');window.location.href='login.do';</script>");
            return  null;
        }
        //没有驾驶证或者驾驶证不通过的也不可以哦
        if(userService.hasAvailableLicense(user.getID())<1){
            writer.print("<script  language='javascript'>alert('需要有效驾驶证，请在个人信息中提交');window.location.href='index.do';</script>");
            return  null;
        }

        Car car = carService.selectBeanById(carid);

        request.setAttribute("car", car);

        //user = userService.selectBeanById(user.getID());

        request.setAttribute("user", user);


        request.setAttribute("url", "reserveadd2.do?carid="+carid);
        request.setAttribute("title", "预定租车");

        return "reserveadd";

    }
    //预定租车操作
    @RequestMapping("/reserveadd2.do")
    public void reserveadd2(HttpServletRequest request, HttpServletResponse response, int carid) throws IOException{

        PrintWriter writer = this.getPrintWriter(response);

        Car car = carService.selectBeanById(carid);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("qiantai");
        //获取租金
        RentPrice rentPrice=rentPriceService.selectPriceById(car.getCarInfoId());
        //todo 封装一个方法用来计算实际日租(其实有可能租赁了很长时间了)
        //当钱不够支付押金时,拒绝服务!
        if(moneyService.canAfford(user.getID(),rentPrice.getDeposit()).doubleValue()<0){
            writer.print("<script  language='javascript'>alert('需要有效驾驶证，请在个人信息中提交');window.location.href='.';</script>");
            return;
        }

        //完成支付并写入日志
        moneyService.payOrReturn(user.getID(),rentPrice.getDeposit());
        //rentLogService.insertLog(user.getName(),user.getCellPhone(),user.getID(),"已预定",rentPrice.getDeposit(),car.getLicensePlate());

        //租赁状态更新
        carService.updateRentStatus(car.getCarInfoId(),user.getCellPhone(),user.getID(),"rented");
        writer.print("<script  language='javascript'>alert('操作成功');window.location.href='reserveList.do'; </script>");

    }
    //查询已经预定的
    @RequestMapping("/reserveList.do")
    public String reserveList(HttpServletRequest request,String pagenum,String brand){

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


        return "reserveList.jsp";

    }

    //来注册吧！
    @RequestMapping("/register.do")
    public String register(HttpServletRequest request){

        request.setAttribute("title", "用户注册");

        return "register";

    }
    //用户注册操作
    @RequestMapping("/register2.do")
    public void register2(HttpServletRequest request,HttpServletResponse response,User user){
        //当表单中组件的 name 和对象的属性名一致时 可以在controller  方法的参数中 写一个对应的对应，spring会自动封装成一个对象
        PrintWriter writer = this.getPrintWriter(response);
        if(userService.existCellphone(user.getCellPhone())>0){

            writer.print("<script language=javascript>alert('该手机号已被注册，注册失败！');window.location.href='register.do';</script>");

            return;
        }

        if(userService.existIdentity(user.getIdentity())>0){

            writer.print("<script language=javascript>alert('该身份证已经存在，注册失败！');window.location.href='register.do';</script>");

            return;
        }
        Date date=new Date(System.currentTimeMillis());
        user.setCreateTime(date);
        user.setRole(2);

        userService.insertBean(user);

        writer.print("<script language=javascript>alert('注册成功');window.location.href='login.do';</script>");


    }
    //查询用户自己的日志
    @RequestMapping("/guestLog.do")
    public String guestLog(HttpServletRequest request,String pagenum,HttpServletResponse response){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("qiantai");
        //分页功能默认第一页
        int currentpage = 1;
        //获取当前页
        if (pagenum != null) {
            currentpage = Integer.parseInt(pagenum);
        }
        //查询
        //List<RentLog> list=rentLogService.selectUserRentLog(user.getID(),(currentpage-1)*pageSize,pageSize);
        //获取总数量
        //int total = rentLogService.selectUserRentLogCount(user.getID());
        //列表返回页面
        //request.setAttribute("list", list);

        //分页信息返回页面
        //request.setAttribute("pagerinfo", PagerUtil.getPagerNormal(total, pageSize,
        //        currentpage, "reserveList.do", "共有" + total + "条记录"));


        request.setAttribute("title", "我的预定");
        return "guestLog";
    }
    //更新信息
    @RequestMapping("/userupdate.do")
    public String userupdate(HttpServletRequest request){

        request.setAttribute("title", "个人信息维护");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("qiantai");


        User bean = userService.selectBeanById(user.getID());

        request.setAttribute("bean", bean);

        return "userupdate";

    }
    //个人信息维护操作
    @RequestMapping("/userupdate2.do")
    public void userupdate2(HttpServletRequest request,HttpServletResponse response,String userName,String cellPhone){

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
    public String uploadLicense(HttpServletRequest request,HttpServletResponse response){
        PrintWriter writer = this.getPrintWriter(response);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("qiantai");

        if (user == null) {
            writer.print("<script  language='javascript'>alert('请先登录');window.location.href='login.do';</script>");
            return  null;
        }

        request.setAttribute("user", user);

        request.setAttribute("title", "证件上传");

        return "uploadLicense";
    }
    //顾客上传证件后供管理员审核
    @RequestMapping("/uploadLicense2.do")
    public void uploadLicense2(HttpServletRequest request,HttpServletResponse response,UserLicense bean,MultipartFile prodFile1){
        PrintWriter writer = this.getPrintWriter(response);


        if(prodFile1==null || prodFile1.getSize()<=0 ){
            this.getPrintWriter(response).print("<script language=javascript>alert('必须上传证件');" +
                    "window.location.href='uploadLicense2.do?';</script>");
            return;
        }


        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("qiantai");

        bean.setLicenseNumber(user.getLicenseNumber());
        bean.setID(user.getID());
        bean.setExamineStatus(0);

        String file =  FileUtil.uploadFile(request, prodFile1);
        bean.setPath(file);

        userLicenseService.deleteBean(user.getID());
        userLicenseService.insertBean(bean);

        writer.print("<script  language='javascript'>alert('操作成功');window.location.href='uploadLicense.do'; </script>");

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