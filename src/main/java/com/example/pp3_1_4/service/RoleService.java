package com.example.pp3_1_4.service;


import com.example.pp3_1_4.model.Role;

import java.util.List;


public interface RoleService {

    Role getRoleById(Long id);
    Role getRoleByName(String name);
    void addRole(Role role);
    List<Role> getAllRoles();
} 