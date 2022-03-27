package com.duoduo.model;

import java.io.Serializable;
import java.sql.Date;

public class Reservation implements Serializable {
    private int carID;//车辆ID
    private String brand;//车子品牌
    private String status;//租车状态
    //状态 available可预定 returning提交归还申请
    // rented被租赁 maintenance维护中
    private int userID;//追踪用户
    private Date time;//当前时间
    private String name;//姓名
    private String cellPhone;//手机号
    private Date returnTime;//归还时间

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
