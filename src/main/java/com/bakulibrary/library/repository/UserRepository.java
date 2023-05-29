package com.bakulibrary.library.repository;

import com.bakulibrary.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);

    User findUserByEmailAndPassword(String email, String password);

    @Query("update User u set u.password = :password where u.email = :email")
    void updatePassword(@Param("password") String password, @Param("email") String email);

}
