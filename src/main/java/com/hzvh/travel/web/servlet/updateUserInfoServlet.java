package com.hzvh.travel.web.servlet;


import com.hzvh.travel.dao.impl.UserDAO;
import com.hzvh.travel.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateUserInfoServlet")
public class updateUserInfoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取User
        User user = (User) req.getSession().getAttribute("user");
        System.out.println("设置前："+user.toString());
        UserDAO dao = new UserDAO();
        String password = req.getParameter("password");
        System.out.println(password);

        if(user.getPassword().equals(password)){

            // 获取填入表单的相关参数
            String name = req.getParameter("name");
            String birthday = req.getParameter("birthday");
            String sex = req.getParameter("sex");
            String telephone = req.getParameter("telephone");
            String email = req.getParameter("email");
            System.out.println("表单："+name+"\t"+birthday+"\t"+sex+"\t"+telephone+"\t"+email+"\t"+password);
            user.setName(name);
            user.setBirthday(birthday);
            user.setSex(sex);
            user.setTelephone(telephone);
            user.setEmail(email);

            System.out.println("设置后："+user.toString());
            dao.updateUserInfo(user);
            System.out.println("执行更新完成");

        }else {
            System.out.println("密码错误");
        }
        resp.setContentType("application/json;charset=utf-8");
        resp.sendRedirect(req.getContextPath()+"/userInfo.html");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
