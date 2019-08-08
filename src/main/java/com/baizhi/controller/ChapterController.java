package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/showAll")
    public Map<String,Object> showAll(Integer page,Integer rows,String album_id)throws Exception{
        Map<String, Object> map = new HashMap<>();
        //查询所有章节
        List<Chapter> list = chapterService.showAll(page, rows,album_id);
        //查询总条数
        Integer totalCount = chapterService.totalCount(album_id);
        map.put("records",totalCount);
        //总页数
        //计算总页数
        Integer totalPage =  totalCount%rows==0? totalCount/rows:totalCount/rows+1;
        map.put("total",totalPage);
        //当前页码
        map.put("page",page);
        //每页展示多少
        map.put("rows",list);
        return map;
    }
    //添加删除
    @RequestMapping("/edit")
    public String edit(Chapter chapter,String album_id,String oper)throws Exception{
        System.out.println(album_id);
        String id=null;
         if("add".equals(oper)){
             //添加操作
             chapter.setAlbum_id(album_id);
             id = chapterService.addOne(chapter);
         }else if ("del".equals(oper)){
             //删除操作
             chapterService.deleteOne(chapter.getId(), album_id);
         }
         return id;
    }
    //上传音频文件
    @RequestMapping("/uploadChapter")
    public Map<String,Object> uploadChapter(MultipartFile url, String id, HttpServletRequest request)throws Exception{
        Map<String, Object> map = chapterService.updateChapter(url, id, request);
        return map;
    }
    //下载音频文件
    @RequestMapping("/downloadChapter")
    public void downloadChapter(String fileName, HttpServletRequest request, HttpServletResponse response)throws Exception{
        //获取文件路径
        String realPath = request.getSession().getServletContext().getRealPath("/album/music");
        //根据路径读取文件
        try {
            FileInputStream inputStream = new FileInputStream(new File(realPath, fileName));

            //设置文件响应格式    响应头    attachment：以附件的形式打开    inline:在线打开
            response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(fileName,"utf-8"));
            ServletOutputStream outputStream = response.getOutputStream();
            //文件下载
            IOUtils.copy(inputStream,outputStream);

            //关闭资源
            inputStream.close();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
