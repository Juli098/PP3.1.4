package com.example.pp3_1_4.controller;


import com.example.pp3_1_4.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/oneUser")
    public String getUserPage(ModelMap modelMap, Principal principal) {
        modelMap.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "oneUser";
    }
}