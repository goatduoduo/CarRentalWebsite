package com.duoduo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface MoneyDao {
    //判断能否支付这个项目,负数代表无法承担
    public BigDecimal canAfford(@Param("userID") int userID,@Param("cost") BigDecimal cost);
    //付款 钱为正数 或者退款 钱为负数
    public void payOrReturn(@Param("userID") int userID,@Param("deltaMoney") BigDecimal deltaMoney);
}
