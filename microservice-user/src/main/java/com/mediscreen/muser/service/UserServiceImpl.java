package com.mediscreen.muser.service;

import com.mediscreen.muser.exception.UserNotFoundException;
import com.mediscreen.muser.model.User;
import com.mediscreen.muser.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<User> getUsers() {
        log.debug("Get all users");
        Iterable<User> users = userRepository.findAll();
        if (users.iterator().next() == null) {
            throw new UserNotFoundException("No user found in database");
        }
        return users;
    }
}
