package com.mediscreen.muser.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;

@Data
@Builder
@Jacksonized
public class User {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String gender;
    private String address;
    private String phoneNumber;

}
