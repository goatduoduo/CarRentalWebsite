package com.duoduo.model;

import java.io.Serializable;
import java.sql.Date;

public class CarBrief implements Serializable {
    private int carInfoId; //汽车ID
    private int carTypeId; //汽车类型ID
    private String carTypeName; //汽车类型名
    private String brand; //品牌
    private int seats; //座位
    private String color; //颜色
    private String picPath; //图片文件
    private String brief; //简介自动生成

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

    public String getBrief() {
        return brief;
    }

    public void setBrief() {
        this.brief = carTypeName+"/"+seats+"座/"+color+"色";
    }
}
