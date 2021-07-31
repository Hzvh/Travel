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

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

            //将json数据写回客户端
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(s);
            return;
        }

        //1.得到数据
        Map<String, String[]> parameterMap = request.getParameterMap();

        User user = new User();
        try {
            //2.将map对象封装到user对象中
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
       // 3.调用userSevice进行注册
        UserService userService = new UserServiceImpl();
        boolean flag = userService.registe(user);
        //4.调用ResultInfo对象
        ResultInfo resultInfo = new ResultInfo();
        if (flag){
            resultInfo.setFlag(true);
        }else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("注册失败！");
        }

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
