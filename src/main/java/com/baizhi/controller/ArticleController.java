package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    //查询当前页的所有信息
    @RequestMapping("/showAll")
    public Map<String, Object> showAll(Integer page, Integer rows) throws Exception {
        Map<String, Object> map = new HashMap<>();
        //查询当前页的所有信息
        List<Article> articles = articleService.showAll(page, rows);
        map.put("rows", articles);
        //当前页
        map.put("page", page);
        //查询总条数
        Integer totalCount = articleService.totalCount();
        map.put("total", totalCount);
        //计算总页数
        Integer pageCount = totalCount % rows == 0 ? totalCount / rows : totalCount / rows + 1;
        map.put("records", totalCount);

        return map;
    }

    //删除操作
    @RequestMapping("/deleteOne")
    public void deleteOne(String id) throws Exception {
        articleService.deleteOne(id);
    }

    //修改状态
    @RequestMapping("/updateStatus")
    public void updateStatus(String id, String status) throws Exception {
        Article article = new Article();
        article.setId(id);
        article.setStatus(status);
        articleService.updateStatus(article);
    }
    //添加操作
    @RequestMapping("/addOne")
    public String addOne( Article article, HttpServletRequest request) throws Exception {
        String id = articleService.addOne(article, request);
        return id;
    }
    //修改文章信息
    @RequestMapping("/updateOne")
    public String updateOne(Article article)throws Exception{
        articleService.updateOne(article);
        return article.getId();
    }
    //上传封面
    @RequestMapping("/upload")
    public void uploadImg(MultipartFile upload,String id,HttpServletRequest request)throws Exception{
        if(!upload.isEmpty()){
            //获得上传文件夹路径
            String realPath = request.getSession().getServletContext().getRealPath("/article/image");
            //获取文件夹
            File file = new File(realPath);
            //判断  创建文件夹
            if(!file.exists()){
                file.mkdirs();
            }
            //获取上传文件的名字
            String filename = upload.getOriginalFilename();
            //给上传文件的名字加上时间戳
            String name = new Date().getTime() + "-" + filename;
            try {
                upload.transferTo(new File(realPath,name));
            }catch (Exception e){
                e.printStackTrace();
            }
            //修改图片的路径
            Article article = new Article();
            article.setId(id);
            article.setInsert_img(name);
            articleService.updateOne(article);
        }

    }
}
