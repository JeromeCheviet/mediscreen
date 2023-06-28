package com.mediscreen.mpatient.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Patient not found")
public class PatientNotFoundException extends RuntimeException {
    private Logger logger = LoggerFactory.getLogger(PatientNotCreatedException.class);

    public PatientNotFoundException(String message) {
        super(message);
        logger.warn(message);
    }
}
