package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.util.UUIDUtil;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterDao chapterDao;
    @Autowired
    private AlbumDao albumDao;

    //查询所有并分页
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Chapter> showAll(Integer page, Integer rows, String album_id) {
        List<Chapter> chapters = chapterDao.selectAll(page, rows, album_id);
        return chapters;
    }
    //查询总条数
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)

    public Integer totalCount(String album_id) {
        Integer totalCount = chapterDao.totalCount(album_id);
        return totalCount;
    }
    //上传音频文件
    @Override
    public Map<String, Object> updateChapter(MultipartFile url, String id, HttpServletRequest request) {
        HashMap<String,Object> map = new HashMap<String, Object>();
        //说的上传文件夹的路径
        String realPath = request.getSession().getServletContext().getRealPath("/album/music");
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        //获得上传的文件名
        String filename = url.getOriginalFilename();
        //给文件名加上时间戳前缀
        String name = new Date().getTime()+"-"+filename;

        //文件上传
        try {
            url.transferTo(new File(realPath,name));
            //获取文件的大小精确到整数位
            long s = url.getSize();
            String size = s/1024/1024+"MB";
            //文件大小精确到小数点的后两位
            DecimalFormat format = new DecimalFormat("0.00");
            String sz = String.valueOf(s);
            double dd = Double.valueOf(sz) / 1024 / 1024;
            String newsize = format.format(dd)+"MB";
            System.out.println(newsize);
            //获取文件的时长
            AudioFileIO fileIO = new AudioFileIO();
            AudioFile audio = fileIO.readFile(new File(realPath, name));
            AudioHeader audioHeader = audio.getAudioHeader();
            //获取时长   分钟 秒
            int length = audioHeader.getTrackLength();
            System.out.println(length);
            String duration = length/60+"分"+length%60+"秒";

            //修改音频信息
            Chapter chapter = new Chapter();
            chapter.setId(id);
            chapter.setUrl(name);
            chapter.setSize(newsize);
            chapter.setDuration(duration);
            chapterDao.updateOne(chapter);

        }catch (Exception e){
            e.printStackTrace();
            map.put("success","400");
            map.put("message","上传失败");
        }
        return map;
    }

    //添加一个章节
    @Override
    public String addOne(Chapter chapter) {
        String id = UUIDUtil.getUUID();
        chapter.setId(id);
        chapter.setUp_date(new Date());
        chapterDao.insertOne(chapter);
        //修改专辑集数
        Album album = new Album();
        album.setId(chapter.getAlbum_id());
        Integer count = chapterDao.totalCount(chapter.getAlbum_id());
        album.setCount(count);
        albumDao.updateOne(album);

        return id;
    }
    //删除一个章节
    @Override
    public String deleteOne(String id,String album_id) {
        chapterDao.deleteOne(id);
        //修改专辑集数
        Album album = new Album();
        album.setId(album_id);
        Integer count = chapterDao.totalCount(album_id);
        album.setCount(count);
        albumDao.updateOne(album);

        return  null;
    }

}
