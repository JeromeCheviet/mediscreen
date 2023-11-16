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

    }

    @Test
    void testGetPatientAssessment_returnAssessment() {
        Assessment expectedAssessment = new Assessment("Jane Doe", 20, Risk.NONE);

        expectedNoteBean.setNotes("Le patient est fumeur");
        expectedSecondNoteBean.setNotes("Le patient a du Cholestérol");

        List<NoteBean> expectedNoteBeanList = new ArrayList<>();

        expectedNoteBeanList.add(expectedNoteBean);
        expectedNoteBeanList.add(expectedSecondNoteBean);

        Assessment actualAssessment = assessmentService.getPatientAssessment(expectedPatientBean, expectedNoteBeanList);

        assertEquals(expectedAssessment.toString(), actualAssessment.toString());
    }

    @Test
    void testGetPatientAssessment_returnRiskNone_whenNoSymptomForYoungerMan() {
        Risk expectedRisk = Risk.NONE;

        expectedPatientBean.setSex("M");
        expectedNoteBean.setNotes("Tout va bien");

        List<NoteBean> expectedNoteBeanList = new ArrayList<>();
        expectedNoteBeanList.add(expectedNoteBean);

        Assessment assessment = assessmentService.getPatientAssessment(expectedPatientBean, expectedNoteBeanList);

        assertEquals(expectedRisk, assessment.getRisk());
    }

    @Test
    void testGetPatientAssessment_returnRiskNone_whenNoSymptomForYoungerWoman() {
        Risk expectedRisk = Risk.NONE;

        expectedPatientBean.setSex("F");
        expectedNoteBean.setNotes("Tout va bien");

        List<NoteBean> expectedNoteBeanList = new ArrayList<>();
        expectedNoteBeanList.add(expectedNoteBean);

        Assessment assessment = assessmentService.getPatientAssessment(expectedPatientBean, expectedNoteBeanList);

        assertEquals(expectedRisk, assessment.getRisk());
    }

    @Test
    void testGetPatientAssessment_returnRiskNone_whenNoSymptomForOlderMan() {
        Risk expectedRisk = Risk.NONE;

        expectedPatientBean.setSex("M");
        expectedPatientBean.setDob(LocalDate.now().minusYears(40));
        expectedNoteBean.setNotes("Tout va bien");

        List<NoteBean> expectedNoteBeanList = new ArrayList<>();
        expectedNoteBeanList.add(expectedNoteBean);

        Assessment assessment = assessmentService.getPatientAssessment(expectedPatientBean, expectedNoteBeanList);

        assertEquals(expectedRisk, assessment.getRisk());
    }

    @Test
    void testGetPatientAssessment_returnRiskNone_whenNoSymptomForOlderWoman() {
        Risk expectedRisk = Risk.NONE;

        expectedPatientBean.setSex("F");
        expectedPatientBean.setDob(LocalDate.now().minusYears(40));
        expectedNoteBean.setNotes("Tout va bien");

        List<NoteBean> expectedNoteBeanList = new ArrayList<>();
        expectedNoteBeanList.add(expectedNoteBean);

        Assessment assessment = assessmentService.getPatientAssessment(expectedPatientBean, expectedNoteBeanList);

        assertEquals(expectedRisk, assessment.getRisk());
    }

    @Test
    void testGetPatientAssessment_returnBorderline_whenTwoSymptomsForOlderMan() {
        Risk expectedRisk = Risk.BORDERLINE;

        expectedPatientBean.setSex("M");
        expectedPatientBean.setDob(LocalDate.now().minusYears(40));
        expectedNoteBean.setNotes("Le taux de Hémoglobine A1C est élevé. Le patient est fumeur");

        List<NoteBean> expectedNoteBeanList = new ArrayList<>();
        expectedNoteBeanList.add(expectedNoteBean);

        Assessment assessment = assessmentService.getPatientAssessment(expectedPatientBean, expectedNoteBeanList);

        assertEquals(expectedRisk, assessment.getRisk());
    }

    @Test
    void testGetPatientAssessment_returnBorderline_whenTwoSymptomsForOlderWoman() {
        Risk expectedRisk = Risk.BORDERLINE;

        expectedPatientBean.setSex("F");
        expectedPatientBean.setDob(LocalDate.now().minusYears(40));
        expectedNoteBean.setNotes("Le taux de Hémoglobine A1C est élevé. Le patient est fumeur");

        List<NoteBean> expectedNoteBeanList = new ArrayList<>();
        expectedNoteBeanList.add(expectedNoteBean);

        Assessment assessment = assessmentService.getPatientAssessment(expectedPatientBean, expectedNoteBeanList);

        assertEquals(expectedRisk, assessment.getRisk());
    }

    @Test
    void testGetPatientAssessment_returnInDanger_whenSixSymptomsForOlderMan() {
        Risk expectedRisk = Risk.DANGER;

        expectedPatientBean.setSex("M");
        expectedPatientBean.setDob(LocalDate.now().minusYears(40));
        expectedNoteBean.setNotes("Le taux de Hémoglobine A1C est élevé. Le patient est fumeur et son poids est limite");
        expectedSecondNoteBean.setNotes("Microalbumine en augmentation. Il des sensations de vertige. Taille normal");

        List<NoteBean> expectedNoteBeanList = new ArrayList<>();
        expectedNoteBeanList.add(expectedNoteBean);
        expectedNoteBeanList.add(expectedSecondNoteBean);

        Assessment assessment = assessmentService.getPatientAssessment(expectedPatientBean, expectedNoteBeanList);

        assertEquals(expectedRisk, assessment.getRisk());
    }

    @Test
    void testGetPatientAssessment_returnInDanger_whenThreeSymptomsForYoungerMan() {
        Risk expectedRisk = Risk.DANGER;

        expectedPatientBean.setSex("M");
        expectedNoteBean.setNotes("Le taux de Hémoglobine A1C est élevé. Le patient est fumeur et son poids est limite");

        List<NoteBean> expectedNoteBeanList = new ArrayList<>();
        expectedNoteBeanList.add(expectedNoteBean);

        Assessment assessment = assessmentService.getPatientAssessment(expectedPatientBean, expectedNoteBeanList);

        assertEquals(expectedRisk, assessment.getRisk());
    }

    @Test
    void testGetPatientAssessment_returnInDanger_whenFourSymptomsForYoungerWoman() {
        Risk expectedRisk = Risk.DANGER;

        expectedPatientBean.setSex("F");
        expectedNoteBean.setNotes("Le taux de Hémoglobine A1C est élevé. Le patient est fumeur et son poids est limite");
        expectedSecondNoteBean.setNotes("Microalbumine en augmentation.");

        List<NoteBean> expectedNoteBeanList = new ArrayList<>();
        expectedNoteBeanList.add(expectedNoteBean);
        expectedNoteBeanList.add(expectedSecondNoteBean);

        Assessment assessment = assessmentService.getPatientAssessment(expectedPatientBean, expectedNoteBeanList);

        assertEquals(expectedRisk, assessment.getRisk());
    }

    @Test
    void testGetPatientAssessment_returnEarlyOnset_whenNineSymptomsForOlderMan() {
        Risk expectedRisk = Risk.EARLY_ONSET;

        expectedPatientBean.setSex("M");
        expectedPatientBean.setDob(LocalDate.now().minusYears(40));
        expectedNoteBean.setNotes("Le taux de Hémoglobine A1C est élevé. Le patient est fumeur et son poids est limite. Cholestérol à surveiller.");
        expectedSecondNoteBean.setNotes("Microalbumine en augmentation. Il des sensations de vertige. Taille normal. ANTICORPS faible, risque de rechute");

        List<NoteBean> expectedNoteBeanList = new ArrayList<>();
        expectedNoteBeanList.add(expectedNoteBean);
        expectedNoteBeanList.add(expectedSecondNoteBean);

        Assessment assessment = assessmentService.getPatientAssessment(expectedPatientBean, expectedNoteBeanList);

        assertEquals(expectedRisk, assessment.getRisk());
    }

    @Test
    void testGetPatientAssessment_returnEarlyOnset_whenEightSymptomsForOlderWoman() {
        Risk expectedRisk = Risk.EARLY_ONSET;

        expectedPatientBean.setSex("F");
        expectedPatientBean.setDob(LocalDate.now().minusYears(40));
        expectedNoteBean.setNotes("Le taux de Hémoglobine A1C est élevé. Le patient est fumeur et son poids est limite. Cholestérol à surveiller.");
        expectedSecondNoteBean.setNotes("Microalbumine en augmentation. Il des sensations de vertige. Taille normal. ANTICORPS faible.");

        List<NoteBean> expectedNoteBeanList = new ArrayList<>();
        expectedNoteBeanList.add(expectedNoteBean);
        expectedNoteBeanList.add(expectedSecondNoteBean);

        Assessment assessment = assessmentService.getPatientAssessment(expectedPatientBean, expectedNoteBeanList);

        assertEquals(expectedRisk, assessment.getRisk());
    }

    @Test
    void testGetPatientAssessment_returnEarlyOnset_whenSevenSymptomsForYoungerWoman() {
        Risk expectedRisk = Risk.EARLY_ONSET;

        expectedPatientBean.setSex("F");
        expectedPatientBean.setDob(LocalDate.now().minusYears(20));
        expectedNoteBean.setNotes("Le taux de Hémoglobine A1C est élevé. Le patient est fumeur et son poids est limite. Cholestérol à surveiller.");
        expectedSecondNoteBean.setNotes("Microalbumine en augmentation. Il des sensations de vertige. Taille normal.");

        List<NoteBean> expectedNoteBeanList = new ArrayList<>();
        expectedNoteBeanList.add(expectedNoteBean);
        expectedNoteBeanList.add(expectedSecondNoteBean);

        Assessment assessment = assessmentService.getPatientAssessment(expectedPatientBean, expectedNoteBeanList);

        assertEquals(expectedRisk, assessment.getRisk());
    }

    @Test
    void testGetPatientAssessment_returnEarlyOnset_whenFiveSymptomsForYoungerMan() {
        Risk expectedRisk = Risk.EARLY_ONSET;

        expectedPatientBean.setSex("M");
        expectedPatientBean.setDob(LocalDate.now().minusYears(20));
        expectedNoteBean.setNotes("Le taux de Hémoglobine A1C est élevé. Le patient est fumeur.");
        expectedSecondNoteBean.setNotes("Microalbumine en augmentation. Il des sensations de vertige. Taille normal.");

        List<NoteBean> expectedNoteBeanList = new ArrayList<>();
        expectedNoteBeanList.add(expectedNoteBean);
        expectedNoteBeanList.add(expectedSecondNoteBean);

        Assessment assessment = assessmentService.getPatientAssessment(expectedPatientBean, expectedNoteBeanList);

        assertEquals(expectedRisk, assessment.getRisk());
    }
}
