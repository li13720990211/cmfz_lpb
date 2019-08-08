package com.baizhi.service;

import com.baizhi.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ChapterService {
    //查询所有章节并分页
    public List<Chapter> showAll(Integer page,Integer rows,String album_id);
    //查询总条数
    public Integer totalCount(String album_id);
    //修改一个章节
    //public Map<String,Object> updateOne(Chapter chapter);
    //上传音频文件
    public Map<String,Object> updateChapter(MultipartFile url, String id, HttpServletRequest request);
    //添加一个章节
    public String addOne(Chapter chapter);
    //删除一章节
    public String deleteOne(String id,String album_id);
}
