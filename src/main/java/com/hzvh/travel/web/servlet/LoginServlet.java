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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //登录验证码
        //获取验证码
        String check = request.getParameter("check");

        //从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");

        //不过验证码是否通过，移除session中验证码记录
        session.removeAttribute("CHECKCODE_SERVER");

        //将session中的验证码和用户输入的比较
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误！");
            //将resultInfo对象序列化成json
            ObjectMapper objectMapper = new ObjectMapper();
            String s = objectMapper.writeValueAsString(resultInfo);

//            //将json数据写回客户端
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(s);
            return;
        }


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
        User u = userService.login(user);
//        System.out.println("Adawdwad");
        ResultInfo resultInfo = new ResultInfo();
        //4.判断用户是否为空
        if (u == null){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名或密码错误");
        }
        //5.判断用户是否激活邮箱
        if (u != null && !"Y".equals(u.getStatus())){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("您尚未激活，请先激活!");
        }
        //6.判断登录成功
        if(u != null && "Y".equals(u.getStatus())){

            resultInfo.setFlag(true);
            request.getSession().setAttribute("user", u);
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
        this.doPost(request, response);
    }
}
