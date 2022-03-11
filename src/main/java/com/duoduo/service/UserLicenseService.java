package com.duoduo.service;

import com.duoduo.model.UserLicense;

import java.sql.Date;

public interface UserLicenseService {
    //删除后插入，因为证件信息较为敏感，一般不允许修改
    public void deleteBean(int ID);
    //插入审核信息
    public void insertBean(UserLicense userLicense);
    //审核
    public void setExamineStatus(int ID,int examineStatus);
}
