package com.mediscreen.mnotes.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class which create and manage an NoteNotCreatedException
 * If this exception is throw, an HTTP status 400 and the reason is sending to the controller
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Patient Not Created")
public class NoteNotCreatedException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(NoteNotCreatedException.class);

    /**
     * Method use to construct an exception when note is not created
     *
     * @param message String whose indicate the reason why the note has not been created
     */
    public NoteNotCreatedException(String message) {
        super(message);
        logger.error(message);
    }
}
