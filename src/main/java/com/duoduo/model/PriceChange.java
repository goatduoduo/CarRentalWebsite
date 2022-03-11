package com.duoduo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class PriceChange implements Serializable {
    private BigDecimal deltaPrice;//价格变化量
    private Date startTime;//开始时间
    private Date endTime;//结束时间
    private String type;//持续类型 一次或者每年

    public BigDecimal getDeltaPrice() {
        return deltaPrice;
    }

    public void setDeltaPrice(BigDecimal deltaPrice) {
        this.deltaPrice = deltaPrice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
