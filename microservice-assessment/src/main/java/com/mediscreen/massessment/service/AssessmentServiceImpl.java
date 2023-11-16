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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        logger.info("Number of symptoms find : {}", nbSymptoms);

        Risk risk = defineRisk(patientAge, patientBean.getSex(), nbSymptoms);
        logger.info("Risk is {}", risk);

        return new Assessment(patientFullName, patientAge, risk);
    }

    private int getAge(LocalDate dob) {
        logger.info("Get age when date of birth is {}", dob);
        return Period.between(dob, currentDate).getYears();
    }

    private int countTriggerWord(List<NoteBean> noteBeans) {
        List<String> notes = new ArrayList<>();
        noteBeans.forEach(noteBean -> notes.add(noteBean.getNotes()));

        AtomicInteger nbSymptome = new AtomicInteger(0);
        symptoms.forEach(symptom -> {
            for (String note : notes) {
                if (note.toLowerCase().contains(symptom.toLowerCase())) {
                    nbSymptome.getAndIncrement();
                }
            }
        });

        return nbSymptome.get();
    }

    private Risk defineRisk(int patientAge, String patientSex, int nbSymptoms) {
        logger.info("Definition of risk for a {} year old patient ({}) with {} symptom(s)", patientAge, patientSex, nbSymptoms);
        if (nbSymptoms == 0) {
            return Risk.NONE;
        }
        if (patientAge >= 30) {
            return getRiskOlderPatients(nbSymptoms);
        } else {
            return getRiskYoungerPatients(patientSex, nbSymptoms);
        }
    }

    private Risk getRiskOlderPatients(int nbSymptoms) {
        if (nbSymptoms >= 2 && nbSymptoms < 6) {
            return Risk.BORDERLINE;
        }
        if (nbSymptoms >= 6 && nbSymptoms < 8) {
            return Risk.DANGER;
        }
        if (nbSymptoms >= 8) {
            return Risk.EARLY_ONSET;
        }

        return Risk.NONE;
    }

    private Risk getRiskYoungerPatients(String patientSex, int nbSymptoms) {
        switch (patientSex) {
            case "M":
                if (nbSymptoms >= 3 && nbSymptoms < 5) {
                    return Risk.DANGER;
                }
                if (nbSymptoms >= 5) {
                    return Risk.EARLY_ONSET;
                }
                break;
            case "F":
                if (nbSymptoms >= 4 && nbSymptoms < 7) {
                    return Risk.DANGER;
                }
                if (nbSymptoms >= 7) {
                    return Risk.EARLY_ONSET;
                }
                break;
            default:
                return Risk.NONE;
        }

        return Risk.NONE;
    }
}

