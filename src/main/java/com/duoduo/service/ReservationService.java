package com.duoduo.service;

import com.duoduo.model.Reservation;

import java.util.List;

public interface ReservationService {
    //查询属于自己的租车日志仅rented和returning
    public List<Reservation> selectUserReservation(int start, int limit,int userID,String brand);

    //查询自己的租车数量
    public int selectUserReservationCount(int userID,String brand);
    //查询一切租车日志 一切状态
    public List<Reservation> selectAllReservation(int start, int limit,int userID,String brand);
}
