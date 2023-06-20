package com.mediscreen.muser.service;

import com.mediscreen.muser.exception.UserNotFoundException;
import com.mediscreen.muser.model.User;
import com.mediscreen.muser.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<User> getUsers() {
        log.debug("Get all users");
        Iterable<User> users = userRepository.findAll();
        if (!users.iterator().hasNext()) {
            throw new UserNotFoundException("No users found in database");
        }
        return users;
    }

    @Override
    public Optional<User> getUserById(int userId) {
        log.debug("Get user with id {}", userId);
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id " + userId + " not found");
        }

        return user;
    }
}
