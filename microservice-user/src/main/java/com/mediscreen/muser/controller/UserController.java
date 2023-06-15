package com.mediscreen.muser.controller;

import com.mediscreen.muser.exception.UserNotFoundException;
import com.mediscreen.muser.model.User;
import com.mediscreen.muser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    public UserService userService;

    @GetMapping(name = "/v1/users")
    public List<User> getAllUsers() {
        List<User> users = userService.getUsers();
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found in database");
        }

        return users;
    }
}
