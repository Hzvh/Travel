package com.hzvh.travel.dao;

import com.hzvh.travel.domain.Route;

import java.util.List;

public interface RouteDao {
//    1.根据cid查询总记录数
    public int findTotalCount(int cid,String rname);


    //    2.根据cid，start，pagesize查询当前页的数据集合
    public List<Route> findByPage(int cid, int start, int pageSize,String rname);

    //3.根据id查询
    public Route findOne(int rid);
}
