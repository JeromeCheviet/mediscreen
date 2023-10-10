package com.mediscreen.mnotes.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY, reason = "Note not deleted")
public class NoteNotDeletedException extends RuntimeException {
    Logger logger = LoggerFactory.getLogger(NoteNotDeletedException.class);

    public NoteNotDeletedException(String message) {
        super(message);
        logger.warn(message);
    }
}
