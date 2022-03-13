package com.duoduo.service.impl;

import com.duoduo.dao.RentLogDao;
import com.duoduo.model.RentLog;
import com.duoduo.service.RentLogService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class RentLogImpl implements RentLogService {
    @Resource
    private RentLogDao rentLogDao;

    @Override
    public void insertLog(String name, String cellPhone, int userID,int carID, String status,
                          BigDecimal deltaMoney, String licensePlate) {
        rentLogDao.insertLog(name, cellPhone, userID, carID, status, deltaMoney, licensePlate);
    }

    @Override
    public void updateLog(int logID, String name, String cellPhone, int userID, String status, BigDecimal deltaMoney, String licensePlate) {
        rentLogDao.updateLog(logID,name,cellPhone,userID,status,deltaMoney,licensePlate);
    }

    @Override
    public List<RentLog> selectUserRentLog(int userID, int start, int limit) {
        return rentLogDao.selectUserRentLog(userID, start, limit);
    }

    @Override
    public int selectUserRentLogCount(int userID) {
        return rentLogDao.selectUserRentLogCount(userID);
    }

    @Override
    public List<RentLog> selectRentLog(int start, int limit) {
        return rentLogDao.selectRentLog(start,limit);
    }

    @Override
    public int selectRentLogCount() {
        return rentLogDao.selectRentLogCount();
    }
}
