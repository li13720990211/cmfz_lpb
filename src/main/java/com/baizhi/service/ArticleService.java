package com.baizhi.service;

import com.baizhi.entity.Article;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ArticleService {

    //查询所有并分页
    public List<Article> showAll(Integer page, Integer rows);
    //查询总条数
    public Integer totalCount();
    //修改状态
    public void updateStatus(Article article);
    //删除是一条
    public void deleteOne(String id);
    //添加一篇文章
    public String addOne(Article article, HttpServletRequest request);
    //修改文章信息
    public void updateOne(Article article);
}
