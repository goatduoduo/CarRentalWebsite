package com.duoduo.service.impl;

import com.duoduo.dao.MoneyDao;
import com.duoduo.service.MoneyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class MoneyServiceImpl implements MoneyService {
    @Resource
    MoneyDao moneyDao;
    @Override
    public BigDecimal canAfford(int userID, BigDecimal cost) {
        return moneyDao.canAfford(userID, cost);
    }

    @Override
    public void payOrReturn(int userID, BigDecimal deltaMoney) {
        moneyDao.payOrReturn(userID, deltaMoney);
    }
}
