package com.baizhi.controller;


import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ArticleService;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
public class InterfaceController {


    @Autowired
    private BannerService bannerService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private ArticleService articleService;


    @RequestMapping("first_page")
    public Map<String,Object> first_page(String uid,String type,String sub_type){

        HashMap<String, Object> map = new HashMap<>();

        //首页展示数据
        if(type.equals("all")){
            List<Banner> banners = bannerService.showAll(1, 5);
            List<Album> albums = albumService.showAll(1, 6);
            List<Article> articles = articleService.showAll(1, 2);

            map.put("banner",banners);
            map.put("album",albums);
            map.put("article",articles);


            //专辑展示数据
        }else if (type.equals("wen")){
            List<Album> albums = albumService.showAll(1, 10);
            map.put("album",albums);

            //文章展示数据
        }else if(type.equals("si")){

            //上师言教
            if (sub_type.equals("ssyj")){
                List<Article> articles = articleService.showAll(1, 2);
                map.put("article",articles);
                //显密法要
            }else if (sub_type.equals("xmfy")){
                List<Article> articles = articleService.showAll(2, 3);
                map.put("article",articles);
            }
        }

        return map;
    }
}
