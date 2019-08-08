package com.baizhi.dao;

import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {

    //查询所有并分页
    public List<Article> selectAll(@Param("page") Integer page, @Param("rows") Integer rows);
    //查询总条数
    public Integer totalCount();
    //修改状态
    public void updateOne(Article article);
    //删除是一条
    public void deleteOne(String id);
    //添加一篇文章
    public void insertOne(Article article);

}
