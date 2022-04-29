package com.duoduo.dao;

import com.duoduo.model.RentStatus;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentStatusDao {
    //选择正在租赁的
    public List<RentStatus> selectRented(@Param("start") int start, @Param("limit") int limit);

    public int countRented();

    //根据车辆id选择
    public RentStatus selectOne(@Param("CarID") int CarID);
}
