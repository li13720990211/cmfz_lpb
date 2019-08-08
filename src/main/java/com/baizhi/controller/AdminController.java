package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.util.ImageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    //登陆
    /*@RequestMapping("/login")
    public String login(Admin admin, HttpSession session,String enCode)throws Exception{
        try {
            adminService.login(admin,session,enCode);
            return "main/main";
        }catch (Exception e){
            e.printStackTrace();
            return "login/login1";
        }
    }*/

    //Ajax表单验证
    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> checkout(Admin admin, String enCode ,HttpSession session)throws Exception{
        Map<String, Object> map = adminService.login(admin, session, enCode);
        return map;
    }
    //退出
    @RequestMapping("/exit")
    public String exit(HttpSession session)throws Exception{
        session.removeAttribute("admin");
        return "redirect:/login/login1.jsp";
    }
    //验证码
    @RequestMapping("/getImageCode")
    public String getImageCode(HttpSession session, HttpServletResponse response)throws Exception{
        session.removeAttribute("code");
        String code = ImageCodeUtil.getSecurityCode();
        session.setAttribute("code",code);
        BufferedImage image = ImageCodeUtil.createImage(code);
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            ImageIO.write(image,"png",stream);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
