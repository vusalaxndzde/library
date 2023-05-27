package com.bakulibrary.library.service.inter;

import com.bakulibrary.library.dto.UserDTO;
import com.bakulibrary.library.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User findUserByEmail(String email);

    void saveUser(User user);

    User convertUserDTOToUser(UserDTO userDTO);

}
