package com.mediscreen.mpatient.service;

import com.mediscreen.mpatient.exception.PatientNotCreatedException;
import com.mediscreen.mpatient.exception.PatientNotDeletedExcecption;
import com.mediscreen.mpatient.exception.PatientNotFoundException;
import com.mediscreen.mpatient.exception.PatientNotUpdatedException;
import com.mediscreen.mpatient.model.Patient;
import com.mediscreen.mpatient.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Iterable<Patient> getPatients() {
        logger.debug("Get all patients");
        Iterable<Patient> patients = patientRepository.findAll();
        if (!patients.iterator().hasNext()) {
            throw new PatientNotFoundException("No patients found in database");
        }
        return patients;
    }

    @Override
    public Patient getPatientById(int patientId) {
        logger.debug("Get patient with id {}", patientId);
        Optional<Patient> patient = patientRepository.findById(patientId);
        if (patient.isEmpty()) {
            throw new PatientNotFoundException("Patient with id " + patientId + " not found");
        }
        logger.debug("Return patient with id {} and family {}", patient.get().getPatientId(), patient.get().getFamily());

        return patient.get();
    }

    @Override
    public void addPatient(Patient patient) {
        logger.debug("Create patient with family {}", patient.getFamily());
        try {
            patientRepository.save(patient);
        } catch (Exception e) {
            throw new PatientNotCreatedException("Patient not created. Reason : " + e);
        }
    }

    @Override
    public void deletePatient(Patient patient) {
        logger.debug("Delete patient with id {}", patient.getPatientId());

        patientRepository.delete(patient);

        Optional<Patient> deletingPatient = patientRepository.findById(patient.getPatientId());

        if (deletingPatient.isPresent()) {
            throw new PatientNotDeletedExcecption("Patient with id " + patient.getPatientId() + " not deleted");
        }

    }

    @Override
    public Patient updatePatient(Patient patient) {
        logger.debug("Update patient with id {}", patient.getPatientId());
        patientRepository.save(patient);

        Patient updatedPatient = getPatientById(patient.getPatientId());


        if (!patient.toString().equals(updatedPatient.toString())) {
            throw new PatientNotUpdatedException("Patient with id " + patient.getPatientId() + " not updated");
        }

        return updatedPatient;
    }
}
