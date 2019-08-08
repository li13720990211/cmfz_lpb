package com.baizhi.service;

import com.baizhi.entity.China;
import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    //查询所有并分页
    public List<User> showAll(Integer page, Integer rows);
    //查询总条数
    public Integer totalCount();
    //修改状态
    public void updateStatus(User user);
    //删除是一条
    public void deleteOne(String id);
    //添加用户
    public void addOne(User user);

    //查询所有并导入到 表格文档中
    public Map<String,String> EasyPoi();
    //所有个数  并展到统计图中
    public Map<String,Object> Echarts();
    //查询个数  根据城市进行分组
    public List<China> EchartsMap();
}
