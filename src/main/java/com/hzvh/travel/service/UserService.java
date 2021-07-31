package com.hzvh.travel.service;

import com.hzvh.travel.domain.User;

public interface UserService {
     boolean registe(User user);

     boolean active(String code);

     User login(User user);

     User FindByUsernameAndEmail(User user);

     boolean updatePassword(User user);

//     boolean FindPassword(User user);
}
