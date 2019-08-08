package com.baizhi.service;

import com.baizhi.entity.Album;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface AlbumService {

    //查询所有并分页
    public List<Album> showAll(Integer page, Integer rows);
    //查询总条数
    public Integer totalCount();
    //添加一个轮播图
    public String addOne(Album album);
    //修改一个轮播图
    public void updateOne(Album album);
    //删除一盒轮播图
    public void deleteOne(String id);
}
