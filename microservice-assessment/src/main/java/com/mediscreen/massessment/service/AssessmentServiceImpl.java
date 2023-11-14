package com.mediscreen.massessment.service;

import com.mediscreen.massessment.bean.NoteBean;
import com.mediscreen.massessment.bean.PatientBean;
import com.mediscreen.massessment.model.Assessment;
import com.mediscreen.massessment.model.Risk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AssessmentServiceImpl implements AssessmentService {
    private static final Logger logger = LoggerFactory.getLogger(AssessmentServiceImpl.class);
    private static final LocalDate currentDate = LocalDate.now();
    private static final List<String> symptoms = Arrays.asList(
            "Hémoglobine A1C",
            "Microalbumine",
            "Taille",
            "Poids",
            "Fumeur",
            "Anormal",
            "Cholestérol",
            "Vertige",
            "Rechute",
            "Réaction",
            "Anticorps"
    );

    @Override
    public Assessment getPatientAssessment(PatientBean patientBean, List<NoteBean> noteBeans) {
        logger.info("Get assessment for patient {} {}", patientBean.getFamily(), patientBean.getGiven());

        String patientFullName = patientBean.getFamily() + " " + patientBean.getGiven();
        int patientAge = getAge(patientBean.getDob());
        int nbSymptoms = countTriggerWord(noteBeans);

        Risk risk = Risk.NONE;

        return new Assessment(patientFullName, patientAge, risk);
    }

    private int getAge(LocalDate dob) {
        logger.info("Get age when date of birth is {}", dob);
        return Period.between(dob, currentDate).getYears();
    }

    private int countTriggerWord(List<NoteBean> noteBeans) {
        Map<String, String> notes = new HashMap<>();
        int nbSymptome = 0;
        noteBeans.forEach((noteBean -> notes.put(noteBean.getId(), noteBean.getNotes())));
        /*symptoms.stream().forEach(symptom -> {
            logger.info(symptom);
            if (notes.containsValue(symptom)) {
                logger.info(symptom);
            }
        });*/
        
        if (notes.values().contains("fumeur")) {
            logger.info(notes.values().toString());
        }



                /*.forEach(symptom -> {
            if (notes.containsValue(symptom)) {
                nbSymptome ++;
            }
        });*/
        logger.info(String.valueOf(nbSymptome));
        return 20;
    }

}
