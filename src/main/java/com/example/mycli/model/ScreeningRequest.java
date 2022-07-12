package com.example.mycli.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ScreeningRequest {
    private String city;
    private String school;
    private String IIN;
    private String phoneNumber;
    private String university;
    private String dateOfBirth;
    private String aboutMe;
}
