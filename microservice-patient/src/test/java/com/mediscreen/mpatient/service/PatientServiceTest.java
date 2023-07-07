package com.mediscreen.mpatient.service;

import com.mediscreen.mpatient.exception.PatientNotCreatedException;
import com.mediscreen.mpatient.exception.PatientNotDeletedExcecption;
import com.mediscreen.mpatient.exception.PatientNotFoundException;
import com.mediscreen.mpatient.model.Patient;
import com.mediscreen.mpatient.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.EMPTY_LIST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {
    @InjectMocks
    private PatientService patientService = new PatientServiceImpl();
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private Patient expectedPatient;

    @BeforeEach
    private void setUp() {
        int expectedPatientId = 1;
        String expectedFamily = "John";
        String expectedGiven = "Doe";
        LocalDate expectedDob = LocalDate.parse("1990-12-25");
        String expectedSex = "M";
        String expectedAddress = "1 rue du Puit";
        String expectedPhone = "000-111-3333";

        expectedPatient.setPatientId(expectedPatientId);
        expectedPatient.setFamily(expectedFamily);
        expectedPatient.setGiven(expectedGiven);
        expectedPatient.setDob(expectedDob);
        expectedPatient.setSex(expectedSex);
        expectedPatient.setAddress(expectedAddress);
        expectedPatient.setPhone(expectedPhone);
    }

    @Test
    void testGetPatients() {
        Patient expectedPatient2 = new Patient(2,
                "Jane'",
                "Doe",
                LocalDate.parse("1980-03-07"),
                "F",
                "1 rue du Puit",
                "000-222-4444");

        List<Patient> expectedPatients = new ArrayList<>();
        expectedPatients.add(expectedPatient);
        expectedPatients.add(expectedPatient2);

        when(patientRepository.findAll()).thenReturn(expectedPatients);

        Iterable<Patient> actualPatients = patientService.getPatients();

        assertEquals(expectedPatients, actualPatients);
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void testGetPatients_throwException_whenNoPatients() {
        when(patientRepository.findAll()).thenReturn(EMPTY_LIST);

        Throwable exception = assertThrows(PatientNotFoundException.class, () -> patientService.getPatients());

        assertEquals("No patients found in database", exception.getMessage());
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void testGetPatientById() {
        when(patientRepository.findById(1)).thenReturn(Optional.of(expectedPatient));

        Patient actualPatient = patientService.getPatientById(1);

        assertEquals(expectedPatient.toString(), actualPatient.toString());
    }

    @Test
    void testGetPatientById_throwException_whenNoPatientFound() {
        when(patientRepository.findById(1)).thenReturn(Optional.empty());

        Throwable exception = assertThrows(PatientNotFoundException.class, () -> patientService.getPatientById(1));

        assertEquals("Patient with id 1 not found", exception.getMessage());
        verify(patientRepository, times(1)).findById(1);
    }

    @Test
    void testAddPatient() {
        when(patientRepository.save(any(Patient.class))).thenReturn(expectedPatient);

        patientService.addPatient(expectedPatient);

        verify(patientRepository, times(1)).save(expectedPatient);
    }

    @Test
    void testAddPatient_throwException_whenPatientNotAdded() {
        when(patientRepository.save(any(Patient.class))).thenThrow(RuntimeException.class);

        Throwable exception = assertThrows(PatientNotCreatedException.class, () -> patientService.addPatient(expectedPatient));

        assertEquals("Patient not created. Reason : java.lang.RuntimeException", exception.getMessage());
        verify(patientRepository, times(1)).save(expectedPatient);
    }

    @Test
    void testDeletePatient() {
        doNothing().when(patientRepository).delete(expectedPatient);
        when(patientRepository.findById(expectedPatient.getPatientId())).thenReturn(Optional.empty());

        patientService.deletePatient(expectedPatient);

        verify(patientRepository, times(1)).delete(expectedPatient);
        verify(patientRepository, times(1)).findById(expectedPatient.getPatientId());
    }

    @Test
    void testDeletePatient_throwException_whenPatientNotFound() {
        doNothing().when(patientRepository).delete(expectedPatient);
        when(patientRepository.findById(expectedPatient.getPatientId())).thenReturn(Optional.of(expectedPatient));

        Throwable exception = assertThrows(PatientNotDeletedExcecption.class, () -> patientService.deletePatient(expectedPatient));

        assertEquals("Patient with id 0 is not deleted", exception.getMessage());
        verify(patientRepository, times(1)).delete(expectedPatient);
        verify(patientRepository, times(1)).findById(expectedPatient.getPatientId());
    }
}
