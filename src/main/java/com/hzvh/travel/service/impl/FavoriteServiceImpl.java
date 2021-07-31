package com.hzvh.travel.service.impl;

import com.hzvh.travel.dao.FavoriteDao;
import com.hzvh.travel.dao.RouteDao;
import com.hzvh.travel.dao.impl.FavoriteDaoImpl;
import com.hzvh.travel.dao.impl.RouteDaoImpl;
import com.hzvh.travel.domain.Favorite;
import com.hzvh.travel.service.FavoriteService;


public class FavoriteServiceImpl implements FavoriteService {

    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    private RouteDao routeDao = new RouteDaoImpl();

    @Override
    public boolean isFavorite(String rid, int uid) {

        Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid), uid);

        return favorite != null;//如果对象有值，则返回true，否则返回false
    }

    @Override
    public void add(String rid, int uid) {
        favoriteDao.add(Integer.parseInt(rid),uid);
    }

    @Override
    public void remove(String rid, int uid) {
        favoriteDao.remove(Integer.parseInt(rid), uid);
    }

}
