package com.example.mycli.model;

import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.AssertTrue;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FindUserByIDWrap {
    private String fullName;
    @Enumerated(value = EnumType.STRING)
    private List<SubjectType> subjectList;
    private String university;
}
