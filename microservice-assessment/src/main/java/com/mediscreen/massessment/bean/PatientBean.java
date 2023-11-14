package com.mediscreen.massessment.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class PatientBean {
    private Integer patientId;
    private String family;
    private String given;
    private LocalDate dob;
    private String sex;
    private String address;
    private String phone;
}
