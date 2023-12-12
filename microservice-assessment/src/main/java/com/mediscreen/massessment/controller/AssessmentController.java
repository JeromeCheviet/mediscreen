package com.mediscreen.massessment.controller;

import com.mediscreen.massessment.bean.NoteBean;
import com.mediscreen.massessment.bean.PatientBean;
import com.mediscreen.massessment.model.Assessment;
import com.mediscreen.massessment.proxy.MNotesProxy;
import com.mediscreen.massessment.proxy.MPatientProxy;
import com.mediscreen.massessment.service.AssessmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Class which manage REST API Controller for assessment of patient
 */
@CrossOrigin
@RestController
public class AssessmentController {
    private static final Logger logger = LoggerFactory.getLogger(AssessmentController.class);

    @Autowired
    private MPatientProxy patientProxy;
    @Autowired
    private MNotesProxy notesProxy;
    @Autowired
    private AssessmentService assessmentService;

    /**
     * Method to get the assessment from a specific patient.
     * Patient information and all notes are retrieved and transferred to the services class.
     *
     * @param id Integer - The patient ID
     * @return the object with the assessment and the HTTP status 200
     */
    @GetMapping(value = "/assess/{id}")
    public ResponseEntity<Assessment> getAssessment(@PathVariable("id") Integer id) {
        logger.debug("Get /assess/{}", id);
        PatientBean patient = patientProxy.getPatient(id);
        List<NoteBean> noteBean = notesProxy.getPatHistory(patient.getPatientId());
        return new ResponseEntity<>(assessmentService.getPatientAssessment(patient, noteBean), HttpStatus.OK);
    }
}
