package com.duoduo.service;

import com.duoduo.model.RentStatus;

import java.util.List;

public interface RentStatusService {
    //选择正在租赁的
    public List<RentStatus> selectRented(int start,int limit);
    //选择数量
    public int countRented();
    //根据车辆id选择
    public RentStatus selectOne(int CarID);
}
