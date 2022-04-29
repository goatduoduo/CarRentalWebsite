package com.duoduo.service.impl;

import com.duoduo.dao.CarDao;
import com.duoduo.model.Car;
import com.duoduo.model.CarBrief;
import com.duoduo.service.CarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Resource
    CarDao carDao;
    @Override
    public void insertBean(Car bean) {
        carDao.insertBean(bean);
    }

    @Override
    public void deleteBean(int id) {
        carDao.deleteBean(id);
    }

    @Override
    public void updateBean(Car bean) {
        carDao.updateBean(bean);
    }

    @Override
    public Car selectBeanById(int id) {
        return carDao.selectBeanById(id);
    }

    @Override
    public List<Car> selectBeanList(int start, int limit, Car car) {
        return carDao.selectBeanList(start,limit,car);
    }

    @Override
    public int selectBeanCount(Car car) {
        return carDao.selectBeanCount(car);
    }

    @Override
    public List<CarBrief> selectBriefList(int start, int limit, String brand, String carTypeId, BigDecimal min, BigDecimal max) {
        return carDao.selectBriefList(start, limit, brand, carTypeId, min, max);
    }

    @Override
    public int selectBriefCount(String brand, String carTypeId, BigDecimal min, BigDecimal max) {
        return carDao.selectBriefCount(brand, carTypeId, min, max);
    }

    @Override
    public void updateRentStatus(int cID, String cellPhone, int userID, String rentStatus) {
        carDao.updateRentStatus(cID, cellPhone, userID, rentStatus);
    }

    @Override
    public void updateRentStatus2(int cID, String cellPhone, int userID, String rentStatus, String name,Date returnTime) {
        carDao.updateRentStatus2(cID, cellPhone, userID, rentStatus, name,returnTime);
    }

    @Override
    public int getCarID() {
        return carDao.getCarID();
    }

    @Override
    public void insertBeanByID(int carID, String licensePlate, String brand, String drivingLicense, int carTypeId, String picPath, BigDecimal dailyRent, BigDecimal deposit, BigDecimal price, BigDecimal insurance, BigDecimal serviceCharge, String location,String color, Date createTime,int seats, String info) {
        carDao.insertBeanByID(carID, licensePlate, brand, drivingLicense, carTypeId, picPath, dailyRent, deposit, price, insurance, serviceCharge,location,color,createTime,seats, info);
    }

    @Override
    public int selectUser(int carID) {
        return carDao.selectUser(carID);
    }
}
