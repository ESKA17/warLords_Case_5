package com.example.mycli.entity;

import com.example.mycli.model.StudyDegree;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "user_info_table")
public class UserInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "city")
    private String city;

    @Column(name = "school" )
    private String school;

    @Column(name = "university")
    private String university;

    @Column(name = "graduation_year")
    private String graduationYear;

    @Column(name = "age")
    private Integer age;

    @Column(name = "IIN")
    private Integer IIN;

    @Column(name = "study_degree")
    @Enumerated(EnumType.STRING)
    private StudyDegree studyDegree;

    @Override
    public String toString() {
        return "UserInformation{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", city='" + city + '\'' +
                ", school='" + school + '\'' +
                ", university='" + university + '\'' +
                ", graduationYear='" + graduationYear + '\'' +
                ", age=" + age +
                ", IIN=" + IIN +
                ", studyDegree=" + studyDegree +
                '}';
    }
}
