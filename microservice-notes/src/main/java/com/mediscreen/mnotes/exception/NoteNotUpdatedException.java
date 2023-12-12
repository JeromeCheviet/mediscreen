package com.mediscreen.mnotes.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class which create and manage a NoteNotUpdatedException
 * If this exception is throw, an HTTP status 204 and the reason is sending to the controller
 */
@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Note not updated")
public class NoteNotUpdatedException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(NoteNotUpdatedException.class);

    /**
     * Method use to construct an exception when note is not updated
     *
     * @param message String whose indicate the id of not updated note
     */
    public NoteNotUpdatedException(String message) {
        super(message);
        logger.error(message);
    }
}
