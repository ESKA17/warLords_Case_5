package com.example.mycli.entity;

import com.example.mycli.model.StudyDegree;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_info_table")
public class UserInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "phone_number", nullable = false)
    private Integer phoneNumber;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "school", nullable = false)
    private String school;

    @Column(name = "university", nullable = false)
    private String university;

    @Column(name = "graduation_year", nullable = false)
    private String graduationYear;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "IIN")
    private Integer IIN;

    @Column(name = "study_degree", nullable = false)
    @Enumerated(EnumType.STRING)
    private StudyDegree studyDegree;

    @Column(name = "bachelor_spec")
    private String bachelor;

    @Column(name = "master_spec")
    private String master;

}
