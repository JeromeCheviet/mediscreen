package com.mediscreen.mpatient.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class which create and manage a PatientNotDeletedException
 * If this exception is throw, an HTTP status 400 and the reason is sending to the controller
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Patient not deleted")
public class PatientNotDeletedExcecption extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(PatientNotDeletedExcecption.class);

    /**
     * Method use to construct an exception when patient is not deleted
     *
     * @param message String whose indicate the id of not deleted patient
     */
    public PatientNotDeletedExcecption(String message) {
        super(message);
        logger.warn(message);
    }
}
