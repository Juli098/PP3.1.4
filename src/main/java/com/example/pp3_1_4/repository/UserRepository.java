package com.example.pp3_1_4.repository;


import com.example.pp3_1_4.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserRepository {
    void addUser(User user);

    void deleteUser(Long id);

    void editUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    UserDetails getUserByUsername(String username);

}