package com.hzvh.travel.dao;

import com.hzvh.travel.domain.Seller;

public interface SellerDao {
    /**
     * 根据id查询商家
     * @param id
     * @return
     */
    public Seller findById(int id);
}
