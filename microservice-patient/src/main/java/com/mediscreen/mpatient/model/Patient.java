package com.mediscreen.mpatient.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Class use to connect data of table user into an object
 */
@Entity
@Table(name = "patient")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Integer patientId;

    @NotBlank(message = "Family is mandatory")
    @Column(name = "family")
    private String family;

    @NotBlank(message = "Given is mandatory")
    @Column(name = "given")
    private String given;

    @NotNull(message = "Date of birth is mandatory")
    @PastOrPresent(message = "Incorrect date of birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
    @Column(name = "dob")
    private LocalDate dob;

    @NotBlank(message = "Sex is mandatory")
    @Pattern(regexp = "[MmFf]{1}")
    @Column(name = "sex")
    private String sex;

    @Column(name = "address")
    private String address;

    @Pattern(regexp = "[0-9][0-9][0-9]-[0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]")
    @Column(name = "phone")
    private String phone;
}
