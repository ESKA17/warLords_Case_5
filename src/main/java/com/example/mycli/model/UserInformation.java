package com.example.mycli.model;

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
@Table(name = "USER_INFORMATION")
public class UserInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_info")
    @SequenceGenerator(name = "user_info", sequenceName = "user_info", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private Integer age;

    @Column(name = "status")
    private Integer status = 0;

    @Column(name = "study_degree")
    @Enumerated(EnumType.STRING)
    private StudyDegree studyDegree;

    @Override
    public String toString() {
        return "UserInformation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", status=" + status +
                ", studyDegree=" + studyDegree +
                '}';
    }
}
