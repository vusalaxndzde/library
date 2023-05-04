package com.bakulibrary.library.service;

import com.bakulibrary.library.entity.Role;
import com.bakulibrary.library.entity.User;
import com.bakulibrary.library.repository.RoleRepository;
import com.bakulibrary.library.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findRoleByRoleName(roleName);
    }

    @Override
    public void saveUser(User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));

        Role role = roleRepository.findRoleByRoleName("USER");
        if (role == null) {
            role = checkRoleExists();
        }
        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);
    }

    public Role checkRoleExists() {
        Role role = new Role();
        role.setRoleName("USER");
        return roleRepository.save(role);
    }

}
