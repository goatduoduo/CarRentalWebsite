package com.duoduo.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Money implements Serializable {
    private String cellPhone;
    private int ID;
    private BigDecimal money;

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
