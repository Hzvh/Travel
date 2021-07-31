package com.hzvh.travel.web.servlet;


import com.hzvh.travel.domain.User;
import com.hzvh.travel.service.FavoriteService;
import com.hzvh.travel.service.RouteService;
import com.hzvh.travel.service.impl.FavoriteServiceImpl;
import com.hzvh.travel.service.impl.RouteServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/myFavoriteServlet/*")
public class MyFavoriteServlet extends BaseServlet {
    private FavoriteService favoriteService = new FavoriteServiceImpl();
    private RouteService routeService = new RouteServiceImpl();

    public void removeFavorite(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // 1.获取线路id
        String rid = request.getParameter("rid");
        //2.获取当前登录的用户user
        User user = (User) request.getSession().getAttribute("user");
        int uid;//用户id
        if (user == null){
            //用户尚未登录
            return;
        }else {
            //用户已经登录
            uid = user.getUid();
        }
        //3.调用Service取消收藏
        favoriteService.remove(rid, uid);
    }
}
