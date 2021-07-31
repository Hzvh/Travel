package com.hzvh.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzvh.travel.domain.ResultInfo;
import com.hzvh.travel.domain.User;
import com.hzvh.travel.service.UserService;
import com.hzvh.travel.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/findPasswordServlet")
public class FindPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取用户名和密码
        Map<String, String[]> parameterMap = request.getParameterMap();

        //2.封装user对象
        User user = new User();
        try{
            BeanUtils.populate(user,parameterMap);
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }

        //3.调用方法查询是否存在该用户
        UserService userService = new UserServiceImpl();
        User u = userService.FindByUsernameAndEmail(user);
//        System.out.println(u.getCode());
//        System.out.println("Adawdwad");
        ResultInfo resultInfo = new ResultInfo();
        //4.判断用户是否为空
        if (u == null){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("当前用户不存在");
        }
        //5.判断用户是否激活邮箱
       else  if (u != null && !"Y".equals(u.getStatus())){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("您尚未激活，请先激活!");
        }
//        //6.判断登录成功
//        else if(u != null && "Y".equals(u.getStatus())){
//
//            resultInfo.setFlag(true);
////            request.getSession().setAttribute("user", u);
//        }
        else{
            boolean flag = userService.updatePassword(user);
            System.out.println(flag);
            if(flag){
                resultInfo.setFlag(true);
            }
        }

        //响应数据
//        ObjectMapper objectMapper = new ObjectMapper();
////        String s = objectMapper.writeValueAsString(info);
//
//        //将json数据写回客户端
//        response.setContentType("application/json;charset=utf-8");
//        objectMapper.writeValue(response.getOutputStream(),resultInfo);
        //将resultInfo对象序列化成json
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(resultInfo);

        //将json数据写回客户端
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(s);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
