package com.duoduo.dao;

import com.duoduo.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    List<User> findAll();
    public void insertBean(User bean);

    public void deleteBean(int id);

    public void updateBean(User bean);

    public User userlogin(
            @Param("username") String username,
            @Param("password") String password
    );
    public User selectBeanByID(@Param("id") int id);

    public int hasAvailableLicense(@Param("id") int id);

    public int existIdentity(@Param("Identity") String Identity);

    public int existCellphone(@Param("Cellphone")String Cellphone);

    public List<User> selectBeanList(@Param("start") int start,@Param("limit") int limit,@Param("username") String username);
    public int selectBeanCount(@Param("username") String username);
}
