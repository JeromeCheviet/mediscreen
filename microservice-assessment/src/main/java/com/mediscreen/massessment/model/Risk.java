package com.mediscreen.massessment.model;

import lombok.ToString;

@ToString
public enum Risk {
    NONE("None"),
    BORDERLINE("Borderline"),
    DANGER("In Danger"),
    EARLY_ONSET("Early onset");

    private String assessment = "";

    Risk(String assessment) {
        this.assessment = assessment;
    }
}
