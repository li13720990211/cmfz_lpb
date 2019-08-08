package com.baizhi.service;

import com.baizhi.entity.Admin;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface AdminService {
    //登陆
    public Map<String,Object> login(Admin admin, HttpSession session, String enCode);
}
