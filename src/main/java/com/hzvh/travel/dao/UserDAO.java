package com.hzvh.travel.dao;

import com.hzvh.travel.domain.User;

public interface UserDAO {

    //根据用户名查询用户信息
    public User findUserByUserName(String _username);

    //添加用户
    public void addUser(User user);

    User findByCode(String code);

    void updateStatus(User user);

    User findByUsernameAndPassword(String username, String password);

//    boolean updatePassword(User user,String password);
    
    User findByUsernameAndEmail(String username,String email);

    boolean updatePassword(User user);
}
