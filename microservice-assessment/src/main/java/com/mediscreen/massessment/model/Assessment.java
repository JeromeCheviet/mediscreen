package com.mediscreen.massessment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * POJO object to send patient risk with the controller
 */
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
