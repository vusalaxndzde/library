package com.bakulibrary.library.service;

import com.bakulibrary.library.entity.Role;
import com.bakulibrary.library.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User findUserByEmail(String email);

    Role findRoleByRoleName(String roleName);

    void saveUser(User user);

}
