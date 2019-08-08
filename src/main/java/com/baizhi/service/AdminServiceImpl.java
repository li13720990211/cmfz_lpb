package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    //登录
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String,Object> login(Admin admin, HttpSession session, String enCode) {

       /* Admin aa = adminDao.login(admin.getUsername(), admin.getPassword());
        session.setAttribute("admin",aa);
        if(aa==null)throw new RuntimeException("用户名或密码错误");
        String code = (String)session.getAttribute("code");
        if(!code.equals(enCode))throw new RuntimeException("验证码输入错误，请重新输入");*/

        String code = (String)session.getAttribute("code");
        Map<String, Object> map = new HashMap<String,Object>();
        Admin aa = adminDao.login(admin.getUsername(), admin.getPassword());
        session.setAttribute("admin",aa);
        //判断验证码
        if(code.equals(enCode)){
            //判断用户名
            if(aa!=null){
                //判断密码
                if(admin.getPassword().equals(aa.getPassword())){
                    map.put("success","200");
                    map.put("message","登陆成功");
                }else {
                    map.put("success","400");
                    map.put("message","密码错误,请重新输入");
                }
            }else{
                map.put("success","400");
                map.put("message","用户名不存在");
            }
        }else {
            map.put("success","400");
            map.put("message","验证码错误，请重新输入");
        }
        return map;
    }
}
