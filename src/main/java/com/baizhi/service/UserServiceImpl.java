package com.baizhi.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.JSON;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.China;
import com.baizhi.entity.City;
import com.baizhi.entity.User;
import com.baizhi.util.UUIDUtil;
import io.goeasy.GoEasy;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    //查询所有并分页
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<User> showAll(Integer page, Integer rows) {
        List<User> users = userDao.selectAll(page, rows);
        return users;
    }
    //查询总条数
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Integer totalCount() {
        Integer totalCount = userDao.totalCount();
        return totalCount;
    }
    //修改状态
    @Override
    public void updateStatus(User user) {
        userDao.updateStatus(user);

    }
    //删除一个用户
    @Override
    public void deleteOne(String id) {
        userDao.deleteOne(id);

        Map<String, Object> map = Echarts();
        String jsonString = JSON.toJSONString(map);
        System.out.println("调用GoEasy");
        //发布消息  发布地址：appkey
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-ecaae94e21524c4486a88e9df5cd1ff0");
        //参数:管道(标识)名称    发布内容
        goEasy.publish("xiaobo", jsonString);
    }
    //添加一个用户
    @Override
    public void addOne(User user) {
        user.setId(UUIDUtil.getUUID().toString());
        user.setName("小波");
        user.setAhama("波波");
        user.setPic_img("2.jpg");
        user.setPhone("13720990211");
        user.setPassword("123456");

        Random random1 = new Random();
        int i1 = random1.nextInt(5);
        if(i1==0){
            user.setCity("山西");
        }else if(i1==1){
            user.setCity("北京");
        }else if(i1==2){
            user.setCity("山东");
        }else if (i1==3){
            user.setCity("河北");
        }else {
            user.setCity("广西");
        }
        Random random2 = new Random();
        int i2 = random2.nextInt(2);
        if (i2 == 0) {
            user.setSex("男");
        } else {
            user.setSex("女");
        }
        Random rndYear = new Random();
        int year = rndYear.nextInt(20)+2000;//生成2000年到2019的整数；年
        Random rndMonth = new Random();
        int month = rndMonth.nextInt(12)+1;//生成1月到12月的整数；月
        Random rndDay = new Random();
        int day = rndDay.nextInt(30)+1;//生成1日到30日的整数；日
        String date=year+"-"+month+"-"+day;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date reg_date =null;
        try {
           reg_date = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setReg_date(reg_date);

        user.setGuru_id("2");
        user.setStatus("正常");
        userDao.insertOne(user);

        Map<String, Object> map = Echarts();
        System.out.println("调用GoEasy");
        String jsonString = JSON.toJSONString(map);
        //发布消息  发布地址：appkey
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-ecaae94e21524c4486a88e9df5cd1ff0");
        //参数:管道(标识)名称    发布内容
        goEasy.publish("xiaobo", jsonString);


    }

    //查询所有用户并导出
    @Override
    public Map<String,String> EasyPoi() {
        List<User> users = userDao.EasyPoi();
        for (User user : users) {
            user.setPic_img("D://IDEA代码/cmfz_lpb/src/main/webapp/user/image/"+user.getPic_img());
        }
        //导出
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息表", "用户"), User.class, users);

        HashMap<String, String> map = new HashMap<>();

        //开始导出
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("D://飞q资料/User.xls"));
            workbook.write(fileOutputStream);
            //关闭资源
            fileOutputStream.close();
            workbook.close();
            map.put("message","导出成功");
            System.out.println("---------导出成功----------");
        }catch (Exception e){
            e.printStackTrace();
            map.put("message","导出失败");
        }

        return map;
    }

    //用户注册量展示
    @Override
    public Map<String,Object> Echarts() {

        List<String> month = Arrays.asList("1月份", "2月份", "3月份", "4月份", "5月份", "6月份", "7月份", "8月份", "9月份", "10月份", "11月份", "12月份");

        List<Integer> boys = new ArrayList<Integer>();
        for(int i=1; i<13 ;i++){
            if(i<10){
              boys.add(userDao.count("%-"+"0" +i+ "-%", "男"));
            }else {
                boys.add(userDao.count("%-" +i+ "-%", "男"));
            }
        }

        List<Integer> girls = new ArrayList<Integer>();

        for(int i=1; i<13 ;i++){
            if(i<10){
                girls.add(userDao.count("%-"+"0" +i+ "-%", "女"));
            }else {
                girls.add(userDao.count("%-" +i+ "-%", "女"));
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("month",month);
        map.put("boys",boys);
        map.put("girls",girls);
        return map;
    }

    //用户注册量分布图
    @Override
    public List<China> EchartsMap() {

        List<City> boys = userDao.citys("男");
        List<City> girls = userDao.citys("女");
        System.out.println(boys);
        ArrayList<China> chinas = new ArrayList<>();
        chinas.add(new China("小姐姐",girls));
        chinas.add(new China("小哥哥",boys));

        return chinas;
    }


}
