package com.example.mycli.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ScreeningRequest {
    private String name;
    private String surname;
    private String fatherName;
    private Integer age;
    private int studyDegree;
}
