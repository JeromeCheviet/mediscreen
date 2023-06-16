package com.mediscreen.muser.controller;

import com.mediscreen.muser.exception.UserNotFoundException;
import com.mediscreen.muser.model.User;
import com.mediscreen.muser.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    public UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "/v1/users")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        logger.debug("get /v1/users controller");
        Iterable<User> users = userService.getUsers();
//        if (users.iterator().) {
//            logger.warn("users list is empty");
//            throw new UserNotFoundException("No users found in database");
//        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
