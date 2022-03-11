package com.duoduo.dao;

import com.duoduo.model.Reservation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public interface ReservationDao {
    //查询属于自己的租车预约仅rented和returning
    public List<Reservation> selectUserReservation(
            @Param("start") int start,@Param("limit") int limit,@Param("userID") int userID,@Param("brand") String brand);
    //查询自己的租车数量
    public int selectUserReservationCount(@Param("userID") int userID,@Param("brand") String brand);

    //查询一切租车预约 一切状态
    public List<Reservation> selectAllReservation(
            @Param("start") int start, @Param("limit") int limit,@Param("userID") int userID,@Param("brand") String brand);
}
