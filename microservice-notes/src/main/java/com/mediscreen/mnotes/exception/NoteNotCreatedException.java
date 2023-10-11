package com.mediscreen.mnotes.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Patient Not Created")
public class NoteNotCreatedException extends RuntimeException {
    private Logger logger = LoggerFactory.getLogger(NoteNotCreatedException.class);

    public NoteNotCreatedException(String message) {
        super(message);
        logger.error(message);
    }
}
