package com.mediscreen.mpatient.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class which create and manage an PatientNotCreatedException
 * If this exception is throw, an HTTP status 400 and the reason is sending to the controller
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Patient Not Created")
public class PatientNotCreatedException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(PatientNotCreatedException.class);

    /**
     * Method use to construct an exception when patient is not created
     *
     * @param message String whose indicate the reason why the patient has not been created
     */
    public PatientNotCreatedException(String message) {
        super(message);
        logger.error(message);
    }
}
