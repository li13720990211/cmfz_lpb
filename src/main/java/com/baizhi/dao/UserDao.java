package com.baizhi.dao;

import com.baizhi.entity.City;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    //查询所有并分页
    public List<User> selectAll(@Param("page") Integer page, @Param("rows") Integer rows);
    //查询总条数
    public Integer totalCount();
    //修改状态
    public void updateStatus(User user);
    //删除是一条
    public void deleteOne(String id);
    //添加用户
    public void insertOne(User user);

    //查询所有
    public List<User> EasyPoi();
    //模糊查询 echarts
    public Integer count(String month,String sex);
    //查询所有条数 并根据城市进行分组
    public List<City> citys(String sex);
}
