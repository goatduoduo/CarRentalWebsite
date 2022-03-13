package com.duoduo.dao;

import com.duoduo.model.RentLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Repository
public interface RentLogDao {
    //增加日志
    public void insertLog(@Param("name") String name,
                          @Param("cellPhone") String cellPhone,
                          @Param("userID") int userID,
                          @Param("carID") int carID,
                          @Param("status") String status,
                          @Param("deltaMoney") BigDecimal deltaMoney,
                          @Param("licensePlate") String licensePlate);
    //更新一条日志
    public void updateLog(@Param("logID") int logID,@Param("name") String name,@Param("cellPhone") String cellPhone,
                          @Param("userID") int userID,@Param("status") String status,
                          @Param("deltaMoney") BigDecimal deltaMoney,@Param("licensePlate") String licensePlate);

    //查询自己的日志
    public List<RentLog> selectUserRentLog(@Param("userID") int userID,
                                           @Param("start") int start, @Param("limit")int limit);

    //查询数量
    public int selectUserRentLogCount(@Param("userID") int userID);

    //查询所有日志
    public List<RentLog> selectRentLog(@Param("start") int start,@Param("limit") int limit);

    //查询数量
    public int selectRentLogCount();
}
