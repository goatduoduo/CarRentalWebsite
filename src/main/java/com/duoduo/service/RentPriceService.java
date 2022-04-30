package com.duoduo.service;

import com.duoduo.model.PriceChange;
import com.duoduo.model.RentPrice;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public interface RentPriceService {
    //获取价格和delta价格
    public RentPrice selectPriceById(int id);
    //获取价格变化表
    public List<PriceChange> selectDeltaPriceById(int id);

    public void moneyChange(int id, BigDecimal money);
    //删除一条价格变化
    public void delDeltaPrice(int id, Date startTime, Date endTime, BigDecimal deltaPrice);
    //新增一条价格变化
    public void addDeltaPrice(int id, Date startTime, Date endTime, BigDecimal deltaPrice);
}
