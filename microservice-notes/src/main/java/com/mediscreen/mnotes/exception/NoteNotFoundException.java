package com.mediscreen.mnotes.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class which create and manage a NoteNotFoundException
 * If this exception is throw, an HTTP status 404 and the reason is sending to the controller
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Note not found")
public class NoteNotFoundException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(NoteNotFoundException.class);

    /**
     * Method use to construct an exception when note is not found
     *
     * @param message String whose indicate the id of not found note
     */
    public NoteNotFoundException(String message) {
        super(message);
        logger.warn(message);
    }
}
