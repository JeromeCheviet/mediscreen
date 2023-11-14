package com.mediscreen.massessment.service;

import com.mediscreen.massessment.bean.NoteBean;
import com.mediscreen.massessment.bean.PatientBean;
import com.mediscreen.massessment.model.Assessment;

import java.util.List;

public interface AssessmentService {
    Assessment getPatientAssessment(PatientBean patientBean, List<NoteBean> noteBeans);

}
