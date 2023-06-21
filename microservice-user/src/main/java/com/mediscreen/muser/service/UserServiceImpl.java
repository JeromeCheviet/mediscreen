package com.mediscreen.muser.service;

import com.mediscreen.muser.exception.UserNotCreatedException;
import com.mediscreen.muser.exception.UserNotFoundException;
import com.mediscreen.muser.model.User;
import com.mediscreen.muser.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<User> getUsers() {
        logger.debug("Get all users");
        Iterable<User> users = userRepository.findAll();
        if (!users.iterator().hasNext()) {
            throw new UserNotFoundException("No users found in database");
        }
        return users;
    }

    @Override
    public Optional<User> getUserById(int userId) {
        logger.debug("Get user with id {}", userId);
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id " + userId + " not found");
        }

        return user;
    }

    @Override
    public void addUser(User user) {
        logger.debug("Create user with firstname {}", user.getFirstName());
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserNotCreatedException("User with firstname not created. Reason : " + e);
        }
    }
}
