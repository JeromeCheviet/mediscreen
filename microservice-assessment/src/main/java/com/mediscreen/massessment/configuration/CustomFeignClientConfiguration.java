package com.mediscreen.massessment.configuration;

import com.mediscreen.massessment.exception.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

/**
 * Custom configuration for Feign to manage error from external API
 */
public class CustomFeignClientConfiguration {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}
