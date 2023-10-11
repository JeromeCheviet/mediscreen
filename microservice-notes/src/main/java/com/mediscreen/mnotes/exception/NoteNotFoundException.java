package com.mediscreen.mnotes.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Note not found")
public class NoteNotFoundException extends RuntimeException {
    private Logger logger = LoggerFactory.getLogger(NoteNotFoundException.class);

    public NoteNotFoundException(String message) {
        super(message);
        logger.warn(message);
    }
}
