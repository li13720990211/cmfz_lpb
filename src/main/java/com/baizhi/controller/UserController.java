package com.baizhi.controller;

import com.baizhi.entity.China;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.PhoneCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/showAll")
    public Map<String ,Object> showAll(Integer page,Integer rows)throws Exception{
        Map<String, Object> map = new HashMap<>();
        //每页展示
        List<User> users = userService.showAll(page, rows);
        map.put("rows",users);
        //查询所有条数
        Integer totalCount = userService.totalCount();
        map.put("records",totalCount);
        //计算总页数
        Integer pageCount = totalCount%rows==0? totalCount/rows:totalCount/rows+1;
        map.put("total",pageCount);
        //当前页
        map.put("page",page);

        return map;
    }

    //添加操作
    @RequestMapping("/addOne")
    public void addOne(User user)throws Exception{
        userService.addOne(user);
    }
    //添加删除操作
    @RequestMapping("/edit")
    public void edit(User user,String oper)throws Exception{
        if(oper.equals("del")){
            userService.deleteOne(user.getId());
        }
    }
    //修改状态
    @RequestMapping("/updateStatus")
    public void updateStatus(String id,String status)throws Exception{
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        userService.updateStatus(user);
    }

    //查询所有 并导出到表格文档里
    @RequestMapping("/easyPoi")
    public Map<String,String> easyPoi()throws Exception{

        Map<String, String> map = userService.EasyPoi();
        return map;
    }

    //用户注册量展示
    @RequestMapping("/echarts")
    public Map<String,Object> echarts()throws Exception{

        Map<String, Object> map = userService.Echarts();
        return map;

    }

    //用户注册量分布图
    @RequestMapping("/echartsMap")
    public List<China> echartsMap()throws Exception{

        List<China> chinas = userService.EchartsMap();
        return chinas;
    }

    //向手机发送验证码
    @RequestMapping("/phoneCode")
    public void phoneCode(HttpServletRequest request)throws Exception{
        String message = PhoneCodeUtil.getPhoneMessage("13720990211");
        System.out.println(message);
        String code = PhoneCodeUtil.getCode();
        System.out.println(code);
        request.getSession().setAttribute("phoneCode",code);
    }
}
