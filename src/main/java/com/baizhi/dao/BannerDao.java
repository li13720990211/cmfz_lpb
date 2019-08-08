package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDao {

    //查询所有并分页
    public List<Banner> selectAll(@Param("page")Integer page,@Param("rows")Integer rows);
    //查询总条数
    public Integer totalCount();
    //添加一个轮播图
    public void insertOne(Banner banner);
    //修改一个轮播图
    public void updateOne(Banner banner);
    //删除一盒轮播图
    public void deleteOne(String id);

}
