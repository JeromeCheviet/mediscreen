package com.mediscreen.massessment.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class NoteBean {
    private String id;
    private Integer patId;
    private String notes;
}
