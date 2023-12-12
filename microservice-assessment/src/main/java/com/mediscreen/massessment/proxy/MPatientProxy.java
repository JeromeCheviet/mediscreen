package com.mediscreen.massessment.proxy;

import com.mediscreen.massessment.bean.PatientBean;
import com.mediscreen.massessment.configuration.CustomFeignClientConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Interface used to interact with microservice-patient
 */
@FeignClient(name = "microservice-patient", configuration = CustomFeignClientConfiguration.class)
@RibbonClient(name = "microservice-patient")
public interface MPatientProxy {

    /**
     * Get the patient information
     *
     * @param id The patient ID
     * @return object with patient information
     */
    @GetMapping(value = "/patient/{id}")
    PatientBean getPatient(@PathVariable("id") int id);
}
