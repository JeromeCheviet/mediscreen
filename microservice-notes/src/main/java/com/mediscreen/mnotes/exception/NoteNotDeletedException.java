package com.mediscreen.mnotes.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class which create and manage a NoteNotDeletedException
 * If this exception is throw, an HTTP status 400 and the reason is sending to the controller
 */
@ResponseStatus(value = HttpStatus.BAD_GATEWAY, reason = "Note not deleted")
public class NoteNotDeletedException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(NoteNotDeletedException.class);

    /**
     * Method use to construct an exception when note is not deleted
     *
     * @param message String whose indicate the id of not deleted note
     */
    public NoteNotDeletedException(String message) {
        super(message);
        logger.warn(message);
    }
}
