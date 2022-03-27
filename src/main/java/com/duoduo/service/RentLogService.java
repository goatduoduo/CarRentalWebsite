package com.duoduo.service;

import com.duoduo.model.RentLog;

import java.math.BigDecimal;
import java.util.List;

public interface RentLogService {
    //增加日志
    public void insertLog(String name, String cellPhone, int userID,int carID, String status,
                          BigDecimal deltaMoney, String licensePlate,Integer rentDays);
    //更新一条日志
    public void updateLog(int logID,String name,String cellPhone, int userID, String status,
                          BigDecimal deltaMoney,String licensePlate);
    //查询自己的日志
    public List<RentLog> selectUserRentLog(int userID, int start, int limit,String brand);

    //查询数量
    public int selectUserRentLogCount(int userID,String brand);

    //查询所有日志
    public List<RentLog> selectRentLog( int start, int limit);

    //查询数量
    public int selectRentLogCount();


}
