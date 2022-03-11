package com.duoduo.service.impl;

import com.duoduo.dao.ReservationDao;
import com.duoduo.model.Reservation;
import com.duoduo.service.ReservationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Resource
    ReservationDao reservationDao;
    @Override
    public List<Reservation> selectUserReservation(int start, int limit, int userID, String brand) {
        return reservationDao.selectUserReservation(start, limit, userID, brand);
    }

    @Override
    public int selectUserReservationCount(int userID, String brand) {
        return reservationDao.selectUserReservationCount(userID, brand);
    }

    @Override
    public List<Reservation> selectAllReservation(int start, int limit, int userID, String brand) {
        return reservationDao.selectAllReservation(start,limit,userID,brand);
    }
}
