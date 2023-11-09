package com.mediscreen.massessment.controller;

import com.mediscreen.massessment.bean.NoteBean;
import com.mediscreen.massessment.bean.PatientBean;
import com.mediscreen.massessment.proxy.MNotesProxy;
import com.mediscreen.massessment.proxy.MPatientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class AssessmentController {
    @Autowired
    private MPatientProxy patientProxy;
    @Autowired
    private MNotesProxy notesProxy;

    @GetMapping(value = "/assess/{id}")
    public ResponseEntity<String> getAssessment(@PathVariable("id") Integer id){
        PatientBean patient = patientProxy.getPatient(id);
        List<NoteBean> noteBean = notesProxy.getPatHistory(patient.getPatientId());
        return  new ResponseEntity<>(noteBean.toString(), HttpStatus.OK);
    }
}
