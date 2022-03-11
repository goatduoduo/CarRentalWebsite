package com.duoduo.service;

import com.duoduo.model.PriceChange;
import com.duoduo.model.RentPrice;

import java.math.BigDecimal;
import java.util.List;

public interface RentPriceService {
    //获取价格和delta价格
    public RentPrice selectPriceById(int id);
    //获取价格变化表
    public List<PriceChange> selectDeltaPriceById(int id);

    public void moneyChange(int id, BigDecimal money);
}
