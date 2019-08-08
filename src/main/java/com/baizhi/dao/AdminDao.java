package com.baizhi.dao;

import com.baizhi.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminDao {

    //登陆
    public Admin login(@Param("username")String username,@Param("password")String password);
}
