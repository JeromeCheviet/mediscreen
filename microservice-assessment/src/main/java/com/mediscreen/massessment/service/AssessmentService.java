package com.mediscreen.massessment.service;

import com.mediscreen.massessment.bean.NoteBean;
import com.mediscreen.massessment.bean.PatientBean;
import com.mediscreen.massessment.model.Assessment;

import java.util.List;

/**
 * Interface link to assessment operations
 */
public interface AssessmentService {
    /**
     * Get the patient assessment
     *
     * @param patientBean Object contain the patient information
     * @param noteBeans   Object contain the patient notes
     * @return an object with the patient fullname, his age and his risk
     */
    Assessment getPatientAssessment(PatientBean patientBean, List<NoteBean> noteBeans);

}
