package com.mediscreen.massessment.bean;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * POJO object to work with Patient
 */
@Getter
@Setter
public class PatientBean {
    private Integer patientId;
    private String family;
    private String given;
    private LocalDate dob;
    private String sex;
}
