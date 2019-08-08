package com.baizhi.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/editor")
public class EditorController {

    //上传图片
    @RequestMapping("/upload")
    public Map<String, Object> upload(MultipartFile photo, HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();
        System.out.println("执行文件上传");

        String realPath = request.getSession().getServletContext().getRealPath("/editor/photo");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }

        //获取文件名
        String filename = photo.getOriginalFilename();
        //给文件名加上时间戳
        String name = new Date().getTime() + "-" + filename;
        //获取http
        String scheme = request.getScheme();
        //获取ip   localhost
        String serverName = request.getServerName();
        //获取端口号  8989
        int serverPort = request.getServerPort();
        //获取项目名 cmfz
        String contextPath = request.getContextPath();

        //网络路径的拼接    http://localhost:8989/cmfz
        String serverPath = scheme + "://" + serverName + ":" + serverPort + "/" + contextPath + "/editor/photo/" + name;
        System.out.println(serverPath);
        //文件上传
        try {
            photo.transferTo(new File(realPath, name));

            map.put("error", 0);
            map.put("url", serverPath);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", 1);
            map.put("url", "上传失败");
        }
        return map;
    }

    @RequestMapping("/showAllPhoto")
    public Map<String, Object> showAllPhoto(HttpServletRequest request) throws Exception {
        Map<String, Object> maps = new HashMap<>();

        ArrayList<Object> lists = new ArrayList<>();

        //获取图片文件夹的路径
        String realPath = request.getSession().getServletContext().getRealPath("/editor/photo");

        //获取文件夹
        File file = new File(realPath);

        //获取文件夹下的所有文件名字
        String[] names = file.list();
        for(int i=0; i<names.length; i++){
            //获取文件名字
            String name = names[i];
            HashMap<String, Object> map = new HashMap<>();

            map.put("is_dir",false); //是否是文件夹
            map.put("has_file",false); //是否是文件
            File f = new File(realPath, name);
            map.put("filesize",f.length()); //文件的大小
            map.put("is_photo",true); //是否是图片
            String extension = FilenameUtils.getExtension(name);
            map.put("filetype",extension); //图片的格式
            map.put("filename",name); //文件的名字

            //字符串拆分
            String[] split = name.split("-");
            String times = split[0];
            long time = Long.parseLong(times);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
            String format = dateFormat.format(time);
            map.put("datetime",format);//上传的时间

            //封装进集合
            lists.add(map);

        }
        maps.put("current_url","http://localhost:8989/cmfz/editor/photo"); //网络路径
        maps.put("total_count",lists.size());  //文件数量
        maps.put("file_list",lists);  //文件集合

        return maps;
    }
}
