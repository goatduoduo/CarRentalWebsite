package com.duoduo.dao;

import com.duoduo.model.Car;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDao {
    //添加
    void insertBean(Car bean);

    //删除
    public void deleteBean(int id);

    //更新
    public void updateBean(Car bean);



    //根据ID查询对象
    public Car selectBeanById(@Param("id") int id);


    //按条件查询列表 支持分页
    public List<Car> selectBeanList(@Param("start") int start, @Param("limit") int limit,@Param("car") Car car);


    //按条件查询列表数量
    public int selectBeanCount(Car car);

    //修改租赁状态
    public void updateRentStatus(int cID,String cellPhone,int userID,String rentStatus);
}
