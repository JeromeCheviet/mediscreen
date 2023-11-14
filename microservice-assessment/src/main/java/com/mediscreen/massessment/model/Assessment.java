package com.mediscreen.massessment.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Assessment {
    private String patientFullName;
    private int patientAge;
    private Enum<Risk> risk;

}
