package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    //分页查询并展示
    @RequestMapping("/showAll")
    @ResponseBody
    public Map<String,Object> showAll(Integer page,Integer rows)throws Exception{
        //查询所有用户
        List<Banner> banners = bannerService.showAll(page, rows);
        //查询总条数
        Integer totalCount = bannerService.totalCount();
        HashMap<String, Object> map = new HashMap<>();
        //当前页号
        map.put("page",page);
        //总条数
        map.put("records",totalCount);
        //计算总页数
        Integer pageCount = 0;
        if(totalCount%rows==0){
            pageCount=totalCount/rows;
        }else {
            pageCount=totalCount/rows+1;
        }
        map.put("total",pageCount);
        //每页具体多少展示多少
        map.put("rows",banners);
        return map;
    }
    //增删改
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(Banner banner,String oper)throws Exception{
        String id = null;
        if("add".equals(oper)){
            //添加操作
            id = bannerService.addOne(banner);
        }else if ("edit".equals(oper)){
            //修改操作
            id = banner.getId();
            bannerService.updateOne(banner);
        }else if ("del".equals(oper)){
            //删除操作
            bannerService.deleteOne(banner.getId());
        }
        return id;
    }
    //上传图片
    @RequestMapping("/uploadBanner")
    @ResponseBody
    public void uploadBanner(MultipartFile img_path, String id, HttpServletRequest request)throws Exception{
        if(!img_path.isEmpty()){
            //获得上传文件夹的路径
            String realPath = request.getSession().getServletContext().getRealPath("/banner/image");
            //获取文件夹
            File file = new File(realPath);
            //创建文件夹
            if(!file.exists()){
                file.mkdirs();
            }
            //获取上传文件的名字
            String filename = img_path.getOriginalFilename();

            String name = new Date().getTime() + "-" + filename;
            //文件上传
            try {
                img_path.transferTo(new File(realPath,name));
            }catch (IOException e){
                e.printStackTrace();
            }
            Banner banner = new Banner();
            banner.setId(id);
            banner.setImg_path(name);;
            //做修改
            bannerService.updateOne(banner);
        }
    }
    //修改状态
    @RequestMapping("/updateStatus")
    @ResponseBody
    public Map<String,Object> updateStatus(String id,String status)throws Exception{
        Banner banner = new Banner();
        banner.setId(id);
        banner.setStatus(status);
        Map<String, Object> map = bannerService.updateStatus(banner);
        return map;
    }
}
