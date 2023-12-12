package com.mediscreen.mpatient.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class which create and manage a PatientNotUpdatedException
 * If this exception is throw, an HTTP status 204 and the reason is sending to the controller
 */
@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Patient not updated")
public class PatientNotUpdatedException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(PatientNotUpdatedException.class);

    /**
     * Method use to construct an exception when patient is not updated
     *
     * @param message String whose indicate the id of not updated patient
     */
    public PatientNotUpdatedException(String message) {
        super(message);
        logger.error(message);
    }
}
