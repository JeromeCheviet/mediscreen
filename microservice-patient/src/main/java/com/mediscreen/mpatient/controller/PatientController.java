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

/**
 * Class which manage REST API Controller for patient.
 */
@CrossOrigin
@RestController
public class PatientController {
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    /**
     * Method to get all patient present in database
     *
     * @return a list of all patient and the HTTP status 200
     */
    @GetMapping(value = "/patient")
    public ResponseEntity<Iterable<Patient>> getAllPatients() {
        logger.debug("get /patient");

        return new ResponseEntity<>(patientService.getPatients(), HttpStatus.OK);
    }

    /**
     * Method to get all information for a specific patient
     *
     * @param id Interger - The patient ID
     * @return all information about the patient and the HTTP status 200
     */
    @GetMapping(value = "/patient/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable("id") Integer id) {
        logger.debug("get /patient/{}", id);

        return new ResponseEntity<>(patientService.getPatientById(id), HttpStatus.OK);
    }

    /**
     * Method to create a new patient.
     *
     * @param newPatient the object model Patient in json format
     * @return all information about the new patient and HTTP status 201
     */
    @PostMapping(value = "/patient/add")
    public ResponseEntity<Patient> addPatient(@RequestBody @Valid Patient newPatient) {
        logger.debug("Post /patient/add");
        patientService.addPatient(newPatient);

        return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
    }

    /**
     * Method to update an existing patient
     *
     * @param id             Integer - Patient ID to update
     * @param updatedPatient the object model Patient in json format
     * @return all information including updated one and HTTP status 200
     */
    @PutMapping(value = "/patient/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") Integer id, @RequestBody @Valid Patient updatedPatient) {
        logger.debug("Put /patient/{}", id);
        patientService.getPatientById(id);
        updatedPatient.setPatientId(id);

        return new ResponseEntity<>(patientService.updatePatient(updatedPatient), HttpStatus.OK);
    }

    /**
     * Method to delete a patient
     *
     * @param id Integer - Patient ID to delete
     * @return a string whose inform the id of deleted patient and HTTP status 204
     */
    @DeleteMapping(value = "/patient/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable("id") Integer id) {
        logger.debug("Delete /patient/{}", id);
        patientService.deletePatient(patientService.getPatientById(id));

        return new ResponseEntity<>("Patient " + id + " deleted", HttpStatus.NO_CONTENT);
    }

}
