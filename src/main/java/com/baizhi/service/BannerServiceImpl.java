package com.baizhi.service;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDao bannerDao;

    //查询所有并分页
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Banner> showAll(Integer page, Integer rows) {
        List<Banner> list = bannerDao.selectAll(page, rows);
        return list;
    }

    //总条数
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Integer totalCount() {
        Integer totalCount = bannerDao.totalCount();
        return totalCount;
    }

    //添加一个轮播图
    @Override
    public String addOne(Banner banner) {
        String id = UUIDUtil.getUUID();
        banner.setId(id);
        banner.setStatus("正常");
        banner.setUp_date(new Date());
        bannerDao.insertOne(banner);
        return id;
    }

    //修改轮播图
    @Override
    public void updateOne(Banner banner) {
        if (banner.getImg_path().equals("")) {
            banner.setImg_path(null);
        } else {
            banner.setUp_date(new Date());
        }
        bannerDao.updateOne(banner);
    }

    //删除一个轮播图
    @Override
    public void deleteOne(String id) {
        bannerDao.deleteOne(id);
    }

    //修改轮播图状态
    @Override
    public Map<String, Object> updateStatus(Banner banner) {
        Map<String, Object> map = new HashMap<>();
        try {
            bannerDao.updateOne(banner);
            map.put("success", "200");
            map.put("message", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", "400");
            map.put("message", "修改失败");
        }
        return map;
    }
}
