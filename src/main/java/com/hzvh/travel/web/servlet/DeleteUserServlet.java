package com.hzvh.travel.web.servlet;

import com.hzvh.travel.dao.impl.UserDAO;
import com.hzvh.travel.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        int uid = user.getUid();
        String password = user.getPassword();
        UserDAO userDAO = new UserDAO();
        boolean b = userDAO.deleteUser(uid, password);
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath()+"/login.html");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
