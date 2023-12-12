package com.mediscreen.massessment.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class which create and manage a DataNotFoundException
 * If this exception is throw, an HTTP status 404 and the reason is sending to the controller
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(DataNotFoundException.class);

    /**
     * Method use to construct an exception when no data found from external API
     *
     * @param message String whose indicate the error from external API.
     */
    public DataNotFoundException(String message) {
        super(message);
        logger.warn(message);
    }
}
