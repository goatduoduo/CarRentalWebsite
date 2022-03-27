package com.duoduo.model;

import java.io.Serializable;
import java.sql.Date;

public class Car implements Serializable {
    private int carInfoId; //汽车ID
    private int carTypeId; //汽车类型ID
    private String licensePlate; //车牌号
    private String drivingLicense; //行驶证
    private Date createTime; //出厂日期
    private String carTypeName; //汽车类型名
    private String brand; //品牌
    private String location; //租车公司地点
    private int seats; //座位
    private String color; //颜色
    private String picPath; //图片文件
    private String info; //简介
    private Double carYear;//车龄
    private Double carMile;//历程

    public Double getCarYear() {
        return carYear;
    }

    public void setCarYear(Double carYear) {
        this.carYear = carYear;
    }

    public Double getCarMile() {
        return carMile;
    }

    public void setCarMile(Double carMile) {
        this.carMile = carMile;
    }

    public int getCarInfoId() {
        return carInfoId;
    }

    public void setCarInfoId(int carInfoId) {
        this.carInfoId = carInfoId;
    }

    public int getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(int carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCarTypeName() {
        return carTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
