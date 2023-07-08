package com.mediscreen.mpatient.controller;

import com.mediscreen.mpatient.model.Patient;
import com.mediscreen.mpatient.service.PatientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {
    private Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    @GetMapping(value = "/patient")
    public ResponseEntity<Iterable<Patient>> getAllPatients() {
        logger.debug("get /patient");

        return new ResponseEntity<>(patientService.getPatients(), HttpStatus.OK);
    }

    @GetMapping(value = "/patient/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable("id") Integer id) {
        logger.debug("get /patient/{}", id);

        return new ResponseEntity<>(patientService.getPatientById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/patient/add")
    public ResponseEntity<Patient> addPatient(@RequestBody @Valid Patient newPatient) {
        logger.debug("Post /patient/add");
        patientService.addPatient(newPatient);

        return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
    }

    @PutMapping(value = "/patient/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") Integer id, @RequestBody @Valid Patient updatedPatient) {
        logger.debug("Put /patient/{}", id);
        patientService.getPatientById(id);
        updatedPatient.setPatientId(id);

        return new ResponseEntity<>(patientService.updatePatient(updatedPatient), HttpStatus.OK);
    }

    @DeleteMapping(value = "/patient/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable("id") Integer id) {
        logger.debug("Delete /patient/{}", id);
        patientService.deletePatient(patientService.getPatientById(id));

        return new ResponseEntity<>("Patient " + id + " deleted", HttpStatus.NO_CONTENT);
    }

}
