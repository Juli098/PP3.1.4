package com.example.pp3_1_4.init;

import com.example.pp3_1_4.model.Role;
import com.example.pp3_1_4.model.User;
import com.example.pp3_1_4.service.RoleService;
import com.example.pp3_1_4.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {


    private UserService userService;


    private RoleService roleService;

    public ApplicationRunnerImpl(UserService userService, RoleService roleService) {
        this.userService = userService;

        this.roleService = roleService;
    }

    @Override
    public void run(ApplicationArguments args) {
        List<User> users = userService.getAllUsers();

        if (users.isEmpty()) {
            roleService.addRole(new Role("ROLE_ADMIN"));
            roleService.addRole(new Role("ROLE_USER"));
            Role admin = roleService.getRoleById(1L);
            Role user = roleService.getRoleById(2L);
            Set<Role> adminRole = new HashSet<>();
            Set<Role> userRole = new HashSet<>();
            adminRole.add(admin);
            userRole.add(user);
            userService.addUser(new User( "Misha", ("admin"),23, adminRole ));
            userService.addUser(new User( "Dima", ("user"), 32, userRole ));
            userService.addUser(new User("Kostya", ("dimab"), 54,  userRole));
            userService.addUser(new User("vasyap", ("vasyap"),12, userRole));
            userService.addUser(new User("kostyap", ("kostyag"),10,userRole));

        }
    }
}