package com.mediscreen.mpatient.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Patient Not Created")
public class PatientNotCreatedException extends RuntimeException {
    private Logger logger = LoggerFactory.getLogger(PatientNotCreatedException.class);

    public PatientNotCreatedException(String message) {
        super(message);
        logger.error(message);
    }
}
