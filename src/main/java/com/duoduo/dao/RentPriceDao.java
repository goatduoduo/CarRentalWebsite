package com.duoduo.dao;


import com.duoduo.model.PriceChange;
import com.duoduo.model.RentPrice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RentPriceDao {
    //获取价格和delta价格
    public RentPrice selectPriceById(@Param("id") int id);
    //获取价格变化表
    public List<PriceChange> selectDeltaPriceById(@Param("id") int id);
    //完成支付
    public void moneyChange(@Param("id") int id,@Param("money") BigDecimal money);
}
