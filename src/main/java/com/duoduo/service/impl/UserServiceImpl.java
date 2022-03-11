package com.duoduo.service.impl;

import com.duoduo.dao.UserDao;
import com.duoduo.model.User;
import com.duoduo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void insertBean(User bean) {
        userDao.insertBean(bean);
    }

    @Override
    public void deleteBean(int id) {
        userDao.deleteBean(id);
    }

    @Override
    public void updateBean(User bean) {
        userDao.updateBean(bean);
    }

    @Override
    public User userlogin(String username, String password) {
        return userDao.userlogin(username,password);
    }

    @Override
    public User selectBeanById(int id) {
        return userDao.selectBeanByID(id);
    }

    @Override
    public int hasAvailableLicense(int id) {
        return userDao.hasAvailableLicense(id);
    }

    @Override
    public int existIdentity(String Identity) {
        return userDao.existIdentity(Identity);
    }

    @Override
    public int existCellphone(String Cellphone) {
        return userDao.existCellphone(Cellphone);
    }
}
