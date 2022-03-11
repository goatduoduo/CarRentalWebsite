package com.duoduo.service.impl;

import com.duoduo.dao.UserLicenseDao;
import com.duoduo.model.UserLicense;
import com.duoduo.service.UserLicenseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserLicenseServiceImpl implements UserLicenseService {
    @Resource
    UserLicenseDao userLicenseDao;
    @Override
    public void deleteBean(int ID) {
        userLicenseDao.deleteBean(ID);
    }

    @Override
    public void insertBean(UserLicense userLicense) {
        userLicenseDao.insertBean(userLicense);
    }

    @Override
    public void setExamineStatus(int ID, int examineStatus) {
        userLicenseDao.setExamineStatus(ID,examineStatus);
    }
}
