package com.example.mycli.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ScreeningRequest {
    private String fulName;
    private Integer age;
    private int studyDegree;
    private String phoneNumber;
    private String school;
    private String city;
    private String university;
    private Integer IIN;
    private String graduationYear;
}
