package com.mediscreen.mpatient.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Patient not deleted")
public class PatientNotDeletedExcecption extends RuntimeException {
    Logger logger = LoggerFactory.getLogger(PatientNotDeletedExcecption.class);
    public PatientNotDeletedExcecption(String message) {
        super(message);
        logger.warn(message);
    }
}
