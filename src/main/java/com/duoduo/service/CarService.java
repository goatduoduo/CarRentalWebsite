package com.duoduo.service;

import com.duoduo.model.Car;
import com.duoduo.model.CarBrief;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public interface CarService {
    //添加
    void insertBean(Car bean);

    //删除
    public void deleteBean(int id);

    //更新
    public void updateBean(Car bean);



    //根据ID查询对象
    public Car selectBeanById(int id);


    //按条件查询列表 支持分页
    public List<Car> selectBeanList(int start, int limit, Car car);


    //按条件查询列表数量
    public int selectBeanCount(Car car);

    //按条件查询车辆简要 支持分页
    public List<CarBrief> selectBriefList(int start, int limit, String brand,String carTypeId,BigDecimal min,BigDecimal max);

    //按条件查询车辆简要 num
    public int selectBriefCount(String brand,String carTypeId,BigDecimal min,BigDecimal max);

    //修改租赁状态
    public void updateRentStatus(int cID,String cellPhone,int userID,String rentStatus);
    //修改租赁状态
    public void updateRentStatus2(int cID,String cellPhone,int userID,String rentStatus,String name,Date returnTime);

    //获取可用ID以添加
    public int getCarID();

    //插入动作
    public void insertBeanByID(int carID, String licensePlate, String brand, String drivingLicense,
                               int carTypeId, String picPath, BigDecimal dailyRent, BigDecimal deposit,
                               BigDecimal price, BigDecimal insurance, BigDecimal serviceCharge, String location,String color, Date createTime,
                               int seats, String info);

    //选择租了这个车子的用户ID
    public int selectUser(int carID);
}
