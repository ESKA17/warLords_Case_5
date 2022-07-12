package com.example.mycli.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ScreeningRequest {
    @NotNull
    private String city;
    @NotNull
    private String school;
    @NotNull
    private String IIN;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String university;
    @NotNull
    private String dateOfBirth;
    @NotNull
    private String aboutMe;
}
