package com.duoduo.test;

import com.duoduo.dao.UserDao;
import com.duoduo.model.User;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class userDaoTest extends BaseJunit4Test{

    @Resource
    private UserDao userDao;

    @Test
    public void testFindAll(){
        List<User> userList=userDao.findAll();
        System.out.println(userList.size());
    }

}
