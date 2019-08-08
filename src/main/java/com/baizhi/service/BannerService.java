package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.List;
import java.util.Map;

public interface BannerService {

    //展示所有信息并分页
    public List<Banner> showAll(Integer page,Integer rows);
    //总个数
    public Integer totalCount();
    //添加一条信息
    public String addOne(Banner banner);
    //修改一条信息
    public void updateOne(Banner banner);
    //删除一条信息
    public void deleteOne(String id);
    //修改状态
    public Map<String,Object> updateStatus(Banner banner);
}
