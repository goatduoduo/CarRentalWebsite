package com.duoduo.service.impl;

import com.duoduo.dao.RentPriceDao;
import com.duoduo.model.PriceChange;
import com.duoduo.model.RentPrice;
import com.duoduo.service.RentPriceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Service
public class RentPriceServiceImpl implements RentPriceService {
    @Resource
    RentPriceDao rentPriceDao;
    @Override
    public RentPrice selectPriceById(int id) {
        return rentPriceDao.selectPriceById(id);
    }

    @Override
    public List<PriceChange> selectDeltaPriceById(int id) {
        return rentPriceDao.selectDeltaPriceById(id);
    }

    @Override
    public void moneyChange(int id, BigDecimal money) {
        rentPriceDao.moneyChange(id, money);
    }

    @Override
    public void delDeltaPrice(int id, Date startTime, Date endTime, BigDecimal deltaPrice) {
        rentPriceDao.delDeltaPrice(id, startTime, endTime, deltaPrice);
    }

    @Override
    public void addDeltaPrice(int id, Date startTime, Date endTime, BigDecimal deltaPrice) {
        rentPriceDao.addDeltaPrice(id, startTime, endTime, deltaPrice);
    }
}
