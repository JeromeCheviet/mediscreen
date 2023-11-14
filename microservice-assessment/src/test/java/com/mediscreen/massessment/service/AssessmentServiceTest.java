package com.mediscreen.massessment.service;

import com.mediscreen.massessment.bean.NoteBean;
import com.mediscreen.massessment.bean.PatientBean;
import com.mediscreen.massessment.model.Assessment;
import com.mediscreen.massessment.model.Risk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AssessmentServiceTest {
    @InjectMocks
    private AssessmentService assessmentService = new AssessmentServiceImpl();

    private NoteBean expectedNoteBean = new NoteBean();
    private NoteBean expectedSecondNoteBean = new NoteBean();

    private PatientBean expectedPatientBean = new PatientBean();

    @BeforeEach
    private void setUp() {
        expectedPatientBean.setPatientId(1);
        expectedPatientBean.setFamily("Jane");
        expectedPatientBean.setGiven("Doe");
        expectedPatientBean.setDob(LocalDate.now().minusYears(20));
        expectedPatientBean.setSex("F");
        expectedPatientBean.setAddress("1 rue du Puit");
        expectedPatientBean.setPhone("000-222-4444");

        expectedNoteBean.setId("1111aaaa");
        expectedNoteBean.setPatId(1);
        expectedNoteBean.setNotes("Le patient est fumeur");

        expectedSecondNoteBean.setId("1111bbbb");
        expectedSecondNoteBean.setPatId(1);
        expectedSecondNoteBean.setNotes("Le patient a du Cholestérol");
    }

    @Test
    void testGetPatientAssessment_returnAssessment() {
        Assessment expectedAssessment = new Assessment("Jane Doe", 20, Risk.NONE);
        List<NoteBean> expectedNoteBeanList = new ArrayList<>();
        expectedNoteBeanList.add(expectedNoteBean);
        expectedNoteBeanList.add(expectedSecondNoteBean);
        Assessment actualAssessment = assessmentService.getPatientAssessment(expectedPatientBean, expectedNoteBeanList);

        assertEquals(expectedAssessment.toString(), actualAssessment.toString());
    }

}
