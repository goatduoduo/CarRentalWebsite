package com.duoduo.dao;


import com.duoduo.model.PriceChange;
import com.duoduo.model.RentPrice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Repository
public interface RentPriceDao {
    //获取价格和delta价格
    public RentPrice selectPriceById(@Param("id") int id);
    //获取价格变化表
    public List<PriceChange> selectDeltaPriceById(@Param("id") int id);
    //完成支付
    public void moneyChange(@Param("id") int id,@Param("money") BigDecimal money);
    //删除一条价格变化
    public void delDeltaPrice(@Param("id") int id,@Param("startTime") Date startTime,@Param("endTime") Date endTime,@Param("deltaPrice") BigDecimal deltaPrice);
    //新增一条价格变化
    public void addDeltaPrice(@Param("id") int id,@Param("startTime") Date startTime,@Param("endTime") Date endTime,@Param("deltaPrice") BigDecimal deltaPrice);
}
