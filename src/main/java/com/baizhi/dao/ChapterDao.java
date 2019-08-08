package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDao {

    //查询所有章节并分页
    public List<Chapter> selectAll(@Param("page")Integer page, @Param("rows")Integer rows,@Param("album_id")String album_id);
    //查询总条数
    public Integer totalCount(String album_id);
    //修改一个章节
    public void updateOne(Chapter chapter);
    //添加一个章节
    public void insertOne(Chapter chapter);
    //删除一章节
    public void deleteOne(String id);
    //根据专辑id查询所有的章节
    public List<Chapter> selectAllById(String album_id);
}
