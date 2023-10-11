package com.mediscreen.mnotes.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Note not updated")
public class NoteNotUpdatedException extends RuntimeException {
    Logger logger = LoggerFactory.getLogger(NoteNotUpdatedException.class);

    public NoteNotUpdatedException(String message) {
        super(message);
        logger.error(message);
    }
}
