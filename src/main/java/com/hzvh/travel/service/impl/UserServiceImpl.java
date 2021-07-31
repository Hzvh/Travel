package com.hzvh.travel.service.impl;

import com.hzvh.travel.dao.UserDAO;
import com.hzvh.travel.dao.impl.UserDaoImpl;
import com.hzvh.travel.domain.User;
import com.hzvh.travel.service.UserService;
import com.hzvh.travel.util.MailUtils;
import com.hzvh.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {

    private UserDAO userDao = new UserDaoImpl();

    //注册用户
    @Override
    public boolean registe(User user) {
        //1.查找当前数据库是否存在当前用户
        User u = userDao.findUserByUserName(user.getUsername());

        //2.判断userByUserName是否为空，如为空则可注册，如不为空当前用户已存在
        if (u != null){
            //2.1用户名存在
            return false;
        }

        //获取随机激活码
        user.setCode(UuidUtil.getUuid());
        //设置邮箱激活状态为N
        user.setStatus("N");
        //用户名不存在,保持用户信息
        userDao.addUser(user);

        //3.激活邮件发送
        String content = "<a href='http://localhost/travel/activeUserServlet?code="+user.getCode()+"'>点击激活旅游网</a>";
        System.out.println(content);
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;

    }

    //激活用户
    @Override
    public boolean active(String code) {
        //1.根据激活码查询用户对象
        User user = userDao.findByCode(code);
        if (user != null) {
            userDao.updateStatus(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

//    @Override
//    public boolean FindPassword(User user,String password) {
//        return userDao.updatePassword(user,password);
//    }

    @Override
    public User FindByUsernameAndEmail(User user) {
        return userDao.findByUsernameAndEmail(user.getUsername(),user.getEmail());
    }

    @Override
    public boolean updatePassword(User user) {
        boolean flag = userDao.updatePassword(user);
        return flag;
    }
}
