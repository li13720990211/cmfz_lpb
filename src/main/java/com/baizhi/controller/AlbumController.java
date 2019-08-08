package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    //查询所有并分页
    @RequestMapping("/showAll")
    @ResponseBody
    public Map<String,Object> showAll(Integer page,Integer rows)throws Exception{
        Map<String,Object> map =new HashMap<String, Object>();
        //查询所有
        List<Album> albums = albumService.showAll(page, rows);
        //当前页号
        map.put("page",page);
        //查询总条数
        Integer totalCount = albumService.totalCount();
        map.put("records",totalCount);
        //计算总页数
        Integer totalPage =  totalCount%rows==0? totalCount/rows:totalCount/rows+1;
        map.put("total",totalPage);
        //每页具体多少展示多少
        map.put("rows",albums);
        return map;
    }
    //增删改方法
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(Album album,String oper)throws Exception{
        String id = null;
        if("add".equals(oper)){
            //添加操作
            id = albumService.addOne(album);
        }else if("edit".equals(oper)){
            //修改操作
            id = album.getId();
            albumService.updateOne(album);
        }else if("del".equals(oper)){
            albumService.deleteOne(album.getId());
        }
        return id;
    }
    //上传头像
    @RequestMapping("/uploadCover")
    @ResponseBody
    public void uploadCover(MultipartFile cover_img, String id, HttpServletRequest request)throws Exception{
        System.out.println(cover_img);
        System.out.println(id);
        if(!cover_img.isEmpty()){
            //获取上传文件夹的路径
            String realPath = request.getSession().getServletContext().getRealPath("album/image");
            //获取文件夹
            File file = new File(realPath);
            //创建新的文件夹
            if(!file.exists()){
                file.mkdirs();
            }
            //获取上传文件的名字
            String filename = cover_img.getOriginalFilename();
            //随机时间毫秒拼接上传文件的名字
            String name = new Date().getTime()+"-"+filename;
            //文件上传
            try {
                cover_img.transferTo(new File(realPath,name));
            }catch (Exception e){
                e.printStackTrace();
            }
            //修改图片路径  并存入数据库
            Album album = new Album();
            album.setCover_img(name);
            album.setId(id);
            albumService.updateOne(album);
        }
    }
}
