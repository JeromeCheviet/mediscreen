package com.mediscreen.massessment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Assessment {
    private String patientFullName;
    private int patientAge;
    private Enum<Risk> risk;

}
