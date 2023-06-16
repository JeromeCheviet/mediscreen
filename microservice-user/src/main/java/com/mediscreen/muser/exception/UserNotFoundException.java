package com.mediscreen.muser.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found")
public class UserNotFoundException extends RuntimeException {
    Logger logger = LoggerFactory.getLogger(UserNotFoundException.class);
    public UserNotFoundException(String message){
        super(message);
        logger.warn(message);
    }
}
