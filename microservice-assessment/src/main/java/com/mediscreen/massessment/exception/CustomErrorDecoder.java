package com.mediscreen.massessment.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

/**
 * Class to parse HTTP status codes from external API and customize it.
 */
public class CustomErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder.Default defaultDecoder = new Default();
    private Logger logger = LoggerFactory.getLogger(CustomErrorDecoder.class);

    /**
     * {@inheritDoc}
     *
     * The method find the response status and if is a not found status, a custom exception is throwing.
     */
    @Override
    public Exception decode(String methodKey, Response response) {
        String requestUrl = response.request().url();
        logger.warn(requestUrl);
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());
        if (HttpStatus.NOT_FOUND.equals(responseStatus)) {
            return new DataNotFoundException("No data found");
        }
        return defaultDecoder.decode(methodKey, response);
    }
}
