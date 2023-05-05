package com.bakulibrary.library.service;

import com.bakulibrary.library.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User findUserByEmail(String email);

    void saveUser(User user);

}
