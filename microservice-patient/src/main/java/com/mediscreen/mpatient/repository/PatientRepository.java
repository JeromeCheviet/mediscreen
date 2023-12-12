package com.mediscreen.mpatient.repository;

import com.mediscreen.mpatient.model.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface to build queries run in table patient
 */
@Repository
public interface PatientRepository extends CrudRepository<Patient, Integer> {
}
