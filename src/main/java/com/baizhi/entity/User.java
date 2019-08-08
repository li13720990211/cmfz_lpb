package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ExcelTarget(value = "user")
public class User implements Serializable {

    @Excel(name = "ID")
    private String id;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "法名")
    private String ahama;
    @Excel(name = "头像",type = 2)
    private String pic_img;
    @Excel(name = "手机号")
    private String phone;
    @Excel(name = "密码")
    private String password;
    @ExcelIgnore
    private String salt;
    @Excel(name = "性别")
    private String sex;
    @Excel(name = "城市")
    private String city;
    @Excel(name = "签名")
    private String sign;
    @Excel(name = "状态")
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册时间",width = 40)
    private Date reg_date;
    @Excel(name = "上师id")
    private String guru_id;
    
}
