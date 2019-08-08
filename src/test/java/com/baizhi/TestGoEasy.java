package com.baizhi;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.UUIDUtil;
import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGoEasy {

    @Autowired
    private UserService userService;

    @Test
    public void testGoEasy(){
        //发布消息  发布地址：appkey
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-ecaae94e21524c4486a88e9df5cd1ff0");
        //参数:管道(标识)名称    发布内容
        goEasy.publish("xiaobo", "Hello, hahaha!");
    }
    @Test
    public void test1(){
        User user = new User();
        user.setId(UUIDUtil.getUUID().toString());
        user.setName("小小波");
        user.setAhama("波波");
        user.setPic_img("1.jpg");
        user.setPhone("13720990211");
        user.setCity("山西省");
        user.setSex("女");
        user.setReg_date(new Date());
        user.setGuru_id("2");
        user.setStatus("正常");

        userService.addOne(user);
    }
}
