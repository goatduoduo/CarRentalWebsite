package com.duoduo.service;

import java.math.BigDecimal;

public interface MoneyService {
    //判断能否支付这个项目,负数代表无法承担
    public BigDecimal canAfford(int userID,BigDecimal cost);
    //付款 钱为正数 或者退款 钱为负数
    public void payOrReturn(int userID,BigDecimal deltaMoney);
    //获取该用户的money
    public BigDecimal getUserCurMoney(int userID);
}
