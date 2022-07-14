package com.example.mycli.model;

import lombok.*;

import javax.validation.constraints.AssertTrue;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FindUserByIDWrap {
    private String fullName;
    private List<SubjectType> subjectList;
    private String university;
}
