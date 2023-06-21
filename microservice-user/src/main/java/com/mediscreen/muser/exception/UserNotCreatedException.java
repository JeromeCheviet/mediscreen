package com.mediscreen.muser.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User not created")
public class UserNotCreatedException extends RuntimeException {
    Logger logger = LoggerFactory.getLogger(UserNotCreatedException.class);

    public UserNotCreatedException(String message) {
        super(message);
        logger.error(message);
    }
}
