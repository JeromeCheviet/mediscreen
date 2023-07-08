package com.mediscreen.mpatient.service;

import com.mediscreen.mpatient.model.Patient;

public interface PatientService {
    Iterable<Patient> getPatients();

    Patient getPatientById(int patientId);

    void addPatient(Patient patient);

    void deletePatient(Patient patient);

    Patient updatePatient(Patient patient);
}
