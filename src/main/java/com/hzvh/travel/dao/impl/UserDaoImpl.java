package com.hzvh.travel.dao.impl;

import com.hzvh.travel.dao.UserDAO;
import com.hzvh.travel.domain.User;
import com.hzvh.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDAO {

//    @Override
//    public User findUserByUserName(String _username) {
//        User user = new User();
//        Connection conn = null;
//        Statement stat = null;
//        ResultSet rs = null;
//
//        try {
//            conn = JDBCUtils.getConnection();
//            stat = conn.createStatement();
//            rs = stat.executeQuery("select * from tab_user where  username = _username");
//            //4 处理执行结果
//            if(rs.next()) {
//                user.setUid(rs.getInt("uid"));
//                user.setUsername(rs.getString("username"));
//                user.setUsername(rs.getString("password"));
//                user.setUsername(rs.getString("name"));
//                user.setUsername(rs.getString("birthday"));
//                user.setUsername(rs.getString("sex"));
//                user.setUsername(rs.getString("telephone"));
//                user.setUsername(rs.getString("email"));
//                user.setUsername(rs.getString("status"));
//                user.setUsername(rs.getString("code"));
//
//            }
//        }  catch(SQLException e) {
//            e.printStackTrace();
//        }finally {
//            JDBCUtils.close(conn,stat,rs);
//        }
//        return user;
//    }\

    private JdbcTemplate jdbcTemplate= new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public User findUserByUserName(String _username) {
        User user = null;
        try{
        //1.编写sql语句，查询数据库
        String sql = "select * from tab_user where  username = ?";

        //2.执行上述sql语句,保存user对象
        user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), _username);
            }catch (Exception e){

        }

        return user;
    }

    @Override
    public void addUser(User user) {
        //1.定义sql
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        //2.执行sql

        jdbcTemplate.update(sql,user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode()
        );
    }

    //根据激活码查找user对象
    @Override
    public User findByCode(String code) {
        User user = null;
        try {
            String sql = "select * from tab_user where code = ?";
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return user;
    }
    //更新用户的邮箱状态
    @Override
    public void updateStatus(User user) {
        String sql = "update tab_user set status = 'Y' where uid = ?";
        jdbcTemplate.update(sql,user.getUid());

    }

    //根据用户名和密码查询
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        try{
            //1.编写sql语句，查询数据库
            String sql = "select * from tab_user where  username = ? and password = ?";

            //2.执行上述sql语句,保存user对象
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,password);
        }catch (Exception e){

        }

        return user;
    }

//    @Override
//    public boolean updatePassword(User user,String password) {
//        String sql = "update tab_user set password=? where username = ? and email = ?";
//        jdbcTemplate.update(sql,password,user.getUsername(),user.getEmail());
//        return false;
//    }


    @Override
    public User findByUsernameAndEmail(String username, String email) {
        User user = null;
        try{
            //1.编写sql语句，查询数据库
            String sql = "select * from tab_user where  username = ? and email = ?";

            //2.执行上述sql语句,保存user对象
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,email);
        }catch (Exception e){

        }

        return user;
    }

    @Override
    public boolean updatePassword(User user) {
        String sql = "update tab_user set password = ? where username = ?";
        jdbcTemplate.update(sql,user.getPassword(),user.getUsername());
        return true;
    }
}

