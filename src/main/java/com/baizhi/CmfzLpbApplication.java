package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//指定该类为入口类
@MapperScan("com.baizhi.dao")//创建dao实体类对象
public class CmfzLpbApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmfzLpbApplication.class, args);
    }

}
