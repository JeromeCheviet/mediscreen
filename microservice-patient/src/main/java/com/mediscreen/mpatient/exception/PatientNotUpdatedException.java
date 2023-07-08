package com.mediscreen.mpatient.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Patient not updated")
public class PatientNotUpdatedException extends RuntimeException {
    Logger logger = LoggerFactory.getLogger(PatientNotUpdatedException.class);
    public PatientNotUpdatedException(String message) {
        super(message);
        logger.error(message);
    }
}
