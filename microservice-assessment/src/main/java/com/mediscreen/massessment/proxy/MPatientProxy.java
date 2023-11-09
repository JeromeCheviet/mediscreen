package com.mediscreen.massessment.proxy;

import com.mediscreen.massessment.bean.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-patient", url = "localhost:8081")
public interface MPatientProxy {
    @GetMapping(value = "/patient/{id}")
    PatientBean getPatient(@PathVariable("id") int id);
}
