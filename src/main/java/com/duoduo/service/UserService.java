package com.duoduo.service;

import com.duoduo.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    //添加
    void insertBean(User bean);

    //删除
    public void deleteBean(int id);

    //更新
    public void updateBean(User bean);

    //用户登录
    public  User userlogin(String username, String password);

    //选择项目
    public User selectBeanById(int id);
    //根据用户ID判断是否取得有效驾驶证
    public int hasAvailableLicense(int id);
    //验证身份证是否已存在
    public int existIdentity(String Identity);
    //验证手机号是否已存在
    public int existCellphone(String Cellphone);

    public List<User> selectBeanList(int start,int limit,String username);
    public int selectBeanCount(String username);
}
