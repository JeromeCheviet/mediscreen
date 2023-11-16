package com.mediscreen.massessment.model;

public enum Risk {
    NONE("None"),
    BORDERLINE("Borderline"),
    DANGER("In Danger"),
    EARLY_ONSET("Early onset");

    private String assessment = "";

    Risk(String assessment) {
        this.assessment = assessment;
    }

    @Override
    public String toString() {
        return assessment;
    }
}
