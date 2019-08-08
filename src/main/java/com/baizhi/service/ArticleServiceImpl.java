package com.baizhi.service;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Article;
import com.baizhi.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    //查询所有并分页
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Article> showAll(Integer page, Integer rows) {
        List<Article> articles = articleDao.selectAll(page,rows);
        return articles;
    }
    //查询总条数
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Integer totalCount() {
        Integer totalCount = articleDao.totalCount();
        return totalCount;
    }
    //修改状态
    @Override
    public void updateStatus(Article article) {
        articleDao.updateOne(article);
    }
    //删除一篇文章
    @Override
    public void deleteOne(String id) {
        articleDao.deleteOne(id);
    }
    //添加一篇文章
    @Override
    public String addOne(Article article, HttpServletRequest request) {
        String id = UUIDUtil.getUUID();
        article.setId(id);
        article.setStatus("正常");
        article.setUp_date(new Date());
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        //String guru_id = admin.getId();
        article.setGuru_id("1");
        articleDao.insertOne(article);
        return id;
    }
    //修改文章信息
    @Override
    public void updateOne(Article article) {
        articleDao.updateOne(article);
    }
}
