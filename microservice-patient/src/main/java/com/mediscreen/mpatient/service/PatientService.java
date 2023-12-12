package com.mediscreen.mpatient.service;

import com.mediscreen.mpatient.model.Patient;

/**
 * Interface link to Patient operations
 */
public interface PatientService {

    /**
     * Get all patients
     *
     * @return an iterable list contains all patients
     */
    Iterable<Patient> getPatients();

    /**
     * Get one patient by his ID
     *
     * @param patientId patient id int number
     * @return an object which contain the patient data
     */
    Patient getPatientById(int patientId);

    /**
     * Add one patient
     *
     * @param patient an object which contain the patient to add
     */
    void addPatient(Patient patient);

    /**
     * Delete one patient
     *
     * @param patient an object which contain the patient to delete
     */
    void deletePatient(Patient patient);

    /**
     * Update one patient
     *
     * @param patient an object which contain patient's data to update
     * @return an object which contain the new patient data
     */
    Patient updatePatient(Patient patient);
}
