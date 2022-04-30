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
import java.math.BigDecimal;
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

    private int pageSize=12;

    @RequestMapping("/login.do")
    public String login(HttpServletRequest request){

        request.setAttribute("title", "用户登录");

        return "/new/login";

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

            if(bean.getRole()==2)
                writer.print("<script language=javascript>window.location.href='user/index.do';</script>");
            else
                writer.print("<script language=javascript>window.location.href='admin/index.do';</script>");

        }

    }
    @RequestMapping("/index.do")
    public String index(HttpServletRequest request,HttpServletResponse response){
        System.out.println(request.getServletContext().getRealPath("/"));
        return "new/userIndex";
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
//        rentLogService.insertLog(user.getName(),user.getCellPhone(),user.getID(),
//                car.getCarInfoId(),"已预定",rentPrice.getDeposit(),car.getLicensePlate(),ren);

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


        return "reserveList";

    }

    //来注册吧！
    @RequestMapping("/register.do")
    public String register(HttpServletRequest request){

        request.setAttribute("title", "用户注册");

        return "new/register";

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
        List<RentLog> list=rentLogService.selectUserRentLog(user.getID(),(currentpage-1)*pageSize,pageSize,null);
        //获取总数量
        int total = rentLogService.selectUserRentLogCount(user.getID(),null);
        //列表返回页面
        request.setAttribute("list", list);

        //分页信息返回页面
        request.setAttribute("pagerinfo", PagerUtil.getPagerNormal(total, pageSize,
                currentpage, "reserveList.do", "共有" + total + "条记录"));


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
    //申请注销
    @RequestMapping("/accountCancellation.do")
    public void accountCancellation(HttpServletRequest request,HttpServletResponse response){
        PrintWriter writer = this.getPrintWriter(response);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("qiantai");
        //todo 如果还有租赁中的，不许注销
        //未提现到0也不可以哦
        //增加注销申请
        rentLogService.insertLog(user.getName(),user.getCellPhone(), user.getID(),0, "申请注销", BigDecimal.valueOf(0),null,null);
        //return "accountCancellation";
    }
    //安全退出操作
    @RequestMapping("/loginout.do")
    public void loginout(HttpServletRequest request,HttpServletResponse response){

        PrintWriter writer = this.getPrintWriter(response);

        HttpSession session = request.getSession();
        session.removeAttribute("qiantai");

        writer.print("<script language=javascript>alert('退出成功');window.location.href='index.do';</script>");


    }
    //用户管理
    //人员列表
    @RequestMapping("/userlist.do")
    public String userlist(HttpServletRequest request,String pagenum,String username){

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

        return "userlist";

    }
    //跳转到修改人员页面
    @RequestMapping("/userupdate22.do")
    public String userupdate(HttpServletRequest request,int id){

        User bean = userService.selectBeanById(id);

        request.setAttribute("bean", bean);

        request.setAttribute("url", "userupdate2do.do?id="+id);

        request.setAttribute("title", "修改人员");

        return "userupdate2";

    }
    //修改人员操作
    @RequestMapping("/userupdate2do.do")
    public void userupdate2(HttpServletResponse response,User bean){

        userService.updateBean(bean);


        this.getPrintWriter(response).print("<script language=javascript>alert('操作成功');window.location.href='userlist.do';</script>");
    }
    //修改人员操作
    @RequestMapping("/userdelete2.do")
    public void userdelete(HttpServletResponse response,int id){
        //todo 删除前需要确保他已经提现完成，且没有车辆在驾驶
        userService.deleteBean(id);


        this.getPrintWriter(response).print("<script language=javascript>alert('操作成功');window.location.href='userlist.do';</script>");
    }
    //修改人员操作
    @RequestMapping("/licenseexamine.do")
    public String licenseexamine(HttpServletRequest request,int status){
        request.setAttribute("status", status);
        List<UserLicense> userLicenses=userLicenseService.selectBeanList(status);
        //列表返回页面
        request.setAttribute("list", userLicenses);
        return "licenseexamine";
    }
    //审核通过
    @RequestMapping("/licenseexamine1.do")
    public void licenseexamine1(HttpServletResponse response,int id){
        userLicenseService.setExamineStatus(id,1);
        this.getPrintWriter(response).print("<script language=javascript>alert('审核通过设置成功');window.location.href='licenseexamine.do';</script>");
    }
    //审核不通过
    @RequestMapping("/licenseexamine2.do")
    public void licenseexamine2(HttpServletResponse response,int id){
        userLicenseService.setExamineStatus(id,2);
        this.getPrintWriter(response).print("<script language=javascript>alert('审核不通过设置成功');window.location.href='licenseexamine.do';</script>");
    }
    //转到跳转页面
    @RequestMapping("/caradd.do")
    public String caradd(HttpServletRequest request){

        request.setAttribute("url", "caradd2.do");

        request.setAttribute("title", "添加车辆");

        return "caradd";

    }
    //添加车辆操作
    @RequestMapping("/caradd2.do")
    public void caradd2(HttpServletResponse response,HttpServletRequest request,String licensePlate,
                        String brand,String drivingLicense,int carTypeId, MultipartFile prodFile,
                        BigDecimal dailyRent,BigDecimal deposit,BigDecimal price,BigDecimal insurance,BigDecimal serviceCharge,String location,Date createTime,
                        int seats, int info){


        if(prodFile==null || prodFile.getSize()<=0 ){
            this.getPrintWriter(response).print("<script language=javascript>alert('车辆图片不能为空');" +
                    "window.location.href='caradd.do';</script>");
            return;
        }

        String pic =  FileUtil.uploadFile(request, prodFile);
        int carID=carService.getCarID();
//        //长到离谱的创建函数
//        carService.insertBeanByID(carID,licensePlate,brand,drivingLicense,
//                carTypeId,pic,dailyRent,deposit,price,insurance,serviceCharge,
//                location,createTime,seats,info);

        this.getPrintWriter(response).print("<script language=javascript>alert('操作成功');" +
                "window.location.href='carlist.do';</script>");
    }
    //todo 车辆的修改和删除
    //管理员的车辆页面
    @RequestMapping("/carlist2.do")
    public String carlist2(HttpServletRequest request,String pagenum){
        //分页功能默认第一页
        int currentpage = 1;
        //获取当前页
        if (pagenum != null) {
            currentpage = Integer.parseInt(pagenum);
        }

        //查询列表
        List<Car> list = carService.selectBeanList((currentpage - 1)
                * pageSize, pageSize,null);

        //列表返回页面
        request.setAttribute("list", list);

        //获取总数量
        int total = carService.selectBeanCount(null);

        //分页信息返回页面
        request.setAttribute("pagerinfo", PagerUtil.getPagerNormal(total, pageSize,
                currentpage, "carlist2.do", "共有" + total + "条记录"));

        //查询按钮
        request.setAttribute("url", "carlist2.do");

        //添加，更新，删除等按钮
        request.setAttribute("url2", "car");

        request.setAttribute("title", "车辆管理");

        return "carlist2";
    }
    @RequestMapping("/carupdate.do")
    public String carupdate(HttpServletRequest request,int id){
        //todo 更新车辆
        Car bean = carService.selectBeanById(id);

        request.setAttribute("bean", bean);

        request.setAttribute("url", "carupdate2.do?id="+id);

        request.setAttribute("title", "修改车辆");
        return "index.do";
    }

    @RequestMapping("/cardelete.do")
    public void cardelete(HttpServletResponse response,int id){

        carService.deleteBean(id);


        this.getPrintWriter(response).print("<script language=javascript>alert('操作成功');window.location.href='carlist2.do';</script>");
    }
    //进入充值与提现页面
    @RequestMapping("/usermoney.do")
    public String usermoney(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        User user=(User) session.getAttribute("qiantai");
        request.setAttribute("userName",user.getUserName());
        request.setAttribute("curMoney",moneyService.getUserCurMoney(user.getID()));
        return "usermoney";
    }
    //充值
    @RequestMapping("/userrecharge.do")
    public void userrecharge(HttpServletRequest request,HttpServletResponse response,BigDecimal recharge){
        HttpSession session = request.getSession();
        User user=(User) session.getAttribute("qiantai");
        moneyService.payOrReturn(user.getID(),recharge.multiply(new BigDecimal(-1)));
        rentLogService.insertLog(user.getName(),user.getCellPhone(),user.getID(),0,"充值",recharge,"",null);
        this.getPrintWriter(response).print("<script language=javascript>alert('操作成功');window.location.href='usermoney.do';</script>");
    }
    //提现
    @RequestMapping("/userimpose.do")
    public void userimpose(HttpServletRequest request,HttpServletResponse response,BigDecimal impose){
        HttpSession session = request.getSession();
        User user=(User) session.getAttribute("qiantai");
        moneyService.payOrReturn(user.getID(),impose.multiply(new BigDecimal(1)));
        rentLogService.insertLog(user.getName(),user.getCellPhone(),user.getID(),0,"提现",impose,"",null);
        this.getPrintWriter(response).print("<script language=javascript>alert('操作成功');window.location.href='usermoney.do';</script>");
    }
    //租车日志
    @RequestMapping("/logview.do")
    public String logview(HttpServletRequest request,HttpServletResponse response,String pagenum){
        //分页功能默认第一页
        int currentpage = 1;
        //获取当前页
        if (pagenum != null) {
            currentpage = Integer.parseInt(pagenum);
        }

        //查询列表
//        List<Car> list = carService.selectBeanList((currentpage - 1)
//                * pageSize, pageSize,null);
        List<RentLog> list=rentLogService.selectRentLog((currentpage - 1) * pageSize, pageSize);

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

        return "logview";
    }
    //人员列表
    @RequestMapping("/carstatus.do")
    public String carstatus(HttpServletRequest request,String pagenum,String username){

        //分页功能默认第一页
        int currentpage = 1;
        //获取当前页
        if (pagenum != null) {
            currentpage = Integer.parseInt(pagenum);
        }

        //查询列表
        List<RentStatus> list=rentStatusService.selectRented((currentpage - 1)
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

        return "carstatus";

    }
    @RequestMapping("/carreturn.do")
    public String carreturn(HttpServletRequest request,HttpServletResponse response,int carID,int userID,Date time){
        RentPrice rentPrice=rentPriceService.selectPriceById(carID);
        List<PriceChange> priceChange=rentPriceService.selectDeltaPriceById(carID);
        //计算需要的钱
        BigDecimal price=getPrice(rentPrice,priceChange);
        request.setAttribute("rentPrice",rentPrice);
        request.setAttribute("total",price);
        return "carreturn";
    }
    //确认归还
    @RequestMapping("/carreturn2.do")
    public void carreturn2(HttpServletRequest request,HttpServletResponse response){
        PrintWriter writer = this.getPrintWriter(response);
        RentPrice rentPrice=(RentPrice) request.getAttribute("rentPrice") ;
        BigDecimal price=(BigDecimal) request.getAttribute("total");
        BigDecimal back=(BigDecimal) request.getAttribute("depositBack");
        int userID=carService.selectUser(rentPrice.getCarInfoId());
        User user=userService.selectBeanById(userID);
        moneyService.payOrReturn(userID,price);
        rentLogService.insertLog(user.getName(),user.getCellPhone(),userID,rentPrice.getCarInfoId(),"支付金钱",price,null,null);
        moneyService.payOrReturn(userID,back.multiply(new BigDecimal(-1)));
        rentLogService.insertLog(user.getName(),user.getCellPhone(),userID,rentPrice.getCarInfoId(),"退还押金",back,null,null);
        carService.updateRentStatus(rentPrice.getCarInfoId(),null,0,"available");
        writer.print("<script language=javascript>alert('还车成功');window.location.href='carreturn.do';</script>");
    }

    public BigDecimal getPrice(RentPrice rentPrice,List<PriceChange> priceChange){
        //todo 插入价格计算算法
        return new BigDecimal(1000);
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
