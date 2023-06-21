package com.mediscreen.muser.service;

import com.mediscreen.muser.model.User;

import java.util.Optional;

public interface UserService {
    Iterable<User> getUsers();

    Optional<User> getUserById(int userId);

    void addUser(User user);
}
