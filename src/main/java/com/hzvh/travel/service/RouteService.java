package com.hzvh.travel.service;
//线路的service

import com.hzvh.travel.domain.PageBean;
import com.hzvh.travel.domain.Route;

public interface RouteService {
//根据类别进行分页查询
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname);
//根据id查询
    Route findOne(String rid);
}
