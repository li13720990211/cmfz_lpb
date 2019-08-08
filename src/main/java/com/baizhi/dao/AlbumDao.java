package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {

    //查询所有专辑并分页
    public List<Album> selectAll(@Param("page")Integer page,@Param("rows")Integer rows);
    //查询总条数
    public Integer totalCount();
    //添加一个专辑
    public void insertOne(Album album);
    //修改一个专辑
    public void updateOne(Album album);
    //删除一专辑
    public void deleteOne(String id);

}
