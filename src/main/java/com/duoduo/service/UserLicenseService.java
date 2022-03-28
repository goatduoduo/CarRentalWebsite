package com.duoduo.service;

import com.duoduo.model.UserLicense;

import java.sql.Date;
import java.util.List;

public interface UserLicenseService {
    //删除后插入，因为证件信息较为敏感，一般不允许修改
    public void deleteBean(int ID);
    //插入审核信息
    public void insertBean(UserLicense userLicense);
    //审核
    public void setExamineStatus(int ID,int examineStatus);
    //选择满足条件的
    public List<UserLicense> selectBeanList(int status);
    //选择用户自己的
    public UserLicense selectUserLicense(int ID);
}
