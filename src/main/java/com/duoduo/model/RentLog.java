package com.duoduo.model;

import java.math.BigDecimal;
import java.sql.Date;

public class RentLog {
    private String name;
    private int logID;
    private String cellPhone;
    private int userID;//in mysql is ID
    private Date logTime;
    private String status;
    private BigDecimal deltaMoney;
    private String licensePlate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLogID() {
        return logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getDeltaMoney() {
        return deltaMoney;
    }

    public void setDeltaMoney(BigDecimal deltaMoney) {
        this.deltaMoney = deltaMoney;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
