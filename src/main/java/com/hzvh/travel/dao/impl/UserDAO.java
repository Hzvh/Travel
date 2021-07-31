package com.hzvh.travel.dao.impl;

import com.hzvh.travel.domain.User;
import com.hzvh.travel.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {


    public User findAnUserByUidAndPW(int uid, String password) {
        User user = new User();
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select * from tab_user where uid=? and password=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,uid);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if (rs.next()){
                user.setUid(uid);
                user.setPassword(password);
                user.setUsername(rs.getString("username"));
                user.setName(rs.getString("name"));
                user.setBirthday(rs.getString("birthday"));
                user.setSex(rs.getString("sex"));
                user.setTelephone(rs.getString("birthday"));
                user.setEmail(rs.getString("email"));
                user.setStatus(rs.getString("status"));
                user.setCode(rs.getString("code"));

            }

        } catch (SQLException throwables) {
            System.out.println("查找异常");
            throwables.printStackTrace();
        }
        return user;
    }

    /*
    删除注销
     */

    public boolean deleteUser(int uid,String password){
        boolean flag =false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "delete from tab_user where uid=? and password=?";
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,uid);
            ps.setString(2,password);
            int i = ps.executeUpdate();
            if (i>0){
                flag = true;
                System.out.println("删除：" + i);
            }
            ps.close();

        } catch (SQLException throwables) {
            System.out.println("查找异常");
            throwables.printStackTrace();
        }
        return flag;
    }

    public boolean updateUserInfo(User user) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        System.out.println(user.toString());
        try {
            conn = JDBCUtils.getConnection();
            String sql = "update tab_user set username=?,name=?,birthday=?,sex=?,telephone=?,email=?,status=?,code=? where uid=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2,user.getName());
            ps.setString(3,user.getBirthday());
            ps.setString(4,user.getSex());
            ps.setString(5,user.getTelephone());
            ps.setString(6,user.getEmail());
            ps.setString(7,user.getStatus());
            ps.setString(8,user.getCode());
            ps.setInt(9, user.getUid());

            int rs = ps.executeUpdate();
            if(rs>0)
                flag = true;
            conn.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    }

