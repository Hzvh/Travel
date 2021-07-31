package com.hzvh.travel.dao;

import com.hzvh.travel.domain.Favorite;

public interface FavoriteDao {

    /**
     * 根据rid和uid查询数据
     * @param rid
     * @param uid
     * @return
     */
    public Favorite findByRidAndUid(int rid, int uid);

    /**
     * 根据rid查询收藏次数
     * @param rid
     * @return
     */
    public int findCountByRid(int rid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     */
    void add(int rid, int uid);

    /**
     * 取消收藏
     * @param rid
     * @param uid
     */
    public void remove(int rid, int uid);

}
