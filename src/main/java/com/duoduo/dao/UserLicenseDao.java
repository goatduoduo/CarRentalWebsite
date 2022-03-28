package com.duoduo.dao;

import com.duoduo.model.UserLicense;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface UserLicenseDao {
    //删除后插入，因为证件信息较为敏感，一般不允许修改
    public void deleteBean(@Param("ID") int ID);

    //插入审核信息
    public void insertBean(UserLicense userLicense);

    //审核
    public void setExamineStatus(@Param("ID") int ID, @Param("examineStatus") int examineStatus);

    //选择满足条件的
    public List<UserLicense> selectBeanList(@Param("status") int status);
    //选择用户自己的
    public UserLicense selectUserLicense(@Param("ID") int ID);
}
