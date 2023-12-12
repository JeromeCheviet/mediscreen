package com.mediscreen.mpatient.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class which create and manage a PatientNotFoundException
 * If this exception is throw, an HTTP status 404 and the reason is sending to the controller
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Patient not found")
public class PatientNotFoundException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(PatientNotFoundException.class);

    /**
     * Method use to construct an exception when patient is not found
     *
     * @param message String whose indicate the id of not found patient
     */
    public PatientNotFoundException(String message) {
        super(message);
        logger.warn(message);
    }
}
