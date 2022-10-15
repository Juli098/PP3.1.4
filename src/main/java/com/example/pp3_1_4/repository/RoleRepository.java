package com.example.pp3_1_4.repository;


import com.example.pp3_1_4.model.Role;

import java.util.List;

public interface RoleRepository {

    Role getRoleById(Long id);
    Role getRoleByName(String name);
    List<Role> getAllRoles();

    void addRole(Role role);
}