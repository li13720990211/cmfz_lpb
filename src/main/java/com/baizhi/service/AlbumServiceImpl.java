package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private ChapterDao chapterDao;

    //查询所有专辑并分页
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Album> showAll(Integer page, Integer rows) {
        List<Album> list = albumDao.selectAll(page, rows);
        return list;
    }
    //查询总条数
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Integer totalCount() {
        Integer totalCount = albumDao.totalCount();
        return totalCount;
    }
    //添加一个专辑
    @Override
    public String addOne(Album album) {
        String id = UUIDUtil.getUUID();
        album.setId(id);
        album.setPub_date(new Date());
        album.setCount(0);
        albumDao.insertOne(album);

        return id;
    }
    //修改一个专辑
    @Override
    public void updateOne(Album album) {
        if(album.getCover_img().equals("")){
            album.setCover_img(null);
        }
        albumDao.updateOne(album);
    }

    @Override
    public void deleteOne(String id) {
        List<Chapter> chapters = chapterDao.selectAllById(id);
        if(chapters!=null){
            albumDao.deleteOne(id);
        }
    }
}
