package com.example.pp3_1_4.service;


import com.example.pp3_1_4.model.User;
import com.example.pp3_1_4.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService  {

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.addUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }

    @Override
    public void editUser(User user) {
        userRepository.editUser(user);

    }
    @Transactional(readOnly=true)
    @Override
    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }
    @Transactional(readOnly=true)
    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByUsername(username);
    }
}